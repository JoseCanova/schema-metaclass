package org.nanotek.meta.controller;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.repository.MetaClassRepository;

public interface MetaClassController<T extends MetaClassRepository<B>, B extends MetaClass<B,?,?>>
extends RepositoryController<T , B> 
{
}
