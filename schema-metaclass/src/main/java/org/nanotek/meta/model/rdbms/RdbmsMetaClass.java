package org.nanotek.meta.model.rdbms;

import java.util.Optional;

import org.nanotek.meta.model.IRdbmsClass;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaIdentity;
import org.nanotek.meta.validation.MetaClassDefaultValidationGroup;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import schemacrawler.schema.Table;


/**
 * public boolean isHasPrimaryKey() {
		return  metaAttributes !=null && metaAttributes.stream().filter(a -> a.isId()).count() > 0;
	}
 */
public class RdbmsMetaClass extends MetaClass<RdbmsMetaClass,RdbmsMetaClassClassifier,RdbmsMetaClassAttribute> implements IRdbmsClass{

	private static final long serialVersionUID = -4542645486119141998L;

	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	@JsonProperty("tableName")
	protected String tableName;
	
	//TODO: Refactor RdbmsClass to permits to be a persistent class on Spring Data Model.
	@JsonIgnore
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	@Transient
	protected transient RdbmsClass rdbmsClass;
	
	public RdbmsMetaClass() {
		super();
	}

	public RdbmsMetaClass(String tableName, String className) {
		super();
		this.tableName = tableName;
		this.className=className;
		this.postConstruct(null);
	}
	
	public RdbmsMetaClass(String tableName, String className, Table table) {
		super();
		this.tableName = tableName;
		this.className=className;
		this.postConstruct(table);
	}
	
	public RdbmsMetaClass(String id , String tableName, String className, Table table) {
		super(id);
		this.tableName = tableName;
		this.className=className;
		this.postConstruct(table);
	}

	
	protected void postConstruct(Table table) {
		Optional
		.ofNullable(table)
		.ifPresentOrElse(
				t -> { 
					this.rdbmsClass = new RdbmsClass(t);
					verifyMetaClassIdentity(table);
				}
		, () -> this.rdbmsClass = new RdbmsClass());
		classifier = new RdbmsMetaClassClassifier ();
	}
	
	
	
	private void verifyMetaClassIdentity(Table table) {
		Optional.ofNullable(table.getPrimaryKey())
		.ifPresent(id -> {
			MetaIdentity mi = new MetaIdentity(id);
			this.setIdentity(mi);
		});
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

	/**
	 * 
	 * @return true if all is ok with RdbmsMetaclass construction of pk verification.
	 */
	public boolean isHasPrimaryKey() {
			return  this.identity !=null && this.metaAttributes !=null;
	}
}
