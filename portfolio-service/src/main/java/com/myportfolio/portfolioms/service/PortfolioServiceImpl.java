package com.myportfolio.portfolioms.service;
import com.myportfolio.portfolioms.FeignClients.MututalInte;
import com.myportfolio.portfolioms.FeignClients.StockInte;
import com.myportfolio.portfolioms.dto.MutualFund;
import com.myportfolio.portfolioms.dto.MutualFundsInputList;
import com.myportfolio.portfolioms.dto.Stock;
import com.myportfolio.portfolioms.dto.StockinputList;
import com.myportfolio.portfolioms.model.Portfolio;
import com.myportfolio.portfolioms.repository.PortfolioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StockInte stockInte;
    @Autowired
    private MututalInte mututalInte;
    public Portfolio createPortfolio(Portfolio portfolio) {
        Portfolio savedPortfolio = portfolioRepo.save(portfolio);
        savedPortfolio.setTotalValue(fetchTotalValue(savedPortfolio));
        return savedPortfolio;
    }
    public Portfolio getPortfolioById(String id) {

        Portfolio portfolio = portfolioRepo.findById(id).orElseThrow(() -> new RuntimeException("No Portfolio Found with ID " + id));

        portfolio.setTotalValue(fetchTotalValue(portfolio));
        return portfolio;
    }
//    public double fetchTotalValue(Portfolio portfolio) {
//
//        double totalValue = 0;
//        for (int stockId : portfolio.getStocks()) {
//
//            Stock stock = restTemplate.getForObject("http://STOCKS-SERVICE/stocks/" + stockId, Stock.class);
//            totalValue += stock.getPrice();
//
//        }
//        for (int mutualId: portfolio.getMutualFunds()) {
//
//            MutualFund mutualFund = restTemplate.getForObject("http://MUTUAL-FUND-SERVICE/MutualFunds/" + mutualId, MutualFund.class);
//            totalValue += mutualFund.getAmountInvested();
//
//        }
//
//        return totalValue;
//    }
//    public double fetchTotalValue(Portfolio portfolio) {
//        double totalValue = 0;
//
//        // Fetch only the stocks associated with the portfolio
//        List<Integer> stockIds = new ArrayList<>(portfolio.getStocks()); // Assuming portfolio.getStocks() returns Set<Integer>
//        if (!stockIds.isEmpty()) {
//            // Construct the URL to fetch only the specific stocks that belong to this portfolio
//            String stockServiceUrl = "http://STOCKS-SERVICE/stocks?ids=" + String.join(",", stockIds.stream().map(String::valueOf).collect(Collectors.toList()));
//            // Make a GET request to fetch the stocks
//            Stock[] stocks = restTemplate.getForObject(stockServiceUrl, Stock[].class);
//            if (stocks != null) {
//                // Loop through the fetched stocks and add their prices to the total value
//                for (Stock stock : stocks) {
//                    totalValue += stock.getPrice();
//                }
//            }
//        }
//
//        // Fetch only the mutual funds associated with the portfolio
//        List<Integer> mutualIds = new ArrayList<>(portfolio.getMutualFunds()); // Assuming portfolio.getMutualFunds() returns Set<Integer>
//        if (!mutualIds.isEmpty()) {
//            // Construct the URL to fetch only the specific mutual funds that belong to this portfolio
//            String mutualFundServiceUrl = "http://MUTUAL-FUND-SERVICE/MutualFunds?ids=" + String.join(",", mutualIds.stream().map(String::valueOf).collect(Collectors.toList()));
//            // Make a GET request to fetch the mutual funds
//            MutualFund[] mutualFunds = restTemplate.getForObject(mutualFundServiceUrl, MutualFund[].class);
//            if (mutualFunds != null) {
//                // Loop through the fetched mutual funds and add their amounts invested to the total value
//                for (MutualFund mutualFund : mutualFunds) {
//                    totalValue += mutualFund.getAmountInvested();
//                }
//            }
//        }
//        return totalValue;
//    }
//    public double fetchTotalValue(Portfolio portfolio) {
//        double totalValue = 0;
//        List<Integer> stockIds = new ArrayList<>(portfolio.getStocks());
//        if (!stockIds.isEmpty()) {
//            String stockServiceUrl = "http://STOCKS-SERVICE/stocks/all";
//            StockinputList stockinputList = new StockinputList(stockIds);
//
//            ResponseEntity<List<Stock>> response = restTemplate.exchange(
//                    stockServiceUrl,
//                    HttpMethod.POST,
//                    new HttpEntity<>(stockinputList),
//                    new ParameterizedTypeReference<List<Stock>>() {}
//            );
//
//
//            List<Stock> stocks = response.getBody();
//            if (stocks != null) {
//                for (Stock stock : stocks) {
//                    totalValue += stock.getPrice();
//                }
//            }
//        }
//        List<Integer> mutualIds = new ArrayList<>(portfolio.getMutualFunds());
//        if (!mutualIds.isEmpty()) {
//            String mutualServiceUrl = "http://MUTUAL-FUND-SERVICE/MutualFunds/all";
//            MutualFundsInputList mutualinputList = new MutualFundsInputList(mutualIds);
//
//            ResponseEntity<List<MutualFund>> response = restTemplate.exchange(
//                    mutualServiceUrl,
//                    HttpMethod.POST,
//                    new HttpEntity<>(mutualinputList),
//                    new ParameterizedTypeReference<List<MutualFund>>() {}
//            );
//
//
//            List<MutualFund> mutualFunds = response.getBody();
//            if (mutualFunds != null) {
//                for (MutualFund m : mutualFunds) {
//                    totalValue += m.getAmountInvested();
//                }
//            }
//        }
//
//        return totalValue;
//    }
public double fetchTotalValue(Portfolio portfolio) {
    double totalValue = 0;
    List<Integer> stockIds = new ArrayList<>(portfolio.getStocks());
    if (!stockIds.isEmpty()) {
//        String stockServiceUrl = "http://STOCKS-SERVICE/stocks/all";
        StockinputList stockinputList = new StockinputList(stockIds);
//        Stock[] stocks = restTemplate.postForObject(
//                stockServiceUrl,
//                stockinputList,
//                Stock[].class
//        );
        List<Stock> stocks=stockInte.getStockByIds(stockinputList);

        if (stocks != null) {
            for (Stock stock : stocks) {
                totalValue += stock.getPrice();
            }
        }
    }
    List<Integer> mutualIds = new ArrayList<>(portfolio.getMutualFunds());
    if (!mutualIds.isEmpty()) {
//        String mutualFundsUrl = "http://MUTUAL-FUND-SERVICE/MutualFunds/all";
        MutualFundsInputList mutualFundsInputList = new MutualFundsInputList(mutualIds);
//        MutualFund[] mutualFunds = restTemplate.postForObject(
//                mutualFundsUrl,
//                mutualFundsInputList,
//                MutualFund[].class
//        );
        List<MutualFund> mutualFunds=mututalInte.getMutualFundByIds(mutualFundsInputList);
        if (mutualFunds != null) {
            for (MutualFund m : mutualFunds) {
                totalValue += m.getAmountInvested();
            }
        }
    }
    return totalValue;
}




}
