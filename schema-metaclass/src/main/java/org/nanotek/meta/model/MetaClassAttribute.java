package org.nanotek.meta.model;

import org.nanotek.meta.util.UUIDStringId;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

@MappedSuperclass
@ToString
public class MetaClassAttribute<T extends MetaClassAttribute<T>> {

	@Id
	@UUIDStringId
	private String id;
	
	@JsonProperty("name")
	protected String fieldName;

	public MetaClassAttribute() {
	}
	

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
