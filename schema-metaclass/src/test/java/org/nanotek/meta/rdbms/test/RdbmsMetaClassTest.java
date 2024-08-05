package org.nanotek.meta.rdbms.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Column;
import schemacrawler.schema.Table;


@SpringBootTest
public class RdbmsMetaClassTest {
	
	
	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy; 
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Test
	void testRdbmsMetaClass() {
		assertNotNull(schemaCrawlerService);
		assertNotNull(columnNameTranslationStrategy);
		Optional<Collection<Table>> ocTables = getCatalogTables();
		
		assertTrue(ocTables.isPresent());
		
		int tablesSize = ocTables.get().size();
		
		List<?> metaClassList = ocTables
		.map(ct -> toStream(ct))
		.orElseThrow()
		.map(t -> createMetaClass(t))
		.collect(Collectors.toList());
		
		
		assertTrue(tablesSize == metaClassList.size());
	}
	
	
	private RdbmsMetaClass createMetaClass(Table table) {
		String tableName = Optional.ofNullable(table.getFullName()).orElse(table.getName());
		assertNotNull(tableName);
		RdbmsMetaClass metaClass = new RdbmsMetaClass(tableName , tableName , table);
		assertNotNull(metaClass.getTableName());
		assertNotNull(metaClass.getRdbmsClass());
		populateMetaClassAttributes(metaClass);
		return metaClass;
	}


	
	public void populateMetaClassAttributes(RdbmsMetaClass metaClass) {
		final RdbmsMetaClass mc = metaClass;
		RdbmsClass rc = mc.getRdbmsClass();
		List<Column> lc = rc.getTable().getColumns();
		
		lc.forEach(c -> {
			RdbmsMetaClassAttribute md = new RdbmsMetaClassAttribute();
			md.setClazz(c.getColumnDataType().getTypeMappedClass());
			md.setColumnName(c.getName());
			String fieldName = columnNameTranslationStrategy.processNameTranslationStrategy(c.getName());
			md.setFieldName(fieldName);
		});
	}
	private Stream<Table> toStream(Collection<Table> ct) {
		return ct.stream();
	}


	public Optional<Collection<Table>> getCatalogTables(){
		return schemaCrawlerService.getCatalogTables();
	}
}
