package org.age.tenisu.repository;

import org.age.tenisu.model.Country;
import org.age.tenisu.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
