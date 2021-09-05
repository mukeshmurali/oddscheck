package com.example.oddscheck.service;

import com.example.oddscheck.Bet;
import com.example.oddscheck.repository.BetRepository;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    private BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Bet findOddsByBetId(Integer id) {
        if(id <0){
            throw new InvalidBetIDException("Invalid format of odds");
        }
        Bet bet = betRepository.findById(id).orElse(null);
        if(bet==null){
            throw new BetNotFoundException();
        }
        bet.setDescription("Odds are returned for bet ID");
        return bet;
    }

    public Bet createOddsForNewBet(Bet bet) {
        Bet b = betRepository.save(bet);
        b.setDescription("Odds have been created for bet");
        return b;
    }
}
