package com.example.pcserviceapi.services;

import com.example.pcserviceapi.models.GenericEntity;
import com.example.pcserviceapi.repositories.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public abstract class GenericService<T extends GenericEntity<T>> {

    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T findById(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public T update(T updated){
        T dbDomain = findById(updated.getId());
        dbDomain.update(updated);

        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(Long id){
        //check if object with this id exists
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}