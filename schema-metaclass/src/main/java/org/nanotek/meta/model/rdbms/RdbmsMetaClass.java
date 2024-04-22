package org.nanotek.meta.model.rdbms;

import java.util.List;

import org.nanotek.meta.model.Classifier;
import org.nanotek.meta.model.IRdbmsClass;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaClassAttribute;
import org.nanotek.meta.validation.MetaClassDefaultValidationGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import schemacrawler.schema.Table;

//TODO: move attributes relative to rdbms here. 
public class RdbmsMetaClass extends MetaClass<RdbmsMetaClass,Classifier<?>> implements IRdbmsClass{

	private static final long serialVersionUID = -4542645486119141998L;

	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	@JsonProperty("tableName")
	protected String tableName;
	
	@JsonIgnore
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	protected RdbmsClass rdbmsClass;
	
	public RdbmsMetaClass() {
		super();
		this.rdbmsClass = new RdbmsClass();
	}

	public RdbmsMetaClass(String tableName, String className, List<MetaClassAttribute> metaAttributes) {
		super(className, metaAttributes);
		this.rdbmsClass = new RdbmsClass();
	}

	public RdbmsMetaClass(String tableName, String className, Table table) {
		super(className, null);
			this.className = className;
			this.rdbmsClass = new RdbmsClass(table);
			classifier = new RdbmsMetaClassClassifier ();
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public RdbmsClass getRdbmsClass() {
		return rdbmsClass;
	}

	public void setRdbmsClass(RdbmsClass rdbmsClass) {
		this.rdbmsClass = rdbmsClass;
	}

}
