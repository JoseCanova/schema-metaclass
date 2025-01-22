package org.nanotek.meta.repository;

import java.util.List;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RdbmsMetaClassRepository 
extends JpaRepository<RdbmsMetaClass , String> {
	public List<RdbmsMetaClass> findByClassName(String className);
}
