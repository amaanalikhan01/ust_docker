package com.myportfolio.mutualfundsms.repository;

import com.myportfolio.mutualfundsms.model.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MutualFundRepository extends JpaRepository<MutualFund,Integer> {
}
