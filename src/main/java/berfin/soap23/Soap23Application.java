package berfin.soap23;

import berfin.soap23.wsdl.CountryISOCodeResponse;
import berfin.soap23.wsdl.FullCountryInfoResponse;
import berfin.soap23.wsdl.TLanguage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Soap23Application {

    public static void main(String[] args) {
        SpringApplication.run(Soap23Application.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }

            CountryISOCodeResponse response = countryClient.getCountryISO(country);
            System.err.println(response.getCountryISOCodeResult());

            FullCountryInfoResponse response1 = countryClient.getCountryInfo(response.getCountryISOCodeResult());
            response1.getFullCountryInfoResult().getLanguages().getTLanguage()
                    .stream()
                    .forEach(language -> {
                        System.out.println("Language ISO Code: " + language.getSISOCode());
                        System.out.println("Language Name: " + language.getSName());
                        System.out.println();
                    });

        };
    }



}
