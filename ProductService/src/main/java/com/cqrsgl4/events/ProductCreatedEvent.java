package com.cqrsgl4.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductCreatedEvent {

    private String productId;
    private  String name;
    private BigDecimal price;
    private  Integer quantity;

}