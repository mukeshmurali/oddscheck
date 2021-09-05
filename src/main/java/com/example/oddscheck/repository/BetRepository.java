package com.example.oddscheck.repository;

import com.example.oddscheck.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet,Integer> {
    public Bet findById(int id);
    public Bet save(Bet bet);
}
