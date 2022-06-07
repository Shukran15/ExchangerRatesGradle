package ru.alfabank.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alfabank.parser.RateParser;
import ru.alfabank.client.CurrencyClient;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class CurrencyProvider {
    @Value("${exchange.app.id}")
    private String id;

    @Value("${currency.base}")
    private String base;

    @Autowired
    private RateParser parser;

    @Autowired
    private CurrencyClient client;

    public Double getDifference(String symbols) throws IOException {
        String todayDate = LocalDate.now().toString();
        String yesterdayDate = LocalDate.now().minusDays(1).toString();

        String todayResponse = client.getRate(todayDate, id, base, symbols);
        String yesterdayResponse = client.getRate(yesterdayDate, id, base, symbols);

        Double todayRate = parser.parseExchange(todayResponse, symbols);
        Double yesterdayRate = parser.parseExchange(yesterdayResponse, symbols);

        return todayRate - yesterdayRate;
    }
}
