package tests.ui;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.ui.pages.GitHubPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.id;

public class GitHubUITests extends TestBase {

    public final static String URL = "https://github.com";
    private final static String REPOSITORY = "Suneks/breakingBadApp";

    GitHubPage gitHubPage = new GitHubPage();

    @Test
    @DisplayName("Поиск репозитория через поисковую строку")
    void searchRepo() {
        gitHubPage.openPage(URL)
                .search(REPOSITORY);
    }

    @Test
    @DisplayName("Авторизация пользователя с неверным паролем")
    void authorizationUserWithNotRightPassword() {
        gitHubPage.openPage(URL);
        $(byText("Sign in")).click();
        $(id("login_field")).setValue("test");
        $(id("password")).setValue("12345678");
        $("[value='Sign in']").click();
//        Selenide.sleep(100);
        $(byText("There have been several failed attempts to sign in from this account or IP address. Please wait a while and try again later.")).shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Открытие вкладки team")
    void openTabFeature() {
        gitHubPage.openPage(URL)
                .clickTeamPage()
                .checkOpenedTeamPage();
    }

    @Test
    @DisplayName("Поиск раздела Issue в репозитории")
    void searchIssueTabTest() {
        gitHubPage.openPage(URL)
                .search(REPOSITORY)
                .goToRepository(REPOSITORY)
                .checkOpenedIssue();
    }

    @Test
    @DisplayName("Открытие страницы Careers")
    void openShopPage() {
        gitHubPage.openPage(URL)
                .clickCareers()
                .checkOpenedCareersPage();
    }
}


