package com.example.rewards.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Customer {
    private int id;
    private List<Transaction> transactions;
}
