package org.age.tenisu.controller;

import org.age.tenisu.model.Country;
import org.age.tenisu.model.Player;
import org.age.tenisu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public Page<Player> getAll(Pageable pageable) {
        return playerService.getPlayersByRank(pageable);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getOne(@PathVariable Long id) {
        return playerService.getOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
