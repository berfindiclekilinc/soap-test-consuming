package berfin.soap23;

import berfin.soap23.wsdl.CountryISOCode;
import berfin.soap23.wsdl.CountryISOCodeResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

            CountryISOCodeResponse response = countryClient.getCountry(country);
            System.err.println(response.getCountryISOCodeResult());
        };
    }

}
