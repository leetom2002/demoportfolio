package com.ezen.shop.admin.statistics;

import java.util.List;
import java.util.Map;

public interface StatisticsMapper {

	//List<Map<String, Object>> monthlysales_statistics(int year);
	List<OrderAmount> monthlysales_statistics(int year);
	
}
