package org.nanotek.meta.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

@MappedSuperclass
@ToString
public class MetaClassAttribute<T extends MetaClassAttribute<T>> {

	@JsonProperty("name")
	protected String fieldName;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public MetaClassAttribute() {
	}

}
