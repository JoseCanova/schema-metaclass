package org.nanotek.meta.model.rdbms;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RdbmsMetaClassForeignKey {

	@JsonIgnore
	RdbmsForeignKey foreignKey;
	
	private String tableName;
	
	private String columnName;
	
	private String joinColumnName;
	
	public RdbmsMetaClassForeignKey(RdbmsForeignKey foreignKey) {
		this.foreignKey=foreignKey;
		postConstruct();
	}

	private void postConstruct() {
		this.tableName = foreignKey.getMetaClass().getTableName();
		this.columnName= Optional
						.ofNullable(foreignKey.referenceMetaClassAttribute)
						.map(a -> a.getColumnName()).orElse(null);
		this.joinColumnName=Optional
							.ofNullable(foreignKey.metaClassAttribute)
							.map(a -> a.getColumnName()).orElse(null);;
	}

	public String getTableName() {
		return tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getJoinColumnName() {
		return joinColumnName;
	}

}
