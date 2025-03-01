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
		
		Map<String, Object> response = new HashMap<>();
        response.put("labels", monthlysales.stream().map(OrderAmount::getMonth).toArray());
        response.put("data", monthlysales.stream().map(OrderAmount::getAmount).toArray());
		
		//entity = new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
		return ResponseEntity.ok(response);
	}
	
}
