package org.nanotek.meta.model.rdbms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaClassAttribute;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
//TODO: Verify best strategy for class attribute. usage for Class.forName is preferable than alter codec for Model MongoDb persistence.
//TODO: Implement a "className" attribute that conforms with transient attribute clazz fullName
@ToString
public class RdbmsMetaClassAttribute 
extends MetaClassAttribute<RdbmsMetaClassAttribute> implements Serializable {

	@JsonProperty("isId")
	protected boolean isPartOfId;
	@JsonProperty("clazz")
	protected String clazz;
//	protected Class<?> clazz;
	@JsonProperty("columnName")
	protected String columnName;
	@JsonProperty("length")
	protected String length;
	@JsonProperty("required")
	protected boolean required;
	@JsonProperty("sqlType")
	protected String sqlType;
	@JsonProperty("attributes")
	@Transient
	protected transient Map<String, Object> attributes;
	@JsonProperty("isPartOfIndex")
	protected boolean partOfIndex;
	@JsonProperty("isGenerated")
	protected boolean generated;
	@JsonProperty("isPartOfForeignKey")
	protected boolean partOfForeignKey;
	
	private List<String> idAliases;
	
	@JsonIgnore
	@Transient
	private transient MetaClass<?,?> metaClass;
	
	
	public RdbmsMetaClassAttribute() {
		super();
	}

	public RdbmsMetaClassAttribute(MetaClass<?,?> mc) {
		super();
		this.metaClass=mc;
	}
	
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getClazz(){
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
	
	public boolean isPartOfIndex() {
		return partOfIndex;
	}

	public void setPartOfIndex(boolean partOfIndex) {
		this.partOfIndex = partOfIndex;
	}
	
	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
	}
	
	public boolean isPartOfForeignKey() {
		return partOfForeignKey;
	}

	public void setPartOfForeignKey(boolean partOfForeignKey) {
		this.partOfForeignKey = partOfForeignKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPartOfId() {
		return isPartOfId;
	}

	public void setPartOfId(boolean isPartOfId) {
		this.isPartOfId = isPartOfId;
	}
	
	
	
}
