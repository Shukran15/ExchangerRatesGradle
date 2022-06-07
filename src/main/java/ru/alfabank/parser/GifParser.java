package ru.alfabank.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GifParser {
    @Autowired
    private ObjectMapper mapper;

    public String getGifRef(String json) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(json);

        JsonNode dataNode = rootNode.get("data");

        JsonNode urlNode = dataNode.get("embed_url");

        return urlNode.asText();
    }

}
