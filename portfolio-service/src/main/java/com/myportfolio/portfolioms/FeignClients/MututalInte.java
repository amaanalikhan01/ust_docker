package com.myportfolio.portfolioms.FeignClients;

import com.myportfolio.portfolioms.dto.MutualFund;
import com.myportfolio.portfolioms.dto.MutualFundsInputList;
import com.myportfolio.portfolioms.dto.Stock;
import com.myportfolio.portfolioms.dto.StockinputList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "MUTUAL-FUND-SERVICE")
public interface MututalInte {
    @RequestMapping(value = "/MutualFunds/all",method = RequestMethod.POST)
    List<MutualFund> getMutualFundByIds(@RequestBody MutualFundsInputList mutualFundsInputList);
}
