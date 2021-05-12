package com.panyukovnn.optional.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

/**
 * Персонаж breaking bad
 */
@Getter
@Setter
@NoArgsConstructor
public class Personage {

    /**
     * Имя
     */
    private String name;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Особенность персонажа
     */
    private String feature;

    /**
     * Метаамфетамин в граммах
     */
    private Double meth;

    public Personage(String name, String surname, String feature) {
        this.name = name;
        this.surname = surname;
        this.feature = feature;
    }

    public Optional<Double> getMeth() {
        return Optional.ofNullable(meth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personage personage = (Personage) o;
        return Objects.equals(name, personage.name) &&
                Objects.equals(surname, personage.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
