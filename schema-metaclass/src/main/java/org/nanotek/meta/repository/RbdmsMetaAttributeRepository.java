package org.nanotek.meta.repository;

import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RbdmsMetaAttributeRepository 
extends JpaRepository<RdbmsMetaClassAttribute, String> {
}
