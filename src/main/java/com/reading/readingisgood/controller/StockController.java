package com.reading.readingisgood.controller;

import com.reading.readingisgood.model.stock.StockDto;
import com.reading.readingisgood.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {
    private StockService stockService;

    @ApiOperation("Adding new stock or update existing endpoint")
    @PostMapping("/add")
    public ResponseEntity<Integer> addStock(@RequestBody @Valid StockDto stockRequest) {
        return ResponseEntity.ok(this.stockService.addStock(stockRequest));
    }
}
