package berfin.soap23;

import berfin.soap23.wsdl.CountryISOCode;
import berfin.soap23.wsdl.CountryISOCodeResponse;
import berfin.soap23.wsdl.FullCountryInfo;
import berfin.soap23.wsdl.FullCountryInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    @Value("${soap.country-info-service.url}")
    private String countryInfoServiceUrl;

    @Value("${soap.full-country-info.url}")
    private String fullCountryInfoUrl;

    public CountryISOCodeResponse getCountryISO(String country) {

        CountryISOCode request = new CountryISOCode();
        request.setSCountryName(country);

        log.info("Requesting location for " + country);

        CountryISOCodeResponse response = (CountryISOCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(countryInfoServiceUrl,
                        request, new SoapActionCallback(""));

        return response;
    }

    public FullCountryInfoResponse getCountryInfo(String countryIso){

        FullCountryInfo request = new FullCountryInfo();
        request.setSCountryISOCode(countryIso);

        log.info("Requesting info for " + countryIso );

        FullCountryInfoResponse response = (FullCountryInfoResponse) getWebServiceTemplate()
                .marshalSendAndReceive(fullCountryInfoUrl,
                        request, new SoapActionCallback(""));

        return response;
    }

}
