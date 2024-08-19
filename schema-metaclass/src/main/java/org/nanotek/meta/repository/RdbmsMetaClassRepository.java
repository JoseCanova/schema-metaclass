package org.nanotek.meta.repository;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RdbmsMetaClassRepository extends MongoRepository<RdbmsMetaClass , String> {
}
