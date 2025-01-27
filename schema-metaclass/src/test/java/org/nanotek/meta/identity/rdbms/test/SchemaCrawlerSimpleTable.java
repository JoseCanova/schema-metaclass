package org.nanotek.meta.identity.rdbms.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import schemacrawler.schema.Column;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.Index;
import schemacrawler.schema.NamedObject;
import schemacrawler.schema.NamedObjectKey;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Privilege;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableConstraint;
import schemacrawler.schema.TableRelationshipType;
import schemacrawler.schema.TableType;
import schemacrawler.schema.Trigger;
import schemacrawler.schema.WeakAssociation;
import schemacrawler.schemacrawler.Identifiers;

public class SchemaCrawlerSimpleTable implements Table {

	public SchemaCrawlerSimpleTable() {
	}

	@Override
	public Schema getSchema() {
		return null;
	}

	@Override
	public void withQuoting(Identifiers identifiers) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NamedObjectKey key() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(NamedObject o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getAttribute(String name, T defaultValue) throws ClassCastException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAttribute(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> Optional<T> lookupAttribute(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void setAttribute(String name, T value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRemarks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRemarks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRemarks(String remarks) {
		// TODO Auto-generated method stub

	}

	@Override
	public TableType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasDefinition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<PrimaryKey> getAlternateKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Column> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ForeignKey> getExportedForeignKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ForeignKey> getForeignKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Column> getHiddenColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ForeignKey> getImportedForeignKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Index> getIndexes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrimaryKey getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Privilege<Table>> getPrivileges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Table> getRelatedTables(TableRelationshipType tableRelationshipType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TableConstraint> getTableConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableType getTableType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Trigger> getTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<WeakAssociation> getWeakAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasForeignKeys() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrimaryKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <A extends PrimaryKey> Optional<A> lookupAlternateKey(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <C extends Column> Optional<C> lookupColumn(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <F extends ForeignKey> Optional<F> lookupForeignKey(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <I extends Index> Optional<I> lookupIndex(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <P extends Privilege<Table>> Optional<P> lookupPrivilege(String name) {
		return Optional.empty();
	}

	@Override
	public <C extends TableConstraint> Optional<C> lookupTableConstraint(String name) {
		return Optional.empty();
	}

	@Override
	public <T extends Trigger> Optional<T> lookupTrigger(String name) {
		return Optional.empty();
	}

	@Override
	public String toString() {
		return "SchemaCrawlerSimpleTable [getSchema()=" + getSchema() + ", getFullName()=" + getFullName()
				+ ", getName()=" + getName() + ", key()=" + key() + ", getAttributes()=" + getAttributes()
				+ ", getRemarks()=" + getRemarks() + ", hasRemarks()=" + hasRemarks() + ", getType()=" + getType()
				+ ", getDefinition()=" + getDefinition() + ", hasDefinition()=" + hasDefinition()
				+ ", getAlternateKeys()=" + getAlternateKeys() + ", getColumns()=" + getColumns()
				+ ", getExportedForeignKeys()=" + getExportedForeignKeys() + ", getForeignKeys()=" + getForeignKeys()
				+ ", getHiddenColumns()=" + getHiddenColumns() + ", getImportedForeignKeys()="
				+ getImportedForeignKeys() + ", getIndexes()=" + getIndexes() + ", getPrimaryKey()=" + getPrimaryKey()
				+ ", getPrivileges()=" + getPrivileges() + ", getTableConstraints()=" + getTableConstraints()
				+ ", getTableType()=" + getTableType() + ", getTriggers()=" + getTriggers() + ", getWeakAssociations()="
				+ getWeakAssociations() + ", hasForeignKeys()=" + hasForeignKeys() + ", hasPrimaryKey()="
				+ hasPrimaryKey() + "]";
	}

	
	
}
