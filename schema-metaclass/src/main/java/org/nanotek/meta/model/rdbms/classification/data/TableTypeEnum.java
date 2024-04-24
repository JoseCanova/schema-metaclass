package org.nanotek.meta.model.rdbms.classification.data;

public enum TableTypeEnum {

	VOID_TABLE(1),
	PK_TABLE(3), 
	COMMON_TABLE(5),
	FIRST_NORMAL_FORM_TABLE(7),
	SECOND_NORMAL_FORM_TABLE(17),
	THIRD_NORMAL_FORM(41);
	
	private Integer type;

	TableTypeEnum(Integer value) {
		this.type= value;
	}
	
	public Integer getType() {
		return this.type;
	}
	
}
