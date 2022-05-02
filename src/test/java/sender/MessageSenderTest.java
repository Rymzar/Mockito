package sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import entity.Country;
import entity.Location;
import geo.GeoService;
import i18n.LocalizationService;
import sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageSenderTest {

    @Test
    void sendRu() {
        GeoService geoService = Mockito.mock(GeoService.class);

        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");


        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        String expected = "Добро пожаловать";
        String result = messageSender.send(headers);
        assertEquals(expected, result);
    }

    @Test
    void sendEng() {

        String ip = "96.44.183.149";

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");


        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String expected = "Welcome";
        String result = messageSender.send(headers);
        assertEquals(expected, result);
    }
}