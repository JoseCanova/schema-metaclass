package org.nanotek.meta.task.classification.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.classification.task.FirstNormalFormClassificationTask;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import schemacrawler.schema.Table;

//TODO: Finish task and unit test.
@SpringBootTest
public class FirstNormalFormClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Autowired
	FirstNormalFormClassificationTask theTask;
	
	public FirstNormalFormClassificationTaskTest() {
	}

	@Test
	void testVoidTableClassificationInjection() throws SQLException {
		assertNotNull(schemaCrawlerService);
		List<Optional<ClassificationResult>> resultList = new ArrayList<Optional<ClassificationResult>>();
		schemaCrawlerService
		.getCatalogTables()
		.ifPresentOrElse(t -> {
			int count = t.size();
			Table[] tary = t.toArray(new Table[count]);
			for (int i = 0 ; i < count-1 ; i++) {
				for (int j = i + 1 ; j < count ; ++j) {
					System.out.println(" " + tary[i].getName() + "  " + tary[j].getName());
					ClassificationData cd1 = buildClassificationData (tary[i]);
					ClassificationData cd2 = buildClassificationData (tary[j]);
					ClassificationDataPair cdp = new ClassificationDataPair(Pair.of(cd1,cd2)) ;
					Optional<ClassificationResult> cr = theTask.evaluate(cdp);
					resultList.add(cr);
				}
			}
			}, new Runnable() {
			@Override
			public void run() {
				throw new RuntimeException("Problem on Test");
			}
		});
		assertTrue(resultList.size() == schemaCrawlerService
				.getCatalogTables().get().size());
	}
	
	private ClassificationData buildClassificationData(Table table) {
		return new ClassificationData(
				new TableKey(Optional.ofNullable(table.getPrimaryKey())),
				new TableColumns(Optional.ofNullable(table.getColumns())),
				new TableForeignKeys(Optional.ofNullable(table.getForeignKeys()))
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
        		"create table IF NOT EXISTS  area_begin_date_join (date_id bigint, area_id bigint not null, primary key (area_id));"; 
        String sql3 = 
        		"create table IF NOT EXISTS begin_dates (class_name VARCHAR NOT NULL not null, id bigint generated by default as identity, day SMALLINT NOT NULL not null, month SMALLINT NOT NULL not null, year SMALLINT NOT NULL not null, primary key (id));";
       String sql4 = 
        		"alter table area_begin_date_join add constraint  FK6ejxgqiu1outyxf4qd2xqjfmj foreign key (date_id) references begin_dates;";
        String sql5 = "alter table area_begin_date_join add constraint  FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area;";
        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        stmt.executeUpdate(sql4);
        stmt.executeUpdate(sql5);
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
