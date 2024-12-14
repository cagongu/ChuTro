package com.spring6framework.ChuTro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final String BASE_URL = "https://provinces.open-api.vn/api/";

    private final RestTemplate restTemplate = new RestTemplate();

    // Lấy danh sách tỉnh
    @GetMapping("/provinces")
    public Object getProvinces() {
        return restTemplate.getForObject(BASE_URL + "?depth=1", Object.class);
    }

    // Lấy danh sách huyện theo mã tỉnh
    @GetMapping("/districts/{provinceCode}")
    public Object getDistricts(@PathVariable String provinceCode) {
        return restTemplate.getForObject(BASE_URL + "p/" + provinceCode + "?depth=2", Object.class);
    }

    // Lấy danh sách xã theo mã huyện
    @GetMapping("/wards/{districtCode}")
    public Object getWards(@PathVariable String districtCode) {
        return restTemplate.getForObject(BASE_URL + "d/" + districtCode + "?depth=2", Object.class);
    }
}