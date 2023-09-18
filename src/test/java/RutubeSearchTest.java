import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RutubeSearchTest extends BaseTest {

    @BeforeEach
    void page() {
        open("https://rutube.ru/");
    }

    @CsvFileSource(resources = "RutubeSearchTest.csv")
    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @DisplayName("Проверка поиска с названием новостных каналов в rutube.ru")

    @ParameterizedTest(name = "При вводе названия новостного канала {1} выводится строка {2}")
    void testMethod(String City, String Result) {
        $(".freyja_char-header-search__input__ln9vI").setValue(City).pressEnter();
        $(".pen-search-author__description").shouldHave(text(Result));
    }
}
