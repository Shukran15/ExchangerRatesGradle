package ru.alfabank.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(name = "gif")
public interface GifClient {
    @RequestLine("GET /random?api_key={key}&tag={tag}&rating=g")
    String getGifJson(@Param("key") String key, @Param("tag") String tag);
}
