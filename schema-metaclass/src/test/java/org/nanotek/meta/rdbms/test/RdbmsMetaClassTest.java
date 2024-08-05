package org.nanotek.meta.rdbms.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Table;


@SpringBootTest
public class RdbmsMetaClassTest {
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Test
	void testRdbmsMetaClass() {
		assertNotNull(schemaCrawlerService);
		getCatalogTables()
		.map(ct -> toStream(ct))
		.orElseThrow()
		.map(t -> toRdbmsMetaClass(t));
	}
	
	
	private RdbmsMetaClass toRdbmsMetaClass(Table table) {
		String tableName = Optional.ofNullable(table.getFullName()).orElse(table.getName());
		assertNotNull(tableName);
		RdbmsMetaClass metaClass = new RdbmsMetaClass(tableName , tableName , table);
		assertNotNull(metaClass.getTableName());
		assertNotNull(metaClass.getRdbmsClass());
		return metaClass;
	}


	private Stream<Table> toStream(Collection<Table> ct) {
		return ct.stream();
	}


	public Optional<Collection<Table>> getCatalogTables(){
		return schemaCrawlerService.getCatalogTables();
	}
}
