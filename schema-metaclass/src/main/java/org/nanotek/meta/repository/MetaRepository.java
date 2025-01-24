package org.nanotek.meta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaRepository<T, ID> extends JpaRepository<T, ID> {

}
