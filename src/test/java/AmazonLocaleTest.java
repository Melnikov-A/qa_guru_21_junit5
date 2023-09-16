import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class AmazonLocaleTest extends BaseTest {
    @BeforeEach
    public void page() {
        open("https://aws.amazon.com/");
    }

    public static Stream<Arguments> languagesQuery() {
        return Stream.of(
                Arguments.of(Local.EN, "Explore Our Solutions"),
                Arguments.of(Local.IT, "Esplora le nostre soluzioni"));
    }

    @Tags({
            @Tag("web"),
            @Tag("locale")
    })
    @DisplayName("Проверка текста страницы в зависимости от локации")
    @ParameterizedTest(name = "При смене локации на {0} отображается {1}")
    @MethodSource("languagesQuery")
    void LocaleTest(Local locale, String title) {
        $("#m-nav-language-selector").click();
        $("#popover-language-selector").$(byText(locale.getDisplayName())).shouldBe(visible).shouldBe(enabled).click();
        $("#Explore_Our_Solutions").shouldHave(text(title));
    }
}
