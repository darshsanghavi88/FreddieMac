package com.FM.api.property.service;

import java.util.List;

import com.FM.api.property.entity.Property;


public interface PropertyService {
	public Property getHighestPropertyByValue(List<String> homeIds);
}
