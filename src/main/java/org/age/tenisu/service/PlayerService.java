package org.age.tenisu.service;

import org.age.tenisu.model.Country;
import org.age.tenisu.model.Player;
import org.age.tenisu.repository.CountryRepository;
import org.age.tenisu.repository.PlayerRepository;
import org.age.tenisu.repository.projection.PlayerBmiDataProjection;
import org.age.tenisu.repository.projection.PlayerMatchesByCountryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CountryRepository countryRepository;

    /**
     * @return players sorted from best to worst.
     */
    public Page<Player> getPlayersByRank(Pageable pageable) {
        return playerRepository.findAllByRank(pageable);
    }

    public Optional<Player> getOne(Long id) {
        return playerRepository.findById(id);
    }


    /**
     * @return best country by its player winn ratio.
     */
    public Optional<Country> findCountryByPlayerRatio() {
        // Aggregate player matches by country
        Map<String, List<Integer>> playersMatchesByCountry = playerRepository.findAllPlayersMatchesByCountry().stream()
                .collect(Collectors.toMap(
                        PlayerMatchesByCountryProjection::getCountryCode,
                        PlayerMatchesByCountryProjection::getLastMatches,
                        (currentMatches, newMatches) -> {
                            currentMatches.addAll(newMatches);
                            return currentMatches;
                        }
                ));

        if (playersMatchesByCountry.isEmpty()) {
            return Optional.empty();
        }

        Map<String, Double> ratioByCountry = playersMatchesByCountry.keySet().stream()
                .collect(Collectors.toMap(key -> key, key -> 0.0));

        // Create countries ratio from matches
        for (String country : playersMatchesByCountry.keySet()) {
            List<Integer> playerMatches = playersMatchesByCountry.get(country);

            int winnCount = playerMatches.stream().reduce(0, Integer::sum);

            Double countryRatio = (double) winnCount / playerMatches.size();

            ratioByCountry.put(country, countryRatio);
        }

        Optional<String> bestCountry = ratioByCountry.keySet().stream()
                .max(Comparator.comparing(ratioByCountry::get));

        return countryRepository.findById(bestCountry.get());
    }


    /**
     * @return the average Body Mass Index of all the players.
     */
    public Optional<Double> calculateAverageBmi() {
        List<PlayerBmiDataProjection> bmiData = playerRepository.findAllPlayersBmiData();

        if (bmiData.isEmpty()) {
            return Optional.empty();
        }

        OptionalDouble average = bmiData.stream()
                .filter(playerData -> playerData.getHeight() > 0 && playerData.getWeight() > 0)
                .mapToDouble(playerData -> {
                    double heightInMeters = playerData.getHeight() / 100.0;
                    double weightInKilo = playerData.getWeight() / 1000.0;
                    return weightInKilo / (heightInMeters * heightInMeters);
                })
                .average();

        return average.isPresent() ?
                Optional.of(average.getAsDouble()) :
                Optional.empty();
    }


    /**
     * @return the median height of all the players.
     */
    public Optional<Double> calculateMedianHeight() {
        List<Integer> playersHeight = playerRepository.findAllHeight();

        if (playersHeight.isEmpty()) {
            return Optional.empty();
        }

        Collections.sort(playersHeight);

        int size = playersHeight.size();
        int middle = size / 2;

        return Optional.of(
                (size % 2 == 0) ?
                        (playersHeight.get(middle - 1) + playersHeight.get(middle)) / 2.0 :
                        playersHeight.get(middle).doubleValue()
        );
    }

}
