package org.nanotek.meta.task.classification.mb.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;
import org.nanotek.meta.model.rdbms.classification.task.VoidTableClassificationTask;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Table;


@SpringBootTest
public class VoidTableClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	VoidTableClassificationTask voidTableClassificationTask;
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	public VoidTableClassificationTaskTest() {
	}

	@Test
	void testVoidTableClassificationInjection() throws SQLException {
		assertNotNull(voidTableClassificationTask);
		assertNotNull(schemaCrawlerService);
		Optional<Collection<Table>> oTables = schemaCrawlerService
				.getCatalogTables();
		oTables
		.ifPresent(lt -> lt.forEach(t -> {
			System.err.println("there are tables");
			schemaCrawlerService
			.getTableClassificationData(Optional.ofNullable(t))
			.ifPresent(cd -> {
				voidTableClassificationTask
				.evaluate(cd)
				.ifPresentOrElse(cr ->{
					assertTrue (cr.getId().equals(TableTypeEnum.VOID_TABLE));
				}, new Runnable() {
					@Override
					public void run() {
						throw new RuntimeException("problem on test");
					}} );
			});
		}));
		assertTrue(1 >= 1);
	}
	
}
