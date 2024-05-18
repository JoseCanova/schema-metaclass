package org.nanotek.meta.task.classification.mb.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.SchemaTable;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexes;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.classification.data.result.ThirdNormalFormClassificationResult;
import org.nanotek.meta.model.rdbms.classification.task.ThirdNormalFormClassificationTask;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Table;

@SpringBootTest
public class ThirdNormalFormClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired 
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	ThirdNormalFormClassificationTask thirdNormalFormTask;
	
	@Test
	void testSecondNormalFormClassificationResult() throws SQLException {
		assertNotNull(defaultDataSource);
		assertNotNull(schemaCrawlerService);
		assertNotNull(thirdNormalFormTask);
		List<Optional<ClassificationResult<?>>> resultList = new ArrayList<Optional<ClassificationResult<?>>>();
		schemaCrawlerService
		.getCatalogTables()
		.ifPresentOrElse(t -> {
			int count = t.size();
			Table[] tary = t.toArray(new Table[count]);
			for (int i = 0 ; i < count ; i++) {
					ClassificationData cd1 = buildClassificationData (tary[i]);
						Optional<ThirdNormalFormClassificationResult> csf2 = thirdNormalFormTask.evaluate(cd1);
						csf2
						.ifPresent(c2->{
							resultList.add(Optional.of(c2));
						});
				}
//			}
			}, new Runnable() {
			@Override
			public void run() {
				throw new RuntimeException("Problem on Test");
			}
		});
		assertTrue(resultList.size() >= 1);
	}

	private ClassificationData buildClassificationData(Table table) {
		return new ClassificationData(
				new SchemaTable(Optional.of(table)),
				new TableKey(Optional.ofNullable(table.getPrimaryKey())),
				new TableColumns(Optional.ofNullable(table.getColumns())),
				new TableForeignKeys(Optional.ofNullable(table.getForeignKeys())), 
				new TableIndexes(Optional.ofNullable(table.getIndexes()))
				);
	}

	
}
