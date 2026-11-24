package com.dss.practica1.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class OrderRequest {
	
	@NotEmpty
    @Valid
    private List<OrderItem> items;

    public OrderRequest() {}

    public OrderRequest(List<OrderItem> items) { this.items = items; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

}
