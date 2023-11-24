package com.ddstudio.ticket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ddstudio.ticket.model.TicketDTO;
import com.ddstudio.ticket.repository.TicketDAO;

/**
 * 예매 서블릿입니다.
 * 예매를 추가 하는 기능을 처리합니다.
 * @author pega0
 *
 */
@WebServlet("/ticket/addReservation.do")
public class AddReservation extends HttpServlet {

	/**
	 * HTTP POST 요청을 처리합니다.
     * 
     * 클라이언트로부터 받은 예매 정보를 처리하고, 결과에 따라 성공 또는 실패 메시지를 표시합니다.
     * 
     * @param req  HTTP 요청 객체
     * @param resp HTTP 응답 객체
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String user_seq = (String) session.getAttribute("seq");

		TicketDAO dao = new TicketDAO();

		TicketDTO adultDTO = new TicketDTO();
		TicketDTO teenagerDTO = new TicketDTO();
		TicketDTO childDTO = new TicketDTO();

		String date = req.getParameter("date");
		String adult_ea = req.getParameter("adult_ea");
		String adult_seq = req.getParameter("adult_seq");
		String adult_price = req.getParameter("adult_price");
		String teenager_ea = req.getParameter("teenager_ea");
		String teenager_seq = req.getParameter("teenager_seq");
		String teenager_price = req.getParameter("teenager_price");
		String child_ea = req.getParameter("child_ea");
		String child_seq = req.getParameter("child_seq");
		String child_price = req.getParameter("child_price");
		String benefit_seq = req.getParameter("benefit_seq");

		String ticket_book_seq;

		adultDTO.setDate(date);
		adultDTO.setEa(adult_ea);
		adultDTO.setSeq(adult_seq);
		adultDTO.setPrice(adult_price);
		adultDTO.setBenefit_seq(benefit_seq);

		teenagerDTO.setDate(date);
		teenagerDTO.setEa(teenager_ea);
		teenagerDTO.setSeq(teenager_seq);
		teenagerDTO.setPrice(teenager_price);
		teenagerDTO.setBenefit_seq(benefit_seq);

		childDTO.setDate(date);
		childDTO.setEa(child_ea);
		childDTO.setSeq(child_seq);
		childDTO.setPrice(child_price);
		childDTO.setBenefit_seq(benefit_seq);

		int result = dao.addAdultReservation(adultDTO);

		if (result == 1 || result == -1) {

			if (result == 1) {
				ticket_book_seq = dao.getTicketBookSeq();
				result = dao.addUserBook(user_seq, ticket_book_seq);

				if (result != 1) {
					PrintWriter writer = resp.getWriter();
					writer.print("<script>alert('Add Adult UserBook failed'); history.back();</script>");
					writer.close();
				}
			}

			result = dao.addTeenagerReservation(teenagerDTO);

			if (result == 1 || result == -1) {
				if (result == 1) {
					ticket_book_seq = dao.getTicketBookSeq();
					result = dao.addUserBook(user_seq, ticket_book_seq);

					if (result != 1) {
						PrintWriter writer = resp.getWriter();
						writer.print("<script>alert('Add Teenager UserBook failed'); history.back();</script>");
						writer.close();
					}
				}

				result = dao.addChildReservation(childDTO);

				if (result == 1 || result == -1) {
					if (result == 1) {
						ticket_book_seq = dao.getTicketBookSeq();
						result = dao.addUserBook(user_seq, ticket_book_seq);

						if (result != 1) {
							PrintWriter writer = resp.getWriter();
							writer.print("<script>alert('Add Child UserBook failed'); history.back();</script>");
							writer.close();
						}
					}
					
					resp.setContentType("text/html; charset=UTF-8");
					
					PrintWriter writer = resp.getWriter();
					writer.print("<script>alert('티켓 예매 완료');location.href='/ddstudio/index.do';</script>");
					writer.close();
					
					return;
					
				} else {
					PrintWriter writer = resp.getWriter();
					writer.print("<script>alert('Add Child failed'); history.back();</script>");
					writer.close();
				}

			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('Add Teenager failed'); history.back();</script>");
				writer.close();
			}

		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('Add Adult failed'); history.back();</script>");
			writer.close();
		}

	}

}