package com.martikan.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base interface for repositories.
 * @param <T> - Entity.
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
}