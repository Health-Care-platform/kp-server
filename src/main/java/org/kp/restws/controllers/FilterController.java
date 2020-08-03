package org.kp.restws.controllers;


import java.util.Arrays;
import java.util.List;

import org.kp.restws.model.DynamicallyFilteringSomeBean;
import org.kp.restws.model.StaticalyFilteringSomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

	@GetMapping("/staticfilter")
	public List<StaticalyFilteringSomeBean> filtering(){
		
		return Arrays.asList(new StaticalyFilteringSomeBean("field 1","field 2", "field 3","field 4"),new StaticalyFilteringSomeBean("field 12","field 22", "field 32","field 4"));
	}
	
	//only field1 and field3
	@GetMapping("/dynamicfilter")
	public MappingJacksonValue filteringDynamic(){
		
		
		DynamicallyFilteringSomeBean dynamicallyFilteringSomeBean = new DynamicallyFilteringSomeBean("field 1","field 2", "field 3","field 4");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("somefilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(dynamicallyFilteringSomeBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	

	//only field2 and field4
	@GetMapping("/dynamicfilterList")
	public MappingJacksonValue filteringDynamicList(){
		
		List<DynamicallyFilteringSomeBean> list = Arrays.asList(new DynamicallyFilteringSomeBean("field 1","field 2", "field 3","field 4"),new DynamicallyFilteringSomeBean("field 12","field 22", "field 32","field 44"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field4");
		FilterProvider filters = new SimpleFilterProvider().addFilter("somefilter", filter);
		mapping.setFilters(filters);
		
		return mapping;
	
	}
	
	
	
}
