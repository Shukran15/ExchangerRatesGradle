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
import ru.alfabank.client.GifClient;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GifProviderTest {

    private static final String gifJson = "{\n" +
            "  \"data\": {\n" +
            "    \"type\": \"gif\",\n" +
            "    \"id\": \"LMQgvvnMO0ZtZHBbd5\",\n" +
            "    \"url\": \"https:\\/\\/giphy.com\\/gifs\\/NamasteCar-range-rover-evoque-LMQgvvnMO0ZtZHBbd5\",\n" +
            "    \"slug\": \"NamasteCar-range-rover-evoque-LMQgvvnMO0ZtZHBbd5\",\n" +
            "    \"bitly_gif_url\": \"https:\\/\\/gph.is\\/g\\/apbbPo6\",\n" +
            "    \"bitly_url\": \"https:\\/\\/gph.is\\/g\\/apbbPo6\",\n" +
            "    \"embed_url\": \"https:\\/\\/giphy.com\\/embed\\/LMQgvvnMO0ZtZHBbd5\",\n" +
            "    \"username\": \"NamasteCar\"" +
            "}\n" +
            "}";

    @Value("${gif.app.id}")
    private String id;

    @Autowired
    private GifProvider provider;

    @MockBean
    private GifClient client;


    @Test
    public void getGifJsonTest() throws JsonProcessingException {
        Mockito.when(client.getGifJson(id, "rich")).thenReturn(gifJson);

        assertEquals(provider.getGifRef("rich"), "https://giphy.com/embed/LMQgvvnMO0ZtZHBbd5");
    }


}
