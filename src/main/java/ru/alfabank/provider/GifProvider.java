package ru.alfabank.provider;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alfabank.client.GifClient;
import ru.alfabank.parser.GifParser;

@Component
public class GifProvider {
    @Value("${gif.app.id}")
    private String id;

    @Autowired
    private GifClient client;

    @Autowired
    private GifParser gifParser;

    public String getGifRef(String tag) throws JsonProcessingException {
        String response = client.getGifJson(id, tag);
        return gifParser.getGifRef(response);
    }

}
