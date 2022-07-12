package com.sgveteris.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<SpendOrder> registerCustomer(@RequestBody OrderRequest orderRequest) {
        log.info("new order request {}", orderRequest);

        return new ResponseEntity<>(orderService.registerCustomer(orderRequest), HttpStatus.OK);
    }
}
