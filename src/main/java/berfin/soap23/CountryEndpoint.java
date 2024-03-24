package berfin.soap23;

import berfin.soap23.wsdl.CountryISOCode;
import berfin.soap23.wsdl.CountryISOCodeResponse;
import berfin.soap23.wsdl.FullCountryInfoResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.oorsprong.org/websamples.countryinfo";

    private CountryClient countryClient;

    public CountryEndpoint(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryISOCode")
    @ResponsePayload
    public FullCountryInfoResponse getCountryISOCode(@RequestPayload String country) {

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

        return response1;
    }


}
