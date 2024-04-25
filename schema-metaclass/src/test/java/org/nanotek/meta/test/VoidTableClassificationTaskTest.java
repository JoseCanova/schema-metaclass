package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.classification.task.VoidTableClassificationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VoidTableClassificationTaskTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	VoidTableClassificationTask voidTableClassificationTask;
	
	public VoidTableClassificationTaskTest() {
	}

	@Test
	void testVoidTableClassificationInjection() throws SQLException {
		assertNotNull(voidTableClassificationTask);
		prepareTest();
		closeTest();
	}
	
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
