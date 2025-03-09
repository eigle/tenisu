package org.age.tenisu.controller;

import org.age.tenisu.model.Country;
import org.age.tenisu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/statistics/countries/ratio/best")
    public ResponseEntity<Country> getBestRatioCountry() {
        return playerService.findCountryByPlayerRatio()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/statistics/players/bmi/average")
    public ResponseEntity<Double> getAverageBmi() {
        return playerService.calculateAverageBmi()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/statistics/players/height/median")
    public ResponseEntity<Double> getMedianHeight() {
        return playerService.calculateMedianHeight()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

}
