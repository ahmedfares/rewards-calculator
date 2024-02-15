package com.example.rewards.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Rewards {
    private Map<Month, Integer> rewardsPerMonth;
    private int totalRewards;
}
