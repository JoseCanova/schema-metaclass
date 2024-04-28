package org.nanotek.meta.task.classification.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO: Finish task and unit test.
@SpringBootTest
public class FirstNormalFormClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	public FirstNormalFormClassificationTaskTest() {
	}

	@Test
	void testVoidTableClassificationInjection() throws SQLException {
		assertNotNull(schemaCrawlerService);
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
