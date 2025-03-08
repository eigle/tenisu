package org.age.tenisu.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum Sex {
    FEMALE("F"), MALE("M");

    private final String code;

    Sex(String code) {
        this.code = code;
    }

    @JsonValue
    public String toJson() {
        return code;
    }

    public static Sex fromCode(String code) {
        for (Sex sex : values()) {
            if (sex.code.equals(code)) {
                return sex;
            }
        }

        throw new IllegalArgumentException("Unknown code : " + code);
    }

    @Converter(autoApply = true)
    public static class SexConverter implements AttributeConverter<Sex, String> {

        @Override
        public String convertToDatabaseColumn(Sex sex) {
            if (sex == null) {
                return null;
            }
            return sex.code;
        }

        @Override
        public Sex convertToEntityAttribute(String dbCode) {
            if (dbCode == null) {
                return null;
            }
            return Sex.fromCode(dbCode);
        }
    }

}

