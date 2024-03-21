package com.example.dabbaWalaWebsite.security;

import com.example.dabbaWalaWebsite.entity.RestaurantOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantOwnerDetailsImpl implements UserDetails {
    @Autowired
    private RestaurantOwner restaurantOwner;
    private List<GrantedAuthority> authorities;
    public RestaurantOwnerDetailsImpl(RestaurantOwner restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
        this.authorities = Arrays.stream(restaurantOwner.getRole().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return restaurantOwner.getPassword();
    }

    @Override
    public String getUsername() {
        return restaurantOwner.getUsername();
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
