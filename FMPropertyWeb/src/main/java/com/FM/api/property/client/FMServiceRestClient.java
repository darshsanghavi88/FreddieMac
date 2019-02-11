package com.FM.api.property.client;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.FM.api.property.entity.Property;
import com.FM.api.property.handler.FMResponseErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FMServiceRestClient {
	private static final Logger logger = Logger.getLogger(FMServiceRestClient.class);
	
	static RestTemplate restTemplate = new RestTemplate();
	static String propertyServiceUrl = "http://webservice-takehome-1.spookle.xyz/property?property_id=";

	public static Property getPropertyByValue(String home_id) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(propertyServiceUrl + home_id, HttpMethod.GET, request, String.class);
		String responseMsg = response.getBody();
		logger.debug(responseMsg);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Since we are not getting status code 404 and checking whether we have address or not
			if (FMResponseErrorHandler.isError(response.getStatusCode())|| !responseMsg.contains("address")) {
				// TODO We can make it more generic
				throw new RestClientException(response.getBody());
			} else {
				Property property = objectMapper.readValue(response.getBody(), Property.class);
				return property;
			}
		} catch (IOException e) {
			throw new RuntimeException("Technical Exception");
		}
	}

	public static Property getHighestPriceHome(List<String> homeIdList) {
		Property highestProperty = new Property();
		for (String string : homeIdList) {
			Property property = getPropertyByValue(string);
			if (property.getValue() > highestProperty.getValue()) {
				highestProperty = property;
			}
		}
		return highestProperty;
	}
}
