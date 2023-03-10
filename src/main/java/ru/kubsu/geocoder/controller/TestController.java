package ru.kubsu.geocoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kubsu.geocoder.client.NominatimClient;
import ru.kubsu.geocoder.dto.NominatimPlace;
import ru.kubsu.geocoder.model.Test;
import ru.kubsu.geocoder.service.TestService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("tests")
public class TestController {
    private TestService service;
    private NominatimClient nominatimClient;

    @Autowired
    public TestController(TestService service, NominatimClient nominatimClient) {
        this.service = service; this.nominatimClient = nominatimClient;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Test getTest(@PathVariable Integer id,
                        @RequestParam String name){
        return service.build(id, name);
    }

    @GetMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public void save(@RequestParam String name){
        service.save(name);
    }

    @GetMapping(value = "/load/{name}", produces = APPLICATION_JSON_VALUE)
    public Test load(@PathVariable String name){
        return service.load(name);
    }

    @GetMapping(value = "/test", produces = APPLICATION_JSON_VALUE)
    public NominatimPlace test() {
      return nominatimClient.search("кубгу", "json").get(0);
    }
}
