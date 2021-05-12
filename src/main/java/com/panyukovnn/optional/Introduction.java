package com.panyukovnn.optional;

import com.panyukovnn.optional.model.Personage;
import org.junit.Test;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static com.panyukovnn.optional.PersonageUtil.personages;
import static org.junit.Assert.assertEquals;

public class Introduction {

    /*
     * Код без optional (Код является одной из ранних реализаций стримов и возвращал null)
     */
    public void noOptional() {
//        personages.stream()
//                .search(c -> c.getName().equals("Jesse")); // Personage или null

//        personages.stream()
//                .search(c -> c.getName().equals("Jessi"))
//                .getFeature(); <------- NullPointerException

//        Personage jesse = personages.stream()
//                .search(c -> c.getName().equals("Jesse"));
//
//        String jesseFeature = jesse != null ? jesse.getFeature() : "UNKNOWN";
    }

    @Test
    public void introduction() {
        /*
         *  Optional может быть только в двух состояниях
         *  - present - содержит значение
         *  - absent - не содержит значения (Optional.empty())
         */

        // Создание optional
        Optional<Personage> oPersonage = Optional.of(new Personage());
        Optional<Personage> emptyOptional = Optional.empty();

        // Nullable
        Optional nullableOptional = Optional.ofNullable(null); // Optional.empty()

        // Примитивные optional
        OptionalInt oInt = OptionalInt.of(10); // Optional<Integer>
        OptionalLong oLong = OptionalLong.of(10L); // Optional<Long>
        OptionalDouble oDouble = OptionalDouble.of(10.0); // Optional<Double>
    }

    /**
     * Правило №1: никогда не сеттить optional-объекту null
     */
    @Test
    public void firstRule() {
        Optional<Object> optional = null; // Никогда такого не допускать
    }

    @Test
    public void withOptional() {
        // Как нам получить фамилию персонажа ?
        personages.stream()
                .filter(p -> p.getName().equals("Gustavo"))
                .findFirst() // Optional<Personage>
                .get()       // Personage
                .getSurname();

        // Что если мы не сможем найти персонажа ?
        // Т.е. что произойдет если мы вызовем метод get у Optional.empty() ?
        personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst()
                .get();
    }

    /**
     * Правило №2: никогда не использовать optional.get(),
     *             если перед этим он не проверен при помощи optional.isPresent()
     * Правило №3: не используйте связку get – isPresent, ищите альтернативы
     */
    @Test
    public void secondAndThirdRules() {
        Optional<Personage> personage = personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst();

        String surname = personage.isPresent() ? personage.get().getSurname() : "UNKNOWN"; // Антипаттерн

        assertEquals("UNKNOWN", surname);
    }
}
