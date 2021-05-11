package com.reading.readingisgood.model.order;

import com.reading.readingisgood.model.user.ApiResponse;
import com.reading.readingisgood.model.user.CustomerDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class OrderDetailDto extends ApiResponse {
    private String id;
    private String orderName;
    private String orderCode;
    private Long orderPrice;
    private Date orderDate;
    private OrderStatus orderStatus;
}
