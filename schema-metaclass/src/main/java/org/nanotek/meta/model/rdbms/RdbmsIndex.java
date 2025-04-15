package org.nanotek.meta.model.rdbms;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;

@JsonInclude(value = Include.NON_NULL)
public class RdbmsIndex{

	protected  String name;
	
	protected  String fullName;
	
	protected  List<String> columnNames;
	
	protected Boolean isUnique;
	
	public RdbmsIndex(Index index) {
		postConstruct(index);
	}

	private void postConstruct(Index index) {
		name = index.getName();
		fullName = index.getFullName();
		getColumnNames(index.getColumns());
		isUnique = index.isUnique();}

	private void getColumnNames(List<IndexColumn> columns) {
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
	

	public Boolean getIsUnique() {
		return isUnique;
	}

	@Override
	public String toString() {
		return "RdbmsIndex [name=" + name + ", fullName=" + fullName + ", columnNames=" + columnNames + "]";
	}

}
