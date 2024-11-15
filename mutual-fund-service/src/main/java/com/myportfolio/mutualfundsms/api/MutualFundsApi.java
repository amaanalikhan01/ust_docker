package com.myportfolio.mutualfundsms.api;

import com.myportfolio.mutualfundsms.dto.MutualFundsInputList;
import com.myportfolio.mutualfundsms.model.MutualFund;
import com.myportfolio.mutualfundsms.repository.MutualFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MutualFunds")
public class MutualFundsApi {
    @Autowired
    private MutualFundRepository mutualFundRepository;

    @GetMapping("/{id}")
    public MutualFund getFundById(@PathVariable int id){
        return mutualFundRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No mutual found with ID : "+id));
    }

    @PostMapping
    public MutualFund addStock(@RequestBody MutualFund mutualFund){
        return mutualFundRepository.save(mutualFund);
    }

    @GetMapping
    public List<MutualFund> getAllStocks(){

        return mutualFundRepository.findAll();
    }

    @PostMapping("/all")
    public List<MutualFund> findAllByIds(@RequestBody MutualFundsInputList mutualFundsInputList){
        return mutualFundRepository.findAllById(mutualFundsInputList.ids());
    }
}
