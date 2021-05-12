package com.panyukovnn.optional;

import com.panyukovnn.optional.exception.NotFoundException;
import com.panyukovnn.optional.model.Personage;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.panyukovnn.optional.PersonageUtil.personages;
import static org.junit.Assert.*;

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
                .findFirst() // Optional<Personage>
                .orElse(new Personage()); // Если .findFirst() вернул Optional.empty()

        assertNotNull(personageOrElse);
        assertNull(personageOrElse.getName());
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

        assertNotNull(personageOrElseGet);
        assertEquals(Optional.of(1.0d), personageOrElseGet.getMeth());
    }

    /**
     * orElseThrow(exsupplier)
     */
    @Test
    public void orElseThrow() {
        personages.stream()
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
        Optional<Personage> oPersonage = personages.stream()
                .filter(p -> p.getName().equals("Jesse"))
                .findFirst();

        String surname = oPersonage       // Optional<Personage>
                .map(p -> p.getSurname()) // Optional<String>
                .orElse("Pinkman");

        assertEquals("Pinkman", surname);
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
     * Метод ifPresent (не путать с isPresent())
     * выполняет lambda функцию, если optional содержит значение
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
        Optional<Personage> oWalter = personages.stream()
                .filter(p -> p.getName().equals("Walter"))
                .findFirst();

        Optional<Personage> oJesse = personages.stream()
                .filter(p -> p.getName().equals("Jesse"))
                .findFirst();

        assertNotEquals(oWalter, oJesse);

        Optional<Personage> oWalterClone = Optional.of(new Personage("Walter", "White", ""));

        assertEquals(oWalter, oWalterClone);
    }

    /**
     * Как тестировать методы, возвращающие Optional
     */
    @Test
    public void testOptional() {
        Optional<Personage> oWalter = personages.stream()
                .filter(p -> p.getName().equals("Walter"))
                .findFirst();

        Personage walterClone = new Personage("Walter", "White", "");

        assertNotEquals(walterClone, oWalter);

        assertEquals(Optional.of(walterClone), oWalter);

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
