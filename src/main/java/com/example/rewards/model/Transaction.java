package com.example.rewards.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
    private int amount;
    private Month month;
}
