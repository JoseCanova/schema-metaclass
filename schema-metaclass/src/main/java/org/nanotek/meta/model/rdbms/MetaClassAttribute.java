package org.nanotek.meta.model.rdbms;

import java.util.List;
import java.util.Map;

import org.nanotek.meta.model.MetaClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class MetaClassAttribute  {

	@JsonProperty("isId")
	protected boolean isId = false;
	@JsonProperty("clazz")
	protected Class<?> clazz;
	@JsonProperty("columnName")
	protected String columnName;
	@JsonProperty("length")
	protected String length;
	@JsonProperty("required")
	protected boolean required;
	@JsonProperty("sqlType")
	private String sqlType;
	@JsonProperty("fieldName")
	private String fieldName;
	@JsonProperty("attributes")
	private Map<String, Object> attributes;
	
	private List<String> idAliases;
	
	@JsonIgnore
	private MetaClass<?,?> metaClass;
	
	
	public MetaClassAttribute() {
		super();
	}

	public MetaClassAttribute(MetaClass<?,?> mc) {
		super();
		this.metaClass=mc;
	}
	
	
	public boolean isId() {
		return isId;
	}



	
	public void setId(boolean isId) {
		this.isId = isId;
	}



	
	public String getColumnName() {
		return columnName;
	}




	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}




	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}


	
	public Class<?> getClazz(){
		return this.clazz;
	}

	
	public String getSqlType() {
		return sqlType;
	}

	
	public void setSqlType(String string) {
		this.sqlType = string;
	}

	
	public String getLength() {
		return length;
	}

	
	public void setLength(String length) {
		this.length = length;
	}

	
	public boolean isRequired() {
		return required;
	}

	
	public void setRequired(boolean required) {
		this.required = required;
	}

	
	public void setFieldName(String name) {
		this.fieldName = name;
	}

	
	public String getFieldName() {
		return fieldName;
	}

	
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	
	public List<String> getIdAliases() {
		return idAliases;
	}

	
	public void setIdAliases(List<String> idAliases) {
		this.idAliases = idAliases;
	}

	public MetaClass<?,?> getMetaClass() {
		return metaClass;
	}

	public void setMetaClass(MetaClass<?,?> metaClass) {
		this.metaClass = metaClass;
	}
	
	
}
