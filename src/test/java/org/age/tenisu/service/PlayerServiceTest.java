package org.age.tenisu.service;

import jakarta.transaction.Transactional;
import org.age.tenisu.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    public void testGetPlayersByRank() {
        Page<Player> players = playerService.getPlayersByRank(
                PageRequest.of(0, 10));

        assertThat(players).hasSize(5);
        assertThat(players.getContent().getFirst().getLastName()).isEqualTo("Nadal");
        assertThat(players.getContent().getLast().getLastName()).isEqualTo("Williams");
    }

}
