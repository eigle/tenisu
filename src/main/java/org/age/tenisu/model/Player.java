package org.age.tenisu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Entity
@ToString
@Getter
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
    @ToString
    @Getter
    public static class PlayerData {

        private int rank;

        private int points;

        private int weight;

        private int height;

        private int age;

        @ElementCollection
        private List<Integer> last;
    }
}
