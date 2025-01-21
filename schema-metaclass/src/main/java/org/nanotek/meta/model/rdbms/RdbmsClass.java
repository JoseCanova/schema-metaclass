package org.nanotek.meta.model.rdbms;

import java.util.ArrayList;
import java.util.List;

import org.nanotek.meta.model.rdbms.table.RdbmsSchemaTable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import schemacrawler.schema.Table;

public class RdbmsClass {

	protected RdbmsSchemaTable table;
	
	private List<RdbmsSchemaTable> referencedTables;
	
	public RdbmsClass() {
		super();
		this.referencedTables=new ArrayList<>();
	}
	
	public RdbmsClass(RdbmsSchemaTable  table) {
		super();
		this.table = table;
		this.referencedTables=new ArrayList<>();
		
	}
	
	
	public void addReferencedTable(RdbmsSchemaTable referencedTable) {
		this.referencedTables.add(referencedTable);
	}

	public List<RdbmsSchemaTable> getReferencedTables() {
		return referencedTables;
	}
	
	public void setTable(RdbmsSchemaTable t) {
		this.table = t;
	}

	public RdbmsSchemaTable getTable() {
		return table;
	}

	public Table getSchemaTable() {
		return table.getSchemaTable();
	}

}
