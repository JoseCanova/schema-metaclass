package org.nanotek.meta.task.classification.test;

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
import org.nanotek.meta.model.rdbms.classification.data.SecondNormalFormClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexes;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.classification.task.FirstNormalFormClassificationTask;
import org.nanotek.meta.model.rdbms.classification.task.SecondNormalFormClassificationTask;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Table;

@SpringBootTest
public class SecondNormalFormClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired 
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	SecondNormalFormClassificationTask secondNormalFormTask;
	
	//TODO: Fix the test of Classification - the iteration of table collection is providing double of the correct value
	@Test
	void testSecondNormalFormClassificationResult() throws SQLException {
		assertNotNull(defaultDataSource);
		assertNotNull(schemaCrawlerService);
		assertNotNull(secondNormalFormTask);
		List<Optional<ClassificationResult<?>>> resultList = new ArrayList<Optional<ClassificationResult<?>>>();
		schemaCrawlerService
		.getCatalogTables()
		.ifPresentOrElse(t -> {
			int count = t.size();
			Table[] tary = t.toArray(new Table[count]);
			for (int i = 0 ; i < count-1 ; i++) {
					ClassificationData cd1 = buildClassificationData (tary[i]);
						Optional<SecondNormalFormClassificationResult> csf2 = secondNormalFormTask.evaluate(cd1);
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

	
	@BeforeEach
	private void prepareTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        String sql = 
        		"create table IF NOT EXISTS  area (id bigint generated by default as identity, area_id bigint not null, name VARCHAR NOT NULL not null, gid UUID NOT NULL not null, areaType_id bigint not null, primary key (id));";
        String sql2 = 
        		"create table IF NOT EXISTS  area_begin_date_join (id bigint generated by default as identity , date_id bigint not null , area_id bigint not null, primary key (id));"; 
        String sql3 = 
        		"create table IF NOT EXISTS begin_dates (class_name VARCHAR NOT NULL not null, id bigint generated by default as identity, day SMALLINT NOT NULL not null, month SMALLINT NOT NULL not null, year SMALLINT NOT NULL not null, primary key (id));";
       String sql4 = 
        		"alter table area_begin_date_join add constraint  fk_bdates_date_id foreign key (date_id) references begin_dates;";
        String sql5 = "alter table area_begin_date_join add constraint  fk_area_area_id foreign key (area_id) references area;";
        String sql6 = "CREATE UNIQUE INDEX uk_area_begin_date_join ON public.area_begin_date_join (date_id, area_id);";

        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        stmt.executeUpdate(sql4);
        stmt.executeUpdate(sql5);
        stmt.executeUpdate(sql6);
        connection.close();
	} 
	
	@AfterEach
	private void closeTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        
        String sql = 
        		"DROP TABLE IF EXISTS area_begin_date_join CASCADE;"; 
        String sql2 = 
        		"DROP TABLE IF EXISTS begin_dates CASCADE;"; 
        String sql3 = 
        		"DROP TABLE IF EXISTS area CASCADE ;"; 
        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        connection.close();		
	}
		
}
