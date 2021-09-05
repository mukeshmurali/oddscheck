package com.example.oddscheck.controller;

import com.example.oddscheck.Bet;
import com.example.oddscheck.service.BetNotFoundException;
import com.example.oddscheck.service.BetService;
import com.example.oddscheck.service.InvalidBetIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BetsController {

    @Autowired
    private BetService betService;

    @GetMapping("/odds/{id}")
    private Bet findOddsByBetId(@PathVariable String id){
        return betService.findOddsByBetId(Integer.parseInt(id));
    }


    @PostMapping("/odds")
    @ResponseStatus(HttpStatus.CREATED)
    private Bet createOddsForNewBet(@RequestBody Bet bet){
        return betService.createOddsForNewBet(bet);
    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String betNotFoundHandler(BetNotFoundException ex){
        return "Bet not found for given ID";
    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String invalidBetIdHandler(InvalidBetIDException ex){
        return "Invalid Bet ID supplied";
    }

}
