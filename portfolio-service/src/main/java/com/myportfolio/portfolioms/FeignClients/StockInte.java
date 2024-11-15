package com.myportfolio.portfolioms.FeignClients;

import com.myportfolio.portfolioms.dto.Stock;
import com.myportfolio.portfolioms.dto.StockinputList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "STOCKS-SERVICE")
public interface StockInte {
    @RequestMapping(value = "/stocks/all",method = RequestMethod.POST)
    List<Stock> getStockByIds(@RequestBody StockinputList stockinputList);
}
