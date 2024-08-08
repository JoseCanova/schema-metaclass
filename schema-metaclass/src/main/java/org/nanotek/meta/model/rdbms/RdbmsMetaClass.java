package org.nanotek.meta.model.rdbms;

import java.util.List;
import java.util.Optional;

import org.nanotek.meta.model.IRdbmsClass;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaIdentity;
import org.nanotek.meta.validation.MetaClassDefaultValidationGroup;

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
//TODO: move attributes relative to rdbms here. 
public class RdbmsMetaClass extends MetaClass<RdbmsMetaClass,RdbmsMetaClassClassifier,RdbmsMetaClassAttribute> implements IRdbmsClass{

	private static final long serialVersionUID = -4542645486119141998L;

	@NotEmpty(groups= {MetaClassDefaultValidationGroup.class})
	@JsonProperty("tableName")
	protected String tableName;
	
	@JsonIgnore
	@NotNull(groups= {MetaClassDefaultValidationGroup.class})
	protected RdbmsClass rdbmsClass;
	
	public RdbmsMetaClass() {
		super();
	}

	
	public RdbmsMetaClass(String tableName, String className, Table table) {
		super();
		this.tableName = tableName;
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

	public boolean isHasPrimaryKey() {
			return  metaAttributes !=null && metaAttributes.stream().filter(a -> a.isId()).count() > 0;
	}
}
