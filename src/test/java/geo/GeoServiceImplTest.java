package geo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import entity.Country;
import entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoServiceImpl geoService=new GeoServiceImpl();
        Location result=geoService.byIp("96.44.183.149");
        Location expected=new Location("New York", Country.USA, " 10th Avenue", 32);
        assertEquals(expected,result);
    }
}