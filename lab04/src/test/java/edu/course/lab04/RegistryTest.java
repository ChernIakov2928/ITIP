package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class RegistryTest {

    // --- Тесты подготовительной части (Book) ---
    @Test
    void bookEqualsAndHashCodeWorkInMap() {
        Map<Book, String> library = new HashMap<>();
        library.put(new Book("123-456", "Java Basics"), "Shelf 1");

        // Ищем по другому экземпляру, но с тем же ISBN
        Book searchBook = new Book("123-456", "Another Title");
        assertEquals("Shelf 1", library.get(searchBook));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book b1 = new Book("111", "Title");
        Book b2 = new Book("222", "Title");
        assertNotEquals(b1, b2);
    }

    // --- Тесты основного задания ---
    @Test
    void differentInstancesOfLogicallyEqualKeyFindSameValue() {
        SampleRegistry registry = new SampleRegistry();
        DataSample sample = new DataSample("cat", new double[]{1.0});

        SampleID id1 = new SampleID("dataset-1", 1);
        registry.addOrReplace(id1, sample);

        SampleID id2 = new SampleID("dataset-1", 1); // Другой экземпляр
        assertEquals(sample, registry.get(id2)); // Должен найти
    }

    @Test
    void duplicateAdditionReplacesValueAndDoesNotIncreaseSize() {
        SampleRegistry registry = new SampleRegistry();
        SampleID id1 = new SampleID("dataset", 1);
        SampleID id2 = new SampleID("dataset", 1);

        DataSample sample1 = new DataSample("old", new double[]{1.0});
        DataSample sample2 = new DataSample("new", new double[]{2.0});

        registry.addOrReplace(id1, sample1);
        registry.addOrReplace(id2, sample2); // Заменяем

        assertEquals(1, registry.size());
        assertEquals(sample2, registry.get(id1)); // Значение заменилось
    }

    @Test
    void comparingWithNullOrOtherTypeReturnsFalse() {
        SampleID id = new SampleID("dataset", 1);
        assertFalse(id.equals(null));
        assertFalse(id.equals("string"));
    }

    @Test
    void equalKeysWithSameHashCodeKeepDifferentValues() {
        // Эксперимент 3: Коллизия
        Map<CollisionKey, String> map = new HashMap<>();
        CollisionKey k1 = new CollisionKey("A", 42);
        CollisionKey k2 = new CollisionKey("B", 42); // Искусственно одинаковый hashCode

        map.put(k1, "Value 1");
        map.put(k2, "Value 2");

        assertEquals(2, map.size()); // Коллизия не сломала Map
        assertEquals("Value 1", map.get(k1));
        assertEquals("Value 2", map.get(k2));
    }

    @Test
    void mutableKeyDemonstratesSearchFailure() {
        // Эксперимент 5: Проблема изменяемого ключа
        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey("original");

        map.put(key, "Data");
        assertEquals("Data", map.get(key)); // Пока всё ок

        key.setValue("changed"); // Меняем ключ ПОСЛЕ добавления

        // Поиск не работает, потому что hashCode изменился, а Map ищет в старом бакете
        assertNull(map.get(key));
    }

    // --- Вспомогательные классы для тестов ---
    // Класс с искусственной коллизией
    static class CollisionKey {

        String name;
        int hash;

        CollisionKey(String name, int hash) {
            this.name = name;
            this.hash = hash;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return Objects.equals(name, ((CollisionKey) o).name);
        }
    }

    // Изменяемый ключ (плохая практика)
    static class MutableKey {

        String value;

        MutableKey(String value) {
            this.value = value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return Objects.equals(value, ((MutableKey) o).value);
        }
    }
}
