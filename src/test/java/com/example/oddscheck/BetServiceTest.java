package com.example.oddscheck;

import com.example.oddscheck.model.Odd;
import com.example.oddscheck.repository.BetRepository;
import com.example.oddscheck.service.BetNotFoundException;
import com.example.oddscheck.service.BetService;
import com.example.oddscheck.service.InvalidBetIDException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BetServiceTest {

    @Mock
    private BetRepository betRepository;

    private BetService betService;

    @Before
    public void setUp() throws Exception {
        betService=new BetService(betRepository);
    }

    @Test
    public void findOddsByBetId_returns_betWithOdds_and_description() {
        //arrange
        Odd odd1=new Odd("1/10",1);
        Odd odd2=new Odd("2/1",2);
        Odd odd3=new Odd("SP",3);
        List<Odd> odds= Arrays.asList(odd1,odd2,odd3);

        given(betRepository.findById(anyInt())).willReturn(new Bet(odds,1, null));
        //act
        Bet bet = betService.findOddsByBetId(1);
        //assert
        assertThat(bet.getId()).isEqualTo(1);
        assertThat(bet.getOdds()).isEqualTo(odds);
        assertThat(bet.getDescription()).isEqualTo("Odds are returned for bet ID");
    }


    @Test(expected = BetNotFoundException.class)
    public void findOddsByBetId_returns_notFound() {
        //arrange
        given(betRepository.findById(anyInt())).willReturn(null);

        //act and assert
        betService.findOddsByBetId(10);
    }

    @Test(expected = InvalidBetIDException.class)
    public void findOddsByBetId_returns_InvalidBetId() {
    //act and assert
        betService.findOddsByBetId(-1);
    }

    @Test
    public void offerOddsForBet_createNewBet() {
        //arrange
        //arrange
        Odd odd1=new Odd("1/10");
        Odd odd2=new Odd("2/1");
        Odd odd3=new Odd("SP");
        ObjectMapper objectMapper = new ObjectMapper();

        List<Odd> odds= Arrays.asList(odd1,odd2,odd3);
        given(betRepository.save(any()))
                .willReturn(new Bet(odds,10, null));

        //act
        Bet bet = betService.createOddsForNewBet(new Bet(odds, 10, null));

        //assert
        assertThat(bet.getId()).isEqualTo(10);
        assertThat(bet.getOdds()).isEqualTo(odds);
        assertThat(bet.getDescription()).isEqualTo("Odds have been created for bet");
    }
}
