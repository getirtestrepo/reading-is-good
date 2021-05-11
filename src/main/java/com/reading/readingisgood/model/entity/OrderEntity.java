package com.reading.readingisgood.model.entity;

import com.reading.readingisgood.model.order.OrderStatus;
import com.reading.readingisgood.model.user.CustomerDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private String id;
    private String orderName;
    private String orderCode;
    private Long orderPrice;
    private CustomerDetailDto buyer;
    private Date orderDate;
    private OrderStatus orderStatus;
}
