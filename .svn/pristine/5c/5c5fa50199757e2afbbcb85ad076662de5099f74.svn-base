package com.lifeshs.vo.shop;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.shop.AttributeDTO;
import com.lifeshs.shop.AttributeValueDTO;

public class AttributeValueVO {
	
	private AttributeDTO attribute;
	
	private List<AttributeValueDTO> attributeValues;

	public AttributeDTO getAttribute() {
		return attribute;
	}

	public void setAttribute(AttributeDTO attribute) {
		this.attribute = attribute;
	}

	public List<AttributeValueDTO> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<AttributeValueDTO> attributeValues) {
		this.attributeValues = attributeValues;
	}
	
	public void addAttributeValue(AttributeValueDTO value) {
		if(this.attributeValues == null) {
			this.attributeValues = new ArrayList<>();
		}
		this.attributeValues.add(value);
	}

}
