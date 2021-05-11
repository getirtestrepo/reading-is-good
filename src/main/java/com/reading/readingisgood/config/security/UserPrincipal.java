package com.reading.readingisgood.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Collections;

import com.reading.readingisgood.model.entity.UserEntity;
import com.reading.readingisgood.model.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserPrincipal implements UserDetails {
    private String id;
    private String userName;
    @JsonIgnore
    private Long mobileNumber;
    @JsonIgnore
    private String password;
    private UserStatus userStatus;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal constructUserPrincipal(UserEntity userEntity) {
        return builder()
                .id(userEntity.getId())
                .mobileNumber(userEntity.getMobileNumber())
                .password(userEntity.getPassword())
                .userName(userEntity.getName())
                .userStatus(UserStatus.fromValue(userEntity.getStatus().intValue()))
                .authorities(Collections.emptyList())
                .build();
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

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

}
