package org.nanotek.meta.repository;

import org.nanotek.meta.model.MetaIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaIdentityRepository 
extends JpaRepository<MetaIdentity, String> {

}
