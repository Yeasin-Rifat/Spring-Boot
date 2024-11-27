package com.example.crud_on_json;



import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataService {
    private static final String GITHUB_JSON_URL = "https://raw.githubusercontent.com/Yeasin-Rifat/Spring-Boot/refs/heads/main/data.json";

    public String fetchJsonData() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GITHUB_JSON_URL, String.class);
    }
}