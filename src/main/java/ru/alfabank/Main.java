package ru.alfabank;

import feign.Contract;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import ru.alfabank.client.CurrencyClient;
import ru.alfabank.client.GifClient;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class Main {

    @Value("${site.exchange}")
    private String exchangeSite;

    @Value("${site.gif}")
    private String gifsSite;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public CurrencyClient currencyClient() {
        return Feign.builder().target(CurrencyClient.class, exchangeSite);
    }

    @Bean
    public GifClient gifClient() {
        return Feign.builder().target(GifClient.class, gifsSite);
    }

    @Bean
    public Contract useFeignAnnotations() {
        return new Contract.Default();
    }

}
