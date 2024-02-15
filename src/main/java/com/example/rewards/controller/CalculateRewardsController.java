package com.example.rewards.controller;

import com.example.rewards.model.Customer;
import com.example.rewards.model.Rewards;
import com.example.rewards.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateRewardsController {

    private final RewardService rewardService;

    public CalculateRewardsController(RewardService rewardService) {
        this.rewardService = rewardService;
    }


    @PostMapping("/rewards")
    public ResponseEntity<Rewards> getRewardsByCustomer (@RequestBody Customer customer) {
        return ResponseEntity.ok(rewardService.calculateCustomerRewards(customer));
    }
}
