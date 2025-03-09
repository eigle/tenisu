package org.age.tenisu.repository;

import org.age.tenisu.model.Player;
import org.age.tenisu.repository.projection.PlayerBmiDataProjection;
import org.age.tenisu.repository.projection.PlayerMatchesByCountryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p ORDER BY p.data.rank ASC")
    Page<Player> findAllByRank(Pageable pageable);

    @Query("SELECT p.data.height FROM Player p WHERE p.data.height IS NOT NULL")
    List<Integer> findAllHeight();

    @Query("SELECT p.data.height AS height, p.data.weight AS weight FROM Player p " +
            "WHERE p.data.height IS NOT NULL AND p.data.weight IS NOT NULL")
    List<PlayerBmiDataProjection> findAllPlayersBmiData();

    @Query("SELECT p.country.code AS countryCode, p.data.last AS lastMatches FROM Player p")
    List<PlayerMatchesByCountryProjection> findAllPlayersMatchesByCountry();

}
