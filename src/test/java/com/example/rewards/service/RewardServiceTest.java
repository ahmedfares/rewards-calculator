package com.example.rewards.service;

import com.example.rewards.model.Customer;
import com.example.rewards.model.Month;
import com.example.rewards.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RewardServiceTest {

    @Autowired
    private RewardService rewardService;

    @Test
    public void testCalculateRewardPoints() {
            // Test case: $120 purchase
            int amount1 = 120;
            int expectedPoints1 = 90; // 2*20 + 1*50 = 90 points
            Transaction transaction1 = Transaction.builder().amount(amount1).build();
            assertEquals(expectedPoints1, rewardService.calculateRewards(transaction1));

            // Test case: $80 purchase
            int amount2 = 80;
            int expectedPoints2 = 30; // 1*30 = 30 points
            Transaction transaction2 = Transaction.builder().amount(amount2).build();
            assertEquals(expectedPoints2, rewardService.calculateRewards(transaction2));

            // Test case: $30 purchase
            int amount3 = 30;
            Transaction transaction3 = Transaction.builder().amount(amount3).build();
            int expectedPoints3 = 0; // No points for purchases under $50
            assertEquals(expectedPoints3, rewardService.calculateRewards(transaction3));
    }

    @Test
    public void testCalculateRewardPointsByCustomer() {

        int amount1 = 111;
        int amount2 = 51;
        int amount3 = 120;
        int expectedPoints = 163;
        Transaction transaction1 = Transaction.builder().amount(amount1).month(Month.April).build();
        Transaction transaction2 = Transaction.builder().amount(amount2).month(Month.April).build();
        Transaction transaction3 = Transaction.builder().amount(amount3).month(Month.February).build();
        Customer customer = Customer.builder().transactions(List.of(transaction1, transaction2, transaction3)).build();
        assertEquals(expectedPoints, rewardService.calculateCustomerRewards(customer).getTotalRewards());
    }
}
