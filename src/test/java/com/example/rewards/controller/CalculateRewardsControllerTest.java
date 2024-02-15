package com.example.rewards.controller;

import com.example.rewards.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculateRewardsController.class)
@AutoConfigureMockMvc
public class CalculateRewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private RewardService rewardService;

    @Test
    public void testGetRewardsByCustomer() throws Exception {
        String jsonRequest = "{\n" +
                "  \"id\": 1,\n" +
                "  \"transactions\": [\n" +
                "    {\n" +
                "      \"amount\": 111,\n" +
                "      \"month\": \"January\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"amount\": 51,\n" +
                "      \"month\": \"January\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"amount\": 120,\n" +
                "      \"month\": \"February\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockMvc.perform(post("/rewards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("totalRewards").value(163)); // Assuming all transactions are for January

    }
}
