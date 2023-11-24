package com.ddstudio.shop.model;

import lombok.Data;

/**
 * 레스토랑 데이터 전송 객체입니다.
 * @author pega0
 *
 */
@Data
public class RestaurantDTO {
	private String restaurant_seq;
	private String name;
	private String menu;
	private String time;
	private String capacity;
	private String tel;
	private String lat;
	private String lng;
	private String category_seq;
	private String category;
	private String img;
	private String start_date;
	private String end_date;
	
	private String location_seq;
}