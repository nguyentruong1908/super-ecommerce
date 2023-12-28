package com.example.authenticateservce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoIP {
    private String ip;
    private String latitude;
    private String longitude;
}