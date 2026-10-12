package dk.mikkel.routeplanning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/GSearch")
public class GSearchController {

    @GetMapping
    public String getWay(){
        return "We Work";
    }
}

