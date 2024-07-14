package homework;

import homework.data.Language;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @BeforeEach
    void setUp() {
        open("https://en.wikipedia.org/wiki/Main_Page");
    }

    @ParameterizedTest(name = "Поиск на сайте по запросу {0} должен показывать соответствующий заголовок")
    @ValueSource(strings = {"Spain", "England"})
    void successfulSearchTest(String input) {
        $("[aria-label='Search Wikipedia']").setValue(input).pressEnter();
        $("#firstHeading").shouldHave(text(input));
    }

    @ParameterizedTest(name = "Поиск на сайте по запросу {0} должен показывать страну проведения")
    @CsvSource({
            "2022 FIFA World Cup, Qatar",
            "UEFA Euro 2024, Germany"
    })
    void searchResultsShouldContainsExpectedCountry(String input, String expectedCountry) {
        $("[aria-label='Search Wikipedia']").setValue(input).pressEnter();
        $("table.infobox.vcalendar tbody tr:nth-child(4) td").shouldHave(text(expectedCountry));
    }

    static Stream<Arguments> selenideShouldDisplayExpectedLinks() {
        return Stream.of(
                Arguments.of(Language.English, List.of("Read", "View source", "View history")),
                Arguments.of(Language.Español, List.of("Leer", "Ver código fuente", "Ver historial"))
        );
    }

    @ParameterizedTest
    @MethodSource("selenideShouldDisplayExpectedLinks")
    void selenideShouldDisplayExpectedLinks(Language language, List<String> expectedLinks) {
        open("https://ru.wikipedia.org/wiki/Заглавная_страница");
        $$("#p-lang > div > ul").find(text(language.name())).click();
        $$("#p-views a").filter(visible).shouldHave(texts(expectedLinks));
    }


}
