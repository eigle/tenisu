package org.age.tenisu.service;

import org.age.tenisu.model.Player;
import org.age.tenisu.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    /**
     * @return players sorted from best to worst.
     */
    public Page<Player> getPlayersByRank(Pageable pageable) {
        return playerRepository.findAllByRank(pageable);
    }

}
