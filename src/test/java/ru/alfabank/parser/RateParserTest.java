package ru.alfabank.parser;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateParserTest {
    private static final String exchangeExample = "{\n" +
            "    \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "    \"license\": \"https://openexchangerates.org/license\",\n" +
            "    \"timestamp\": 1654606789,\n" +
            "    \"base\": \"USD\",\n" +
            "    \"rates\": {\n" +
            "        \"RUB\": 61.350007\n" +
            "    }\n" +
            "}";


    @Autowired
    private RateParser rateParser;

    @Test
    public void parseExchangeTest() throws IOException {
        double rate = rateParser.parseExchange(exchangeExample, "RUB");
        assertTrue(Math.abs(61.350007 - rate) < 0.001);
    }


}
