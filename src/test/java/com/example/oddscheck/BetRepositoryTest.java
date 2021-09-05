package com.example.oddscheck;

import com.example.oddscheck.model.Odd;
import com.example.oddscheck.repository.BetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BetRepositoryTest {

    @Autowired
    private BetRepository betRepository;

    @Test
    public void findOddsByBetId_returns_betWithOdds() {
        //arrange
        Odd odd1=new Odd("1/10",1);
        Odd odd2=new Odd("2/1",2);
        Odd odd3=new Odd("SP",3);
        List<Odd> odds= Arrays.asList(odd1,odd2,odd3);
        betRepository.save(new Bet(odds,1,null));

        Bet bet = betRepository.findById(1);
        assertThat(bet.getId()).isEqualTo(1);
        assertThat(bet.getOdds().size()).isEqualTo(odds.size());
    }
}
