package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Column;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;

@SpringBootTest
public class SchemaCrawlerServiceTest {

	
	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	@Test
	void testSchemaCrawlerService() throws SQLException {
		 assertNotNull(schemaCrawlerService);
		 Optional<Collection<Table>> oTables = schemaCrawlerService.getCatalogTables();
		 assertFalse(oTables.equals(Optional.empty()));
		 oTables.get().forEach(t -> {
			 Optional<List<Column>>ocl = schemaCrawlerService.getTableColumns(Optional.of(t));
			 assertTrue(ocl.get().size() == 0);
			 Optional<PrimaryKey> opk = schemaCrawlerService.getTablePrimaryKey(Optional.of(t));
			 assertTrue(opk.equals(Optional.empty()));
		 });
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
