package com.reading.readingisgood.controller;

import com.reading.readingisgood.constants.Constants;
import com.reading.readingisgood.model.user.ApiResponse;
import com.reading.readingisgood.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @ApiOperation("Returns customer detail")
    @GetMapping("/detail")
    public ResponseEntity<? extends ApiResponse> getUserDetail(
            @RequestHeader(name = Constants.AUTH_HEADER, required = true) String auth) {
        return ResponseEntity.ok(this.customerService
                .getDetails(auth));
    }
}
