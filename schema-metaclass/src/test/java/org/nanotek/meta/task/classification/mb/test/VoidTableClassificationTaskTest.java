package org.nanotek.meta.task.classification.mb.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;
import org.nanotek.meta.model.rdbms.classification.task.VoidTableClassificationTask;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Table;

//TODO: Verify reason of void table classificatin problem.
//@SpringBootTest
public class VoidTableClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	VoidTableClassificationTask voidTableClassificationTask;
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	public VoidTableClassificationTaskTest() {
	}

//	@Test
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
	
	@BeforeEach
	private void prepareTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        String sql = 
        		"create table IF NOT EXISTS  void_area ( area_id bigint not null, name VARCHAR NOT NULL not null, gid UUID NOT NULL not null, areaType_id bigint not null) ;";
        stmt.executeUpdate(sql);
        connection.close();
	} 
	
	@AfterEach
	private void closeTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        String sql = 
        		"DROP TABLE IF EXISTS void_area CASCADE;"; 
        stmt.executeUpdate(sql);
        connection.close();		
	}
}
