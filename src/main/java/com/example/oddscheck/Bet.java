package com.example.oddscheck;

import com.example.oddscheck.model.Odd;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bet {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @OneToMany(targetEntity = Odd.class, cascade = CascadeType.ALL)
    @JoinColumn(name="betodd_fk", referencedColumnName = "id")
    private List<Odd> odds;

    public Bet() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bet(List<Odd> odds, int id, String description) {
        this.odds = odds;
        this.id = id;
        this.description = description;
        if(isValidId(id))
            this.description=description;
    }

    private boolean isValidId(int id) {
        return true;
    }

    public List<Odd> getOdds() {
        return odds;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
