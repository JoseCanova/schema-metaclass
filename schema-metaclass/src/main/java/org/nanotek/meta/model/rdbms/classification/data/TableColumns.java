package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;
import java.util.Optional;

import schemacrawler.schema.Column;

public record TableColumns (Optional<List<Column>> columns) {
	public TableColumns(final Optional<List<Column>> columns) {
		this.columns = columns;
	}
}