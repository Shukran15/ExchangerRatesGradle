package ru.alfabank.parser;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GifParserTest {

    private static final String gifResponseExample =
            "{\"data\": {\n" +
                    "    \"type\": \"gif\",\n" +
                    "    \"id\": \"LMQgvvnMO0ZtZHBbd5\",\n" +
                    "    \"url\": \"https:\\/\\/giphy.com\\/gifs\\/NamasteCar-range-rover-evoque-LMQgvvnMO0ZtZHBbd5\",\n" +
                    "    \"slug\": \"NamasteCar-range-rover-evoque-LMQgvvnMO0ZtZHBbd5\",\n" +
                    "    \"bitly_gif_url\": \"https:\\/\\/gph.is\\/g\\/apbbPo6\",\n" +
                    "    \"bitly_url\": \"https:\\/\\/gph.is\\/g\\/apbbPo6\",\n" +
                    "    \"embed_url\": \"https:\\/\\/giphy.com\\/embed\\/LMQgvvnMO0ZtZHBbd5\",\n" +
                    "    \"username\": \"NamasteCar\"" +
                    "} }";

    @Autowired
    private GifParser gifParser;

    @Test
    public void getGifRefTest() throws JsonProcessingException {
        String gifUrl = gifParser.getGifRef(gifResponseExample);
        assertEquals(gifUrl, "https://giphy.com/embed/LMQgvvnMO0ZtZHBbd5");
    }


}
