package org.nanotek.meta.model.rdbms.classification.data;

import java.io.Serializable;

public enum TableTypeEnum implements Serializable{

	VOID_TABLE(1),
	PK_TABLE(3), 
	COMMON_TABLE(5),
	FIRST_NORMAL_FORM_TABLE(7),
	SECOND_NORMAL_FORM_TABLE(17),
	THIRD_NORMAL_FORM(41), 
	FACT_TABLE(43), 
	DIMENSION_TABLE(47),
	INTEGRATION_TABLE(53);
	
	private Integer type;

	TableTypeEnum(Integer value) {
		this.type= value;
	}
	
	public Integer getType() {
		return this.type;
	}
	
}
