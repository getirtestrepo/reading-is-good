package com.reading.readingisgood.service;

import com.reading.readingisgood.exception.UserIsExistException;
import com.reading.readingisgood.model.entity.UserEntity;
import com.reading.readingisgood.model.user.RegisterRequest;
import com.reading.readingisgood.model.user.UserStatus;
import com.reading.readingisgood.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long registerUser(RegisterRequest registerRequest) {
        assertUserIsNotExist(registerRequest.getMobileNumber());
        UserEntity userEntity =UserEntity.builder()
                .mobileNumber(registerRequest.getMobileNumber())
                .name(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .address(registerRequest.getAddress())
                .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                .age(registerRequest.getAge())
                .status(UserStatus.ACTIVE.getStatus())
                .createdDate(DateTime.now().toDate()).
                        build();
        UserEntity savedUser = (UserEntity)this.userRepository.save(userEntity);
        log.info("User with phone number : {} is saved", savedUser.getMobileNumber());
        return savedUser.getMobileNumber();
    }

    private void assertUserIsNotExist(Long phone) {
        this.userRepository
                .findByMobileNumber(phone)
                .ifPresent(userEntity -> {
                    throw new UserIsExistException();
                });
    }
}
