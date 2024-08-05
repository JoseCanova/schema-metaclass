package org.nanotek.meta.repository;

import org.nanotek.meta.model.MetaClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetaClassRepository<K extends MetaClass<K,?,?>> extends MongoRepository<MetaClass<K,?,?>, String> {

}
