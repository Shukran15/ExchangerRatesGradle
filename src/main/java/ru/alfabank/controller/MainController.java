package ru.alfabank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfabank.parser.GifParser;
import ru.alfabank.provider.CurrencyProvider;
import ru.alfabank.provider.GifProvider;

import java.io.IOException;

@Controller
public class MainController {
    @Value("${rich.tag}")
    private String richTag;

    @Value("${broke.tag}")
    private String brokeTag;

    @Value("${fail.gif.url}")
    private String failGifUrl;

    @Autowired
    private GifProvider gifProvider;


    @Autowired
    private CurrencyProvider currencyProvider;




    @GetMapping("/")
    public String index(Model model, @RequestParam("symbols") String symbols) {

        try {
            double diff = currencyProvider.getDifference(symbols);

            String gifRef = (diff > 0) ? gifProvider.getGifRef(richTag) : gifProvider.getGifRef(brokeTag);

            model.addAttribute("gif", gifRef);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());

            model.addAttribute("gif", failGifUrl);
        }

        return "index";
    }
}
