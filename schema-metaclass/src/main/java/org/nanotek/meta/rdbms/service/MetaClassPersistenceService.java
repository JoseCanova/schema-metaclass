package org.nanotek.meta.rdbms.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.nanotek.MetaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public class MetaClassPersistenceService<R extends MetaRepository<T , ID> , T , ID> {

	protected R repository;
	
	public MetaClassPersistenceService(R repository) {
		this.repository = repository;
	}

	public void delete(T entity) {
		repository.delete(entity);
	}

	public <S extends T> S save(S entity) {
		return repository.save(entity);
	}

	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		return repository.saveAll(entities);
	}

	public <S extends T> Optional<S> findOne(Example<S> example) {
		return repository.findOne(example);
	}

	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	public void flush() {
		repository.flush();
	}

	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public <S extends T> S saveAndFlush(S entity) {
		return repository.saveAndFlush(entity);
	}

	public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
		return repository.saveAllAndFlush(entities);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public List<T> findAllById(Iterable<ID> ids) {
		return repository.findAllById(ids);
	}

	public void deleteInBatch(Iterable<T> entities) {
		repository.deleteInBatch(entities);
	}

	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}

	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	public void deleteAllInBatch(Iterable<T> entities) {
		repository.deleteAllInBatch(entities);
	}

	public boolean existsById(ID id) {
		return repository.existsById(id);
	}

	public <S extends T> long count(Example<S> example) {
		return repository.count(example);
	}

	public void deleteAllByIdInBatch(Iterable<ID> ids) {
		repository.deleteAllByIdInBatch(ids);
	}

	public <S extends T> boolean exists(Example<S> example) {
		return repository.exists(example);
	}

	public void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}

	public T getOne(ID id) {
		return repository.getOne(id);
	}

	public <S extends T, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return repository.findBy(example, queryFunction);
	}

	public long count() {
		return repository.count();
	}

	public void deleteById(ID id) {
		repository.deleteById(id);
	}

	public T getById(ID id) {
		return repository.getById(id);
	}

	public void deleteAllById(Iterable<? extends ID> ids) {
		repository.deleteAllById(ids);
	}

	public T getReferenceById(ID id) {
		return repository.getReferenceById(id);
	}

	public void deleteAll(Iterable<? extends T> entities) {
		repository.deleteAll(entities);
	}

	public <S extends T> List<S> findAll(Example<S> example) {
		return repository.findAll(example);
	}

	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return repository.findAll(example, sort);
	}

	public void deleteAll() {
		repository.deleteAll();
	}
	
	
}
