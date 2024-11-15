package com.myportfolio.portfolioms.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class MutualFund {
    private int id;
    private String fundName;
    private double amountInvested;
    private double growthRate;

}
