package com.reading.readingisgood.service;

import com.reading.readingisgood.config.security.JwtTokenProvider;
import com.reading.readingisgood.exception.OrderNotFoundException;
import com.reading.readingisgood.exception.OutOfStockException;
import com.reading.readingisgood.model.entity.OrderEntity;
import com.reading.readingisgood.model.entity.StockEntity;
import com.reading.readingisgood.model.entity.UserEntity;
import com.reading.readingisgood.model.order.OrderDetailDto;
import com.reading.readingisgood.model.order.OrderDto;
import com.reading.readingisgood.model.stock.StockDto;
import com.reading.readingisgood.model.user.CustomerDetailDto;
import com.reading.readingisgood.repository.OrderRepository;
import com.reading.readingisgood.repository.StockRepository;
import com.reading.readingisgood.repository.UserRepository;
import com.reading.readingisgood.util.Converter;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final Converter converter;
    private final JwtTokenProvider provider;

    @Transactional
    public String placeOrder(OrderDto orderRequest, String auth) {
        Optional<StockEntity> stockEntity = stockRepository.findAvailableStock(orderRequest.getOrderCode(), orderRequest.getOrderCount());
        if (stockEntity.isPresent()) {
            StockEntity stock = stockEntity.get();
            String token = provider.extractTokenFromAuthorizationHeader(auth);
            Long mobileNumber = provider.extractMobileNoFromToken(token);
            Optional<UserEntity> customer = userRepository.findByMobileNumber(mobileNumber);
            OrderEntity savedOrder = orderRepository.save(converter.toOrder(stock, orderRequest, customer.get()));
            Integer orderCount = orderRequest.getOrderCount();
            stock.setStockCount(stock.getStockCount() - orderRequest.getOrderCount());
            stockRepository.save(stock);
            return savedOrder.getId();
        } else {
            throw new OutOfStockException();
        }
    }

    public OrderDetailDto getOrder(String orderId) {
        return orderRepository.findById(orderId)
                .map(converter::toOrderDetailDto)
                .orElseThrow(() -> new OrderNotFoundException());
    }

    public List<OrderDetailDto> getCustomerOrders(String auth) {
        String token = provider.extractTokenFromAuthorizationHeader(auth);
        Long mobileNumber = provider.extractMobileNoFromToken(token);
        List<OrderEntity> orderList =  orderRepository.findByCustomerOrder(mobileNumber);
        if (Collections.isEmpty(orderList)) {
            throw new OrderNotFoundException();
        } else {
            return orderList.stream()
                    .map(odto -> OrderDetailDto.builder()
                            .orderName(odto.getOrderName())
                            .orderCode(odto.getOrderCode())
                            .orderDate(odto.getOrderDate())
                            .orderPrice(odto.getOrderPrice())
                            .orderStatus(odto.getOrderStatus())
                            .id(odto.getId()).build())
                    .collect(Collectors.toList());
        }

    }
}
