package com.example.rewards.service;

import com.example.rewards.model.Customer;
import com.example.rewards.model.Month;
import com.example.rewards.model.Rewards;
import com.example.rewards.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RewardService {

    public Rewards calculateCustomerRewards(Customer customer) {
        Map<Month, Integer> rewardsPerMonth = new HashMap<>();
        int total = 0;
        for(Transaction transaction: customer.getTransactions()) {
            int rewards = calculateRewards(transaction);
            rewardsPerMonth.put(transaction.getMonth(), rewardsPerMonth.getOrDefault(transaction.getMonth(), 0) + rewards);
            total += rewards;
        }
        return Rewards.builder().rewardsPerMonth(rewardsPerMonth).totalRewards(total).build();
    }

    public int calculateRewards(Transaction transaction) {
        int amount = transaction.getAmount();
        int points = 0;
        points += (amount > 100)?2*(amount-100):0;
        points += (amount > 100)?50:(amount>50)?amount-50:0;
        return points;
    }
}
