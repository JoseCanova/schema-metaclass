package org.nanotek.meta.repository;

import java.util.List;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RdbmsMetaClassRepository extends MongoRepository<RdbmsMetaClass , String> {
	public List<RdbmsMetaClass> findByClassName(String className);
}
