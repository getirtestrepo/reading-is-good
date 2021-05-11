package com.reading.readingisgood.util;

import com.reading.readingisgood.model.entity.OrderEntity;
import com.reading.readingisgood.model.entity.StockEntity;
import com.reading.readingisgood.model.entity.UserEntity;
import com.reading.readingisgood.model.order.OrderDetailDto;
import com.reading.readingisgood.model.order.OrderDto;
import com.reading.readingisgood.model.order.OrderStatus;
import com.reading.readingisgood.model.user.CustomerDetailDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Converter {

    public CustomerDetailDto toCustomerDetailDto(UserEntity userEntity) {
        return CustomerDetailDto.builder()
                .customerName(userEntity.getName())
                .customerLastName(userEntity.getLastName())
                .address(userEntity.getAddress())
                .age(userEntity.getAge())
                .mobileNumber(userEntity.getMobileNumber())
                .build();
    }

    public OrderEntity toOrder(StockEntity stock, OrderDto orderRequest, UserEntity userEntity) {
        return OrderEntity.builder()
                .orderDate(new Date())
                .orderName(stock.getStockName())
                .orderStatus(OrderStatus.INITIAL)
                .buyer(toCustomerDetailDto(userEntity))
                .orderPrice(stock.getStockPrice() * orderRequest.getOrderCount())
                .orderCode(stock.getStockCode())
                .build();
    }

    public OrderDetailDto toOrderDetailDto(OrderEntity orderEntity) {
        return OrderDetailDto
                .builder()
                .id(orderEntity.getId())
                .orderName(orderEntity.getOrderName())
                .orderDate(orderEntity.getOrderDate())
                .orderCode(orderEntity.getOrderCode())
                .orderPrice(orderEntity.getOrderPrice())
                .orderStatus(orderEntity.getOrderStatus())
                .build();
    }
}
