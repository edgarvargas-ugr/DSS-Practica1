package com.dss.practica1.model;

import java.math.BigDecimal;

public class OrderResponse {
	
	private Long orderId;
    private String status;
    private BigDecimal total;

    public OrderResponse() {}

    public OrderResponse(Long orderId, String status, BigDecimal total) {
        this.orderId = orderId;
        this.status = status;
        this.total = total;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

}
