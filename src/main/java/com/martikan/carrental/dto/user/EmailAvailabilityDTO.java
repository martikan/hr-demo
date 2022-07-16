package com.martikan.carrental.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO to send email availability.
 */
@Builder
@Data
@EqualsAndHashCode
public class EmailAvailabilityDTO implements Serializable {

    private static final long serialVersionUID = 8775368935049642738L;

    @NotNull
    private boolean emailAvailable;

}