package ru.alfabank.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateParser {
    @Autowired
    private ObjectMapper mapper;


    public Double parseExchange(String json, String symbols) throws IOException {
        JsonNode rootNode = mapper.readTree(json);

        JsonNode ratesNode = rootNode.get("rates");

        JsonNode currencyNode = ratesNode.get(symbols);

        if (currencyNode == null) {
            throw new IOException("Не правильно задана валюта");
        }
        Double result = Double.parseDouble(currencyNode.asText());

        return result;
    }


}
