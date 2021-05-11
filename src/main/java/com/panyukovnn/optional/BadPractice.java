package com.panyukovnn.optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Примеры неправильного использования
 */
public class BadPractice {

    private String getDefaultValue() {
        return "defaultValue";
    }

    /**
     * Правило №4: не стоит злоупотреблять optional
     * Правило №5: если в цепочке присутствуют вложенные optional, то вероятно это слишком сложно
     */
    @Test
    public void fourthRule() {
        String s = null;

        // Плохо
        String result = Optional.ofNullable(s).orElseGet(this::getDefaultValue);

        assertEquals(result, getDefaultValue());

        // Хорошо
        result = (s != null) ? s : getDefaultValue();

        assertEquals(result, getDefaultValue());

        /*
         * При помощи optional можно составлять сложные конструкции,
         * однако код перестанет быть читабельным,
         * в связи с чем иногда лучше отказаться от их использования
         *
         * Например, если в цепочке optional возвращаются вложенные optional: Optional<Optional<T>>
         */
    }

    /**
     * Проблема с optional.get()
     * Brian Goetz сказал, что его главным разочарованием является optional.get():
     * "There is a get() method on Optional; we should never called it get().
     * We should have called it getOrThrowSomethingHorribleIfTheThingIsEmpty()"
     * Поскольку этот метод полностью разрушает смысл использования optional
     */

    /**
     * Правило №6: не используйте optional в полях, параметрах методов и коллекциях
     */
    private Optional<Double> optionalDouble; // Антипаттерн

    public void notUseOptional(Optional<Integer> optionalInteger /* Антипаттерн */) {
        List<Optional<String>> stringOptionals = new ArrayList<>(); // Замедляет работу программы из-за дополнительного оборачивания объектов
    }

    /**
     * Правило №7: не используйте == для сравнения optional
     */

    /**
     * Выводы:
     *
     * Не стоит заменять каждый null на optional
     * - null может быть вполне безопасен
     * - на null легко проверить
     * - nullable параметры это нормально
     *
     * Optional это не замена null, он лишь решает проблему, когда нам нужно
     * вернуть из метода значение или default value
     */
}
