package com.martikan.carrental.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Abstract entity class.
 */
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -8359600363538220054L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}