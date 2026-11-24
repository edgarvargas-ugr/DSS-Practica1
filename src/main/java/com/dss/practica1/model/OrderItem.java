package com.dss.practica1.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItem {
	
	@NotNull
    private Long productId;

    @Min(1)
    private int quantity;

    public OrderItem() {}

    public OrderItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}
