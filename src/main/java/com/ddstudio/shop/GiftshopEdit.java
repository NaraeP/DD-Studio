package com.ddstudio.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddstudio.shop.model.GiftShopDTO;
import com.ddstudio.shop.repository.ShopDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 기프트샵 수정 서블릿 입니다.
 * 선택한 기프트샵을 수정 하는 기능을 처리합니다.
 * @author pega0
 *
 */
@WebServlet("/shop/giftshop/edit.do")
public class GiftshopEdit extends HttpServlet {

	/**
	 * HTTP GET 요청을 처리합니다.
     * 
     * 기프트샵 수정 페이지로 포워딩합니다.
     * 
     * @param req  HTTP 요청 객체
     * @param resp HTTP 응답 객체
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String shop_seq = req.getParameter("seq");
		
		ShopDAO dao = new ShopDAO();
		
		GiftShopDTO dto = dao.getGiftshop(shop_seq);
		
		String[] times = dto.getTime().split(" ");
		
		dto.setStartTime(times[0]);
		dto.setEndTime(times[2]);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/shop/giftshop/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
	 * HTTP POST 요청을 처리합니다.
     * 
     * 클라이언트로부터 받은 기프트샵 정보 및 이미지를 처리하고, 결과에 따라 성공 또는 실패 메시지를 표시합니다.
     * 
     * @param req  HTTP 요청 객체
     * @param resp HTTP 응답 객체
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// multiple DATA라 req.getParameter() 동작 불가 > MultipartRequest로 대체해야댐
		ShopDAO dao = new ShopDAO();
		GiftShopDTO dto = new GiftShopDTO();
		ArrayList<String> fileList = new ArrayList<String>();
		
		try {
			MultipartRequest multi = new MultipartRequest(req, req.getRealPath("/asset/image"), 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy());
			System.out.println(req.getRealPath("/asset/image"));
			
			String shop_seq = multi.getParameter("shop_seq");
			String shopName = multi.getParameter("name");
			String startTime = multi.getParameter("start-time");
			String endTime = multi.getParameter("end-time");
			
			String time = startTime + " - " + endTime;
			
			String info = multi.getParameter("info");
			String tel = multi.getParameter("tel");
			String lng = multi.getParameter("lng");
			String lat = multi.getParameter("lat"); 
			
			dto.setShop_seq(shop_seq);
			dto.setName(shopName);
			dto.setTime(time);
			dto.setInfo(info);
			dto.setTel(tel);
			dto.setLat(lat);
			dto.setLng(lng);
			
			dao.addLocation(dto);
			
			String location_seq = dao.getLocationSeq(dto);
			
			if (location_seq != null) {
				dto.setLocation_seq(location_seq);
				
				int result = dao.editGiftShop(dto);
				
				if (result == 1) {
					Enumeration<?> files = multi.getFileNames();
					
					while (files.hasMoreElements()) {
					    String name = (String) files.nextElement();
					    String filename = multi.getFilesystemName(name);
					    // 파일명을 이용하여 다양한 작업 수행
					    // filename을 리스트에 추가하거나, DB에 저장하거나, 다른 작업 수행 가능
					    if (filename != null) {
					    	fileList.add(filename);
					    }
					}
					
					if(fileList.size() > 0) {
						result = dao.addShopImg(fileList, dto.getShop_seq());
						
						if (result > 0) {
							resp.sendRedirect("/ddstudio/shop/giftshop/detail.do?seq=" + dto.getShop_seq());
						} else {
							PrintWriter writer = resp.getWriter();
							writer.print("<script>alert('Add Shop Img failed'); history.back();</script>");
							writer.close();
						}
					} else {
						resp.sendRedirect("/ddstudio/shop/giftshop/detail.do?seq=" + dto.getShop_seq());
					}
				} else {
					PrintWriter writer = resp.getWriter();
					writer.print("<script>alert('Edit Shop failed'); history.back();</script>");
					writer.close();
				}
			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('location failed'); history.back();</script>");
				writer.close();
			}
			
		} catch (Exception e) {
			System.out.println("GiftshopEdit.doPost()");
			e.printStackTrace();
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed'); history.back();</script>");
		writer.close();
		
	}

}