package berfin.soap23;

import berfin.soap23.wsdl.CountryISOCode;
import berfin.soap23.wsdl.CountryISOCodeResponse;
import berfin.soap23.wsdl.CountryInfoService;
import berfin.soap23.wsdl.CountryInfoServiceSoapType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    public CountryISOCodeResponse getCountry(String country) {

/*
        CountryInfoService countryInfoService = new CountryInfoService();

        CountryInfoServiceSoapType countryInfoServiceSoapType = countryInfoService.getCountryInfoServiceSoap();

        CountryISOCode countryISOCode = new CountryISOCode();
        countryISOCode.setSCountryName(countryInfoServiceSoapType.countryISOCode(country));


        return countryISOCode;
*/


        CountryISOCode request = new CountryISOCode();
        request.setSCountryName(country);

        log.info("Requesting location for " + country);

        CountryISOCodeResponse response = (CountryISOCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?op=CountryISOCode",
                        request, new SoapActionCallback(""));

        return response;

    }
}
