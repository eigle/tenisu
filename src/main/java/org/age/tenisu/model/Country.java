package org.age.tenisu.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class Country {

    @Id
    private String code;

    private String picture;

}
