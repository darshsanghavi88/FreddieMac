package com.FM.api.property.handler;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.FM.api.property.service.impl.PropertyServiceImpl;

public class FMResponseErrorHandler implements ResponseErrorHandler {

	private static final Logger logger = Logger.getLogger(PropertyServiceImpl.class);

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		logger.debug("Response error: {} {}" + response.getStatusCode() + response.getStatusText());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return isError(response.getStatusCode());
	}

	public static boolean isError(HttpStatus status) {
		HttpStatus.Series series = status.series();
		return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
	}
}
