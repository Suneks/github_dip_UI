package tests.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import helpers.DriverUtils;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.ui.pages.GitHubPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

public class GitHubUITests extends TestBase {

    public final static String BASE_URL = "https://github.com";
    private final static String REPOSITORY = "Suneks/demo_qa1";

    GitHubPage gitHubPage = new GitHubPage();

    @Test
    @DisplayName("Поиск репозитория через поисковую строку")
    void searchRepo() {
        gitHubPage.openPage(BASE_URL)
                  .search(REPOSITORY);
    }

    @Test
    @DisplayName("Авторизация пользователя с неверным паролем")
    void authorizationUserWithNotRightPassword() {
        gitHubPage.openPage(BASE_URL);
        $(byText("Sign in")).click();
        $(id("login_field")).setValue("test");
        $(id("password")).setValue("12345678");
        $("[value='Sign in']").click();
        Selenide.sleep(100);
        $(byText("There have been several failed attempts to sign in from this account or IP address. Please wait a while and try again later.")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Открытие вкладки team")
    void openTabFeature() {
        gitHubPage.openPage(BASE_URL)
                .clickTeamPage()
                .checkOpenedTeamPage();
    }

    @Test
    @DisplayName("Заголовок страницы должен содержать текст заголовка")
    void titleTest() {
        step("Open url 'https://github.com/'", () ->
                open("https://github.com/"));

        step("Page title should have text 'GitHub: Where the world builds software · GitHub'", () -> {
            String expectedTitle = "GitHub: Where the world builds software · GitHub";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("В консоли не должно быть ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://github.com/'", () ->
                open("https://github.com/"));

        step("Console log should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}