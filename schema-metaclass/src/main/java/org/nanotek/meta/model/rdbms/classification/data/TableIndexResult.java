package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import schemacrawler.schema.Index;

public record TableIndexResult(IndexTypeEnum indexType , Map<String,List<Index>> columnsIndexResult) implements Map<String , List<Index>> {

	public TableIndexResult(IndexTypeEnum indexType , Map<String,List<Index>> columnsIndexResult) {
		this.indexType=indexType;
		this.columnsIndexResult= Collections.unmodifiableMap(columnsIndexResult);
	}

	@Override
	public int size() {
		return columnsIndexResult.size();
	}

	@Override
	public boolean isEmpty() {
		return columnsIndexResult.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return columnsIndexResult.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return columnsIndexResult.containsKey(value);
	}

	@Override
	public List<Index> get(Object key) {
		return columnsIndexResult.get(key);
	}

	@Override
	public List<Index> put(String key, List<Index> value) {
		return null;
	}

	@Override
	public List<Index> remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends List<Index>> m) {
		
	}

	@Override
	public void clear() {
	}

	@Override
	public Set<String> keySet() {
		return  Collections.unmodifiableSet(columnsIndexResult.keySet());
	}

	@Override
	public Collection<List<Index>> values() {
		return Collections.unmodifiableCollection(columnsIndexResult.values());
	}

	@Override
	public Set<Entry<String, List<Index>>> entrySet() {
		return Collections.unmodifiableSet(columnsIndexResult.entrySet());
	}
	
}
