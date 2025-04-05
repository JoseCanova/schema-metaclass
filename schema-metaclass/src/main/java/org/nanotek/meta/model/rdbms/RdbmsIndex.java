package org.nanotek.meta.model.rdbms;

import java.util.List;
import java.util.stream.Collectors;

import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;

public class RdbmsIndex{

	protected  String name;
	
	protected  String fullName;
	
	protected  List<String> columnNames;
	
	public RdbmsIndex(Index index) {
		postConstruct(index);
	}

	private void postConstruct(Index index) {
		name = index.getName();
		fullName = index.getFullName();
		getColumnName(index.getColumns());
	}

	private void getColumnName(List<IndexColumn> columns) {
		columnNames  = columns
						.stream().map(c ->  c.getName())
						.collect(Collectors.toList());
	}

	public String getFullName() {
		return fullName;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}


}
