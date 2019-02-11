package com.FM.api.property.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.FM.api.property.client.FMServiceRestClient;
import com.FM.api.property.entity.Property;
import com.FM.api.property.service.PropertyService;

@RestController
@RequestMapping(value = "/api/v1/property")
public class PropertyController {
	private static final Logger logger = Logger.getLogger(PropertyController.class);
	
	PropertyService service;
	
	@RequestMapping(value="/getHighestValue", method = RequestMethod.GET)
	public String getHighestPropertyByValue(@RequestBody List<String> properties) {
		logger.debug(properties.size());
		try {
			Property property = FMServiceRestClient.getHighestPriceHome(properties);
			return property.getOwner();
		} catch (RestClientException restClientException) {
			return "Property Not Found";
		} catch (Exception exception) {
			return "Technical Exception. Check with the service";
		}
	}
}
