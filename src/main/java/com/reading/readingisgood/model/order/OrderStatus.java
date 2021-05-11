package com.reading.readingisgood.model.order;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    INITIAL(0),
    SOLD(1),
    CANCELLED(2);

    private final Integer status;

    public static OrderStatus fromValue(int status) {
        for (OrderStatus orderStatus : values()) {
            if (Objects.equals(orderStatus.getStatus(), Integer.valueOf(status)))
                return orderStatus;
        }
        throw new RuntimeException("Unknown order status for value : " + status);
    }

}
