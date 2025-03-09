package org.age.tenisu.repository.projection;

import java.util.List;

public interface PlayerMatchesByCountryProjection {

    String getCountryCode();

    List<Integer> getLastMatches();

}
