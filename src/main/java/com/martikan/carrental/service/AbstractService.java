package com.martikan.carrental.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;

import com.martikan.carrental.aspect.IsAdmin;
import com.martikan.carrental.aspect.IsUser;
import com.martikan.carrental.exception.ResourceNotFoundException;
import com.martikan.carrental.mapper.BaseMapper;
import com.martikan.carrental.repository.BaseRepository;

import lombok.RequiredArgsConstructor;

/**
 * Abstract class for services.
 * @param <T> - Entity.
 * @param <D> - DTO.
 */
@IsUser
@Transactional
@RequiredArgsConstructor
public abstract class AbstractService<T, D> {

    private final BaseRepository<T> repository;

    private final BaseMapper<T, D> mapper;

    public List<D> findAll(final Pageable pageable) {
        return repository.findAll(pageable)
                .stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public D findById(final Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
    }

    @IsAdmin
    public D update(final Long id, final D dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing = mapper.updateEntity(mapper.toEntity(dto));
                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
    }

    @IsAdmin
    public D save(final D dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @IsAdmin
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

}