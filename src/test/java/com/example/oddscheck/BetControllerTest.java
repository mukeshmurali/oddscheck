package com.example.oddscheck;

import com.example.oddscheck.controller.BetsController;
import com.example.oddscheck.model.Odd;
import com.example.oddscheck.service.BetNotFoundException;
import com.example.oddscheck.service.BetService;
import com.example.oddscheck.service.InvalidBetIDException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BetsController.class)
public class BetControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BetService betService;

    @Test
    public void findOddsByBetId_returns_betWithOdds_and_description() throws Exception {
        //arrange
        Odd odd1 = new Odd("1/10");
        Odd odd2 = new Odd("2/1");
        Odd odd3 = new Odd("SP");

        List<Odd> odds = Arrays.asList(odd1, odd2, odd3);
        given(betService.findOddsByBetId(anyInt())).willReturn(new Bet(odds, 1, "Odds are returned for bet ID"));

        //act //assert
        mockMvc.perform(MockMvcRequestBuilders.get("/odds/1")).andExpect(status().isOk()).andExpect(jsonPath("id").value(1)).andExpect(jsonPath("description").value("Odds are returned for bet ID"));
    }

    @Test
    public void findOddsByBetId_returns_notFound() throws Exception {
        //arrange
        given(betService.findOddsByBetId(anyInt())).willThrow(new BetNotFoundException());

        //act //assert
        mockMvc.perform(MockMvcRequestBuilders.get("/odds/10")).andExpect(status().isNotFound()).andExpect(content().string("Bet not found for given ID"));
    }


    @Test
    public void findOddsByBetId_returns_InvalidBetId() throws Exception {
        //arrange
        given(betService.findOddsByBetId(anyInt())).willThrow(new InvalidBetIDException("Invalid Bet ID supplied"));

        //act //assert
        mockMvc.perform(MockMvcRequestBuilders.get("/odds/-1")).andExpect(status().isBadRequest()).andExpect(content().string("Invalid Bet ID supplied"));
    }

    @Test
    public void offerOddsForBet_createNewBet() throws Exception {
        //arrange
        Odd odd1 = new Odd("1/10");
        Odd odd2 = new Odd("2/1");
        Odd odd3 = new Odd("SP");
        ObjectMapper objectMapper = new ObjectMapper();

        List<Odd> odds = Arrays.asList(odd1, odd2, odd3);
        given(betService.createOddsForNewBet(any())).willReturn(new Bet(odds, 10, "Odds have been created for bet"));

        //act //assert
        mockMvc.perform(MockMvcRequestBuilders.post("/odds").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new Bet(odds, 10, "Odds have been created for bet")))).andExpect(status().isCreated()).andExpect(jsonPath("description").value("Odds have been created for bet"));
    }
}
