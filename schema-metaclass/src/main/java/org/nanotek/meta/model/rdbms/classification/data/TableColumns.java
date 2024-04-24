package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;

import schemacrawler.schema.Column;

public record TableColumns (List<Column> columns) {
	public TableColumns(List<Column> columns) {
		this.columns = columns;
	}
}