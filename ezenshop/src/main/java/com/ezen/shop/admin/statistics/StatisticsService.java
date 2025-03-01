package com.ezen.shop.admin.statistics;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatisticsService {

	private final StatisticsMapper statisticsMapper;
	
//	public List<Map<String, Object>> monthlysales_statistics(int year) {
//		return statisticsMapper.monthlysales_statistics(year);
//	}
	
	public List<OrderAmount> monthlysales_statistics(int year) {
		return statisticsMapper.monthlysales_statistics(year);
	}
}
