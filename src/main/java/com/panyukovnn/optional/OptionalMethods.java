package com.panyukovnn.optional;

import com.panyukovnn.optional.exception.NotFoundException;
import com.panyukovnn.optional.model.Personage;
import com.sun.xml.internal.bind.v2.runtime.output.FastInfosetStreamWriterOutput;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.panyukovnn.optional.PersonageUtil.personages;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Методы optional
 */
public class OptionalMethods {

    /* Семейство методов orElse - правильное использование optional */

    /**
     * orElse(default)
     */
    @Test
    public void orElseMethods() {
        Personage personageOrElse = personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst()
                .orElse(new Personage()); // Если .findFirst() вернул Optional.empty()
    }

    /**
     * orElseGet(supplier)
     */
    @Test
    public void orElseGet() {
        Personage personageOrElseGet = personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst()
                .orElseGet(() -> {
                    Personage badger = new Personage();
                    badger.setMeth(1.0d);

                    return badger;
                });
    }

    /**
     * orElseThrow(exsupplier)
     */
    @Test
    public void orElseThrow() {
        Personage personageOrElseThrow = personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst()
                .orElseThrow(() -> new NotFoundException()); // NotFoundException::new
    }

    /* Дополнительные методы */

    /**
     * map и orElse
     */
    @Test
    public void map() {
        Optional<Personage> personage = personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst();

        String surname = personage.map(p -> p.getSurname()).orElse("UNKNOWN");

        assertEquals("UNKNOWN", surname);
    }

    /**
     * Одной цепочкой
     */
    @Test
    public void mapInChain() {
        String jesseSurname = personages.stream()
                .filter(p -> p.getName().equals("Jesse"))
                .findFirst()
                .map(Personage::getSurname)
                .orElse("UNKNOWN");

        assertEquals("Pinkman", jesseSurname);
    }

    /**
     * Метод filter удобно использовать, если нам необходимо добавить в цепочку какое-либо условие
     */
    @Test
    public void filter() {
        personages.stream()
                .filter(p -> p.getName().equals("Walter"))
                .findFirst()
                .filter(p -> p.getFeature().equals("Economist")) // Если не подходит, то вернет Optional.empty()
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Метод ifPresent (не путать с isPresent()) - выполняет lambda функцию, если optional содержит значение
     */
    @Test
    public void ifPresent() {
        getTask().ifPresent(t -> t.start());
    }

    private Optional<Thread> getTask() {
        return Optional.of(new Thread());
    }

    /**
     * equals и hashCode вызывают данные методы у значения, содержащегося в optional
     */
    @Test
    public void equalsAndHashCode() {
        Optional<Personage> walter = personages.stream()
                .filter(p -> p.getName().equals("Walter"))
                .findFirst();

        Optional<Personage> jesse = personages.stream()
                .filter(p -> p.getName().equals("Jesse"))
                .findFirst();

        assertNotEquals(walter, jesse);

        Optional<Personage> walterClone = Optional.of(new Personage("Walter", "White", ""));

        assertEquals(walter, walterClone);
    }

    /**
     * Как тестировать методы, возвращающие Optional
     */
    @Test
    public void testOptional() {
        Optional<Personage> walter = personages.stream()
                .filter(p -> p.getName().equals("Walter"))
                .findFirst();

        assertNotEquals(new Personage("Walter", "White", ""), walter);

        assertEquals(Optional.of(new Personage("Walter", "White", "")), walter);

        Optional<Personage> nobody = personages.stream()
                .filter(p -> p.getName().equals("Nobody"))
                .findFirst();

        assertEquals(Optional.empty(), nobody);
    }

    /**
     * Java 9
     */
    @Test
    public void java9() {
//        Возвращает пустой стрим или стрим с одним значением, содержащимся в optional
//        Stream<T> Optional.stream()

//        Если содержит значение, то вызывает consumer, иначе запускает runnable
//        void Optional.ifPresentOrElse(Consumer<T>, Runnable)

//        Если текущий optional содержит значение то возвращает его, иначе возвращает другой optional
//        Optional<T> Optional.or(Supplier<Optional<T>>)
    }
}
