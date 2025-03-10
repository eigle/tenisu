package org.age.tenisu.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
public class Player {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String shortName;

    private Sex sex;

    @ManyToOne
    private Country country;

    private String picture;

    private PlayerData data;

    @Embeddable
    @Data
    public static class PlayerData {

        private Integer rank;

        private Integer points;

        private Integer weight;

        private Integer height;

        private Integer age;

        @ElementCollection
        private List<Integer> last;
    }
}
