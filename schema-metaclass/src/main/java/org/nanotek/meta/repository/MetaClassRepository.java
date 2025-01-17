package org.nanotek.meta.repository;

import org.nanotek.meta.model.MetaClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaClassRepository<K extends MetaClass<K,?,?>> extends JpaRepository<MetaClass<K,?,?>, String> {

}
