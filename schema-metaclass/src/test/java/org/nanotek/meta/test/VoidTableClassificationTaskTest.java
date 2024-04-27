package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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


//TODO:finish this test.
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
		schemaCrawlerService
		.getCatalogTables()
		.ifPresent(lt -> lt.forEach(t -> {
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
		assertTrue(1 == 1);
	}
	
	@BeforeEach
	private void prepareTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        String sql = 
        		"CREATE TABLE IF NOT EXISTS accounts (\r\n"
        		+ ");"; 
        stmt.executeUpdate(sql);
        connection.close();
	} 
	
	@AfterEach
	private void closeTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        String sql = 
        		"DROP TABLE IF EXISTS accounts;"; 
        stmt.executeUpdate(sql);
        connection.close();		
	}

}
