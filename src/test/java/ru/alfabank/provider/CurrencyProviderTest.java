package ru.alfabank.provider;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alfabank.client.CurrencyClient;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyProviderTest {
    private static final String todayRate = "{\n" +
            "    \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "    \"license\": \"https://openexchangerates.org/license\",\n" +
            "    \"timestamp\": 1654606789,\n" +
            "    \"base\": \"USD\",\n" +
            "    \"rates\": {\n" +
            "        \"RUB\": 61.350007\n" +
            "    }\n" +
            "}";

    private static final String yesterdayRate = "{\n" +
            "    \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "    \"license\": \"https://openexchangerates.org/license\",\n" +
            "    \"timestamp\": 1654473589,\n" +
            "    \"base\": \"USD\",\n" +
            "    \"rates\": {\n" +
            "        \"RUB\": 63.250003\n" +
            "    }\n" +
            "}";


    @Value("${exchange.app.id}")
    private String id;

    @Value("${currency.base}")
    private String base;

    @Autowired
    private CurrencyProvider provider;

    @MockBean
    private CurrencyClient client;

    @Test
    public void getDifference() throws IOException {
        Mockito.when(client.getRate(LocalDate.now().toString(), id, base, "RUB")).thenReturn(todayRate);
        Mockito.when(client.getRate(LocalDate.now().minusDays(1).toString(), id, base, "RUB")).thenReturn(yesterdayRate);

        assertTrue(Math.abs(-1.8999960000000016-provider.getDifference("RUB"))<0.0001);
    }

}
