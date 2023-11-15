package com.ddstudio.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ddstudio.activity.model.AttractionDTO;
import com.ddstudio.test.repository.TestDAO;

@WebServlet("/test/worldcup/attraction/detail.do")
public class WorldcupAttraction extends HttpServlet {

	private ArrayList<AttractionDTO> attractionList;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TestDAO dao = new TestDAO();

		// 어트랙션 리스트 가져오기
		attractionList = dao.getAttractionList();

		// 세션 가져오기
		HttpSession session = req.getSession();

		// 세션에서 선택한 어트랙션 리스트 가져오기
		@SuppressWarnings("unchecked") // 제네릭 경고 무시
		ArrayList<String> selectedAttractions = (ArrayList<String>) session.getAttribute("selectedAttractions");

		selectedAttractions = new ArrayList<>();

		// 선택하지 않은 어트랙션 리스트 생성
		ArrayList<AttractionDTO> remainingAttractions = new ArrayList<>();
		for (AttractionDTO attraction : attractionList) {
			remainingAttractions.add(attraction);
		}

		// 선택하지 않은 어트랙션 중에서 랜덤으로 두 개 선택
		ArrayList<AttractionDTO> selectedTwoAttractions = getRandomTwoAttractions(remainingAttractions);

		// 선택한 어트랙션과 선택한 두 어트랙션을 request에 저장
		req.setAttribute("selectedAttractions", selectedAttractions);
		req.setAttribute("selectedTwoAttractions", selectedTwoAttractions);

		// JSP로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/test/worldcup/attraction/detail.jsp");
		dispatcher.forward(req, resp);
	}

	// 랜덤으로 두 어트랙션을 선택하는 메서드
	private ArrayList<AttractionDTO> getRandomTwoAttractions(ArrayList<AttractionDTO> attractions) {
		ArrayList<AttractionDTO> selectedTwoAttractions = new ArrayList<>();

		Random random = new Random();

		if (attractions != null && attractions.size() > 1) {
			int index1 = random.nextInt(attractions.size());
			int index2;

			do {
				index2 = random.nextInt(attractions.size());
			} while (index1 == index2);

			// 두 어트랙션을 리스트에 추가
			selectedTwoAttractions.add(attractions.get(index1));
			selectedTwoAttractions.add(attractions.get(index2));
		} else if (attractions != null && attractions.size() == 1) {
			selectedTwoAttractions.add(attractions.get(0));
		}

		return selectedTwoAttractions;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String selectedAttractionSeq = req.getParameter("attractionSeq");
		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<String> selectedAttractions = (ArrayList<String>) session.getAttribute("selectedAttractions");

		// 새로운 세션일 경우에만 초기화
		if (req.getParameter("isNewSession") != null) {
			selectedAttractions = new ArrayList<>();
			session.setAttribute("selectedAttractions", selectedAttractions);
		}

		// System.out.println(req.getParameter("isNewSession"));

		// 선택한 어트랙션이 중복되지 않았을 때만 추가
		if (!selectedAttractions.contains(selectedAttractionSeq)) {
			selectedAttractions.add(selectedAttractionSeq);
			session.setAttribute("selectedAttractions", selectedAttractions);

			// 두 개의 어트랙션을 다시 선택
			ArrayList<AttractionDTO> remainingAttractions = new ArrayList<>();
			for (AttractionDTO attraction : attractionList) {
				if (!selectedAttractions.contains(attraction.getAttraction_seq())) {
					remainingAttractions.add(attraction);
				}
			}
			ArrayList<AttractionDTO> selectedTwoAttractions = getRandomTwoAttractions(remainingAttractions);

			// JSON 응답 생성
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("selectedAttraction", selectedAttractionSeq);
			jsonResponse.put("selectedAttractions", selectedAttractions);

			// 이미지 정보를 JSON으로 추가
			JSONArray selectedTwoAttractionsJsonArray = new JSONArray();
			for (AttractionDTO attraction : selectedTwoAttractions) {
				JSONObject attractionJson = new JSONObject();
				attractionJson.put("attraction_seq", attraction.getAttraction_seq());
				attractionJson.put("name", attraction.getName());
				attractionJson.put("img", attraction.getImg());
				selectedTwoAttractionsJsonArray.add(attractionJson);
			}

			// 선택되지 않은 어트랙션 시퀀스 추가
			ArrayList<String> remainingAttractionSeqs = new ArrayList<>();
			for (AttractionDTO attraction : remainingAttractions) {
				remainingAttractionSeqs.add(attraction.getAttraction_seq());
			}
			jsonResponse.put("remainingAttractionSeqs", remainingAttractionSeqs);

			jsonResponse.put("selectedTwoAttractions", selectedTwoAttractionsJsonArray);

			resp.getWriter().write(jsonResponse.toString());
		}
		
	}

}