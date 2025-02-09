package com.assignment.onlineShop.repository.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@Builder
@AllArgsConstructor
public class WebUser implements UserDetails {

    private final String username;
    private final String password;
    private final String fullName;
    private final String phoneNumber;
    private final LocalDate birthday;
    private String role;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<ShopOrder> shopOrders;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Address> addresses;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public WebUser(String username, String password, String fullName, String phoneNumber, LocalDate birthday, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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