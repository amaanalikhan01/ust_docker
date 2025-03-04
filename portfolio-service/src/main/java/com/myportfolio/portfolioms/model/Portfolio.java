package com.myportfolio.portfolioms.model;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Portfolio {
    @Id
    private String id;

    private String name;

    @ElementCollection
    private Set<Integer> stocks = new HashSet<>();

    @ElementCollection
    private Set<Integer> MutualFunds = new HashSet<>();

    @Transient
    private double totalValue;

}
