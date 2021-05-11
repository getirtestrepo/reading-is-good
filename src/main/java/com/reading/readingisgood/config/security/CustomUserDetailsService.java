package com.reading.readingisgood.config.security;

import com.reading.readingisgood.model.entity.UserEntity;
import com.reading.readingisgood.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        UserEntity mobileNumber = (UserEntity)this.userRepository.findByMobileNumber(Long.valueOf(value))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number : " + value));
        return createPrincipal(mobileNumber);
    }

    private UserDetails createPrincipal(UserEntity userEntity) {
        UserPrincipal userPrincipal = UserPrincipal.constructUserPrincipal(userEntity);
        return (UserDetails)userPrincipal;
    }

    public UserPrincipal loadUserByMobileNumber(Long mobileNumber) {
        UserEntity entity = (UserEntity)this.userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number : " + mobileNumber));
        return UserPrincipal.constructUserPrincipal(entity);
    }
}