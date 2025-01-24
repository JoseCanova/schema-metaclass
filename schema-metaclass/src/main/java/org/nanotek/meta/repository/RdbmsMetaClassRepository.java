package org.nanotek.meta.repository;

import java.util.List;

import org.nanotek.MetaRepository;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;

public interface RdbmsMetaClassRepository 
extends MetaRepository<RdbmsMetaClass , String> {
	public List<RdbmsMetaClass> findByClassName(String className);
}
