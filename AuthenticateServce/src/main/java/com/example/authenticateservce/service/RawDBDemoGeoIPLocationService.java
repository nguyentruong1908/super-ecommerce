package com.example.authenticateservce.service;

import com.example.authenticateservce.model.GeoIP;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class RawDBDemoGeoIPLocationService {
    private DatabaseReader dbReader;

    public RawDBDemoGeoIPLocationService() throws IOException {
        File database = new File("GeoLite2-City_20231121/GeoLite2-City_20231121/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public GeoIP getLocation(String ip)
            throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);
        String latitude =
                response.getLocation().getLatitude().toString();
        String longitude =
                response.getLocation().getLongitude().toString();
        return new GeoIP(ip, latitude, longitude);
    }
}
