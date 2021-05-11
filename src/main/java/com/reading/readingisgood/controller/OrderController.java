package com.reading.readingisgood.controller;

import com.reading.readingisgood.constants.Constants;
import com.reading.readingisgood.model.order.OrderDetailDto;
import com.reading.readingisgood.model.order.OrderDto;
import com.reading.readingisgood.model.user.ApiResponse;
import com.reading.readingisgood.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @ApiOperation("Order placement endpoint")
    @PostMapping("/buy")
    public ResponseEntity<String> placeOrder(@RequestBody @Valid OrderDto orderRequest,
                                             @RequestHeader(name = Constants.AUTH_HEADER, required = true) String auth) {
        return ResponseEntity.ok(this.orderService.placeOrder(orderRequest, auth));
    }

    @ApiOperation("Returns order details")
    @GetMapping("/get")
    public ResponseEntity<? extends ApiResponse> getOrderDetail(
            @RequestParam(name = "orderId", required = true) String orderId) {
        return ResponseEntity.ok(this.orderService.getOrder(orderId));
    }

    @ApiOperation("Returns all orders belonging to customer")
    @GetMapping("/get/customer")
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetail(
            @RequestHeader(name = Constants.AUTH_HEADER, required = true) String auth) {
        return ResponseEntity.ok(this.orderService.getCustomerOrders(auth));
    }
}
