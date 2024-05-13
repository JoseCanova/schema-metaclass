package org.nanotek.meta.model.rdbms.classification.data.result;

import java.util.List;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;

import schemacrawler.schema.TableConstraintColumn;

public class FirstNormalFormClassificationResult extends ClassificationResult<FirstNormalFormClassificationResult> {

	private static final long serialVersionUID = -6402099956409189701L;
	
	private String tableName;
	
	private String foreignKeyTableName;
	
	private List<TableConstraintColumn> foreignKeyColumns;
	
	public FirstNormalFormClassificationResult( String  tName , String fkTname) {
		super(TableTypeEnum.FIRST_NORMAL_FORM_TABLE);
		tableName = tName ;
		foreignKeyTableName = fkTname;
		
	}
	
	public FirstNormalFormClassificationResult( String  tName , String fkTname , List<TableConstraintColumn> columns) {
		super(TableTypeEnum.FIRST_NORMAL_FORM_TABLE);
		tableName = tName ;
		foreignKeyTableName = fkTname;
		foreignKeyColumns=columns;
	}
	
	public String getTableName() {
		return tableName;
	}

	public String getForeignKeyTableName() {
		return foreignKeyTableName;
	}

	public List<TableConstraintColumn> getForeignKeyColumns() {
		return foreignKeyColumns;
	}

}
