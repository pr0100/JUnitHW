package homework;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {
    @Test
    @DisplayName("Первый тест")
     void simpleTest() {
        System.out.println("Hello World!");
    }

    @Test
    @DisplayName("Второй тест")
    void newSimpleTest() {
        System.out.println("Hello World!");
    }
}
