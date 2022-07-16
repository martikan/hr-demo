package com.martikan.carrental.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.martikan.carrental.domain.user.User;

import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * Custom UserDetails class.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private static final long serialVersionUID = 9059447104676210512L;

    @EqualsAndHashCode.Include
    private final Long id;

    private final String username;

    private final Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private final String password;

    public UserDetails(Long id, String username, Collection<? extends GrantedAuthority> authorities, String password) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
        this.password = password;
    }

    public UserDetails(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.authorities = user.getRoles();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}