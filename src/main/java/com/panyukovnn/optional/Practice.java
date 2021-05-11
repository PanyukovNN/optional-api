package com.panyukovnn.optional;

import com.panyukovnn.optional.model.Personage;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.panyukovnn.optional.PersonageUtil.personages;

/**
 * Примеры использования
 */
public class Practice {

    /**
     * Способ отсеять все пустые optional при формировании списка из значений optional'ов
     */
    @Test
    public void removeEmptyOptionalFromList() {
        List<Double> methCount = personages.stream()
                .map(Personage::getMeth)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        System.out.println(methCount);
    }

    /**
     * Как адаптировать код к ранее написанному коду, использующему null
     */
    @Test
    public void adaptingWithNull() {
        Optional.ofNullable(new Object());

        // Не рекомендуется, поскольку данную конструкцию используют слишком часто
        Personage nobody = getPersonage().orElse(null);
    }

    private Optional<Personage> getPersonage() {
        return Optional.of(new Personage());
    }
}
