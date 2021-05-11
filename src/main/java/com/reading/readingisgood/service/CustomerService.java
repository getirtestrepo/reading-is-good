package com.reading.readingisgood.service;

import com.reading.readingisgood.config.security.JwtTokenProvider;
import com.reading.readingisgood.model.user.CustomerDetailDto;
import com.reading.readingisgood.repository.UserRepository;
import com.reading.readingisgood.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final JwtTokenProvider provider;
    private final UserRepository userRepository;
    private final Converter converter;

    public CustomerDetailDto getDetails(String auth) {
        String token = provider.extractTokenFromAuthorizationHeader(auth);
        Long mobileNumber = provider.extractMobileNoFromToken(token);
        log.debug("mobile number is {}", mobileNumber);
        CustomerDetailDto userEntity = userRepository.findByMobileNumber(mobileNumber).map(converter::toCustomerDetailDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile number : " + mobileNumber));
        return userEntity;
    }
}
