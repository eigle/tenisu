package org.age.tenisu.repository;

import org.age.tenisu.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p ORDER BY p.data.rank ASC")
    Page<Player> findAllByRank(Pageable pageable);
}
