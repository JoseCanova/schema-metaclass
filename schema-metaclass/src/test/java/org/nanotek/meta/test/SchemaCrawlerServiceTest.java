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
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
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
			 assertTrue(ocl.get().size() > 0);
		 });
	}
	
	
	@Test
	void testSchemaCrawlerServiceCatalogClassificationMap() {
		 assertNotNull(schemaCrawlerService);
		 Optional<Collection<Table>> oTables = schemaCrawlerService.getCatalogTables();
		 Map <Table,ClassificationData> classificationMap = schemaCrawlerService.getCatalogClassificationMap(oTables);
		 assertTrue(classificationMap.keySet().size() == 3);
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
        		"alter table area_begin_date_join add constraint FK6ejxgqiu1outyxf4qd2xqjfmj foreign key (date_id) references begin_dates;";
        String sql5 = "alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area;";
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
        		"DROP TABLE IF EXISTS area  CASCADE;"; 
        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        connection.close();		
	}
	
}
