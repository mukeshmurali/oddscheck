package com.example.oddscheck.repository;

import com.example.oddscheck.Bet;
import com.example.oddscheck.model.Odd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OddsRepository extends JpaRepository<Odd,Integer> {
}
