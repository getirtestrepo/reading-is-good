package com.reading.readingisgood.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum UserStatus {
    ACTIVE(1),
    BLOCKED(2);

    private Integer status;

    public static UserStatus fromValue(int status) {
        for (UserStatus userStatus : values()) {
            if (Objects.equals(userStatus.getStatus(), Integer.valueOf(status)))
                return userStatus;
        }
        throw new RuntimeException("Unknown user status for value : " + status);
    }
}
