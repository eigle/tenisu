package org.age.tenisu.service;

import jakarta.transaction.Transactional;
import org.age.tenisu.model.Country;
import org.age.tenisu.model.Player;
import org.age.tenisu.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

@SpringBootTest
@Transactional
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testGetPlayersByRank() {
        Page<Player> players = playerService.getPlayersByRank(
                PageRequest.of(0, 10));

        assertThat(players).hasSize(5);
        assertThat(players.getContent().getFirst().getLastName()).isEqualTo("Nadal");
        assertThat(players.getContent().getLast().getLastName()).isEqualTo("Williams");
    }

    @Test
    public void testCalculateMedian() {
        Optional<Double> median = playerService.calculateMedianHeight();

        assertThat(median).isPresent().get()
                .isEqualTo(185.0);
    }

    @Test
    public void testCalculateMedianEmpty() {
        playerRepository.deleteAll();
        Optional<Double> median = playerService.calculateMedianHeight();

        assertThat(median).isNotPresent();
    }

    @Test
    public void testCalculateMedianOnTwoPlayers() {
        // Delete all players except two
        playerRepository.deleteAll(playerService.getPlayersByRank(
                PageRequest.of(0, 3)).getContent());

        Optional<Double> median = playerService.calculateMedianHeight();

        assertThat(median).isPresent().get()
                .isEqualTo(184.0);
    }

    @Test
    public void testCalculateAverageBmi() {
        Optional<Double> averageBmi = playerService.calculateAverageBmi();

        assertThat(averageBmi).isPresent();
        assertThat(averageBmi.get()).isCloseTo(23.3578389955, within(0.00000000001));
    }

    @Test
    public void testFindCountryByPlayerRatio() {
        Optional<Country> bestCountry = playerService.findCountryByPlayerRatio();

        assertThat(bestCountry).isPresent();
        assertThat(bestCountry.get().getCode()).isEqualTo("SRB");

        // Create loose streaking Serbian player
        var data = new Player.PlayerData();
        data.setLast(List.of(0, 0, 0, 0, 0));
        var serbianPlayer = new Player();
        serbianPlayer.setId(1L);
        serbianPlayer.setCountry(bestCountry.get());
        serbianPlayer.setData(data);

        playerRepository.save(serbianPlayer);

        bestCountry = playerService.findCountryByPlayerRatio();

        assertThat(bestCountry).isPresent();
        assertThat(bestCountry.get().getCode()).isEqualTo("SUI");
    }

}
