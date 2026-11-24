package com.dss.practica1.service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dss.practica1.model.OrderItem;
import com.dss.practica1.model.OrderRequest;
import com.dss.practica1.model.OrderResponse;
import com.dss.practica1.model.Producto;

@Service
public class OrderService {
	
	private final ProductService productService;
    private final AtomicLong orderSequence = new AtomicLong(1000);

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    public OrderResponse checkout(OrderRequest request) {
        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Order must contain at least one item");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : request.getItems()) {
            if (item == null || item.getProductId() == null || item.getQuantity() < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order item");
            }

            Producto product = productService.getProductById(item.getProductId());
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Invalid product ID: " + item.getProductId());
            }

            BigDecimal price = BigDecimal.valueOf(product.getPrecio());
            BigDecimal subtotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(subtotal);
        }

        long orderId = orderSequence.incrementAndGet();
        return new OrderResponse(orderId, "CONFIRMED", total);
    }

}
