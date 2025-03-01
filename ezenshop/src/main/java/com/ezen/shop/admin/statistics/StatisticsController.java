package com.ezen.shop.admin.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin/statist/*")
@RequiredArgsConstructor
@Controller
public class StatisticsController {

	private final StatisticsService statisticsService;
	
	// 주문통계에서 기본적으로 보여줄 페이지.
	@GetMapping("/order_statist")
	public void order_statist() {
		
	}
	
	// ajax요청으로 차트에 사용할 데이타
	@GetMapping("/monthlysales")
	public ResponseEntity<Map<String, Object>>  monthlysales_statistics(Integer year) throws Exception {
		
		log.info("년도" + year);
		
		// 특정년도의 월별데이타 매출현황
		//List<Map<String, Object>> monthlysales = statisticsService.monthlysales_statistics(year);
		List<OrderAmount> monthlysales = statisticsService.monthlysales_statistics(year);
		
		// 차트 레이블과 데이터 포맷에 맞게 작업.(스프링부트, 자바스크립트)
		Map<String, Object> response = new HashMap<>();
		
		
        response.put("labels", monthlysales.stream().map(OrderAmount::getMonth).toArray());
        response.put("data", monthlysales.stream().map(OrderAmount::getAmount).toArray());
		
		//entity = new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/monthlysales2")
	public ResponseEntity<List<Map<String, Object>>> monthlysales2(Integer year) throws Exception {
		
		return ResponseEntity.ok(statisticsService.monthlysales_statistics2(year));
	}
	
	// 전체주문통계
	@GetMapping("/static_sale_all")
	public void static_sale_all() throws Exception {
				
	}
	
	@GetMapping("/daily")
	public ResponseEntity<List<Map<String, Object>>> getDailyStatistics(int year, int month) throws Exception {
		
		return ResponseEntity.ok(statisticsService.getDailyStatistics(year, month));
	}
	
	@GetMapping("/hourly")
	public ResponseEntity<List<Map<String, Object>>> getHourlyStatistics(String start_date, String end_date) throws Exception {
		
		return ResponseEntity.ok(statisticsService.getHourlyStatistics(start_date, end_date));
	}
	
	@GetMapping("/weekly")
	public ResponseEntity<List<Map<String, Object>>> getWeeklyStatistics(String start_date, String end_date) throws Exception {
		
		return ResponseEntity.ok(statisticsService.getWeeklyStatistics(start_date, end_date));
	}
	
	@GetMapping("/monthly")
	public ResponseEntity<List<Map<String, Object>>> getMonthlyStatistics(int year) throws Exception {
		
		return ResponseEntity.ok(statisticsService.getMonthlyStatistics(year));
	}
	
	
	
}
