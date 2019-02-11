package com.FM.api.property.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.FM.api.property.client.FMServiceRestClient;
import com.FM.api.property.entity.Property;

public class PropertyServiceImpl {
	private static final Logger logger = Logger.getLogger(PropertyServiceImpl.class);
	
	public Property getHighestPropertyByValue(List<String> homeIds){
		return FMServiceRestClient.getHighestPriceHome(homeIds);
	}
}
