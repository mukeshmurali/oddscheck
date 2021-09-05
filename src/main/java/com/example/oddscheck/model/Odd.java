package com.example.oddscheck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Odd {
    private String oddsValue;

    private Integer oddsId;

    public Odd(String oddsValue, Integer oddsId) {
        this.oddsValue = oddsValue;
        this.oddsId = oddsId;
    }

    public Odd(String oddsValue) {
        this.oddsValue = oddsValue;
    }

    public Odd() {
    }

    public String getOddsValue() {
        return oddsValue;
    }

    public void setOddsValue(String oddsValue) {
        this.oddsValue = oddsValue;
    }

    public void setId(Integer id) {
        this.oddsId = id;
    }

    @Id
    public Integer getId() {
        return oddsId;
    }
}
