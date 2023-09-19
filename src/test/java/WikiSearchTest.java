
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WikiSearchTest extends BaseTest {
    @BeforeEach
    public void page() {
        open("https://www.wikipedia.org/");
    }

    static Stream<String> searchQuery() {
        return Stream.of("Venus", "Andromeda", "USSR");
    }

    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @DisplayName("Проверка поиска в Wikipedia.org")
    @ParameterizedTest(name = "После ввода и поиска {0} отобразилась страница с информацией {0}")
    @MethodSource("searchQuery")

    public void searchTest(String searchQuery) {

        $("#searchInput").setValue(searchQuery);
        $(".pure-button-primary-progressive").click();
        $(".mw-parser-output").shouldHave(text(searchQuery));
    }
}
