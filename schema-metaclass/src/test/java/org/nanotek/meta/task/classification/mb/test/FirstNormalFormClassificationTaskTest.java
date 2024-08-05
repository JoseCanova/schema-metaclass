package org.nanotek.meta.task.classification.mb.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.SchemaTable;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexes;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.classification.task.FirstNormalFormClassificationTask;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import schemacrawler.schema.Table;

@SpringBootTest
public class FirstNormalFormClassificationTaskTest {

	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	FirstNormalFormClassificationTask theTask;
	
	public FirstNormalFormClassificationTaskTest() {
	}

	@Test
	void testVoidTableClassificationInjection() throws SQLException {
		assertNotNull(schemaCrawlerService);
		List<Optional<?>> resultList = new ArrayList<Optional<?>>();
		schemaCrawlerService
		.getCatalogTables()
		.ifPresentOrElse(t -> {
			int count = t.size();
			Table[] tary = t.toArray(new Table[count]);
			for (int i = 0 ; i < count-1 ; i++) {
				for (int j = i + 1 ; j < count ; ++j) {
					System.err.println(" " + tary[i].getName() + "  " + tary[j].getName());
					ClassificationData cd1 = buildClassificationData (tary[i]);
					ClassificationData cd2 = buildClassificationData (tary[j]);
					ClassificationDataPair cdp = new ClassificationDataPair(Pair.of(cd1,cd2)) ;
					Optional<?> cr = theTask.evaluate(cdp);
					cr.ifPresent(c -> {
						resultList.add(Optional.of(c));
					});
				}
			}
			}, new Runnable() {
			@Override
			public void run() {
				throw new RuntimeException("Problem on Test");
			}
		});
		assertTrue(resultList.size() > 0);
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
