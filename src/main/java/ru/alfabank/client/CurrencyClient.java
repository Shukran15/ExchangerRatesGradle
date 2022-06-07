package ru.alfabank.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(name = "currency")
public interface CurrencyClient {
    @RequestLine("GET /{date}.json?app_id={id}&base={base}&symbols={symbols}")
    String getRate(@Param("date") String date, @Param("id") String appId, @Param("base") String base, @Param("symbols") String symbols);
}
