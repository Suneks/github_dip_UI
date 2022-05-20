package tests.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubPage {

    @Step("Open page {url}")
    public GitHubPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Search {name}")
    public GitHubPage search(String name) {
        $(".header-search-input").click();
        $(".header-search-input").val(name).pressEnter();
        return this;
    }

    @Step("Go to repository")
    public GitHubPage goToRepository(String repository) {
        $(By.linkText(repository)).click();
        return this;
    }

    @Step("Click team page")
    public GitHubPage clickTeamPage() {
        $(".HeaderMenu-link", 1).click();
        return this;
    }

    @Step("Click button careers")
    public GitHubPage clickCareersShop() {
        $(byText("Careers")).scrollTo().click();
        return this;
    }

    @Step("Check opened issue")
    public GitHubPage checkOpenedIssue() {
        $("[data-tab-item='i1issues-tab']").click();
        $(".blankslate h3").shouldHave(text("Welcome to issues!"));
        return this;
    }

    @Step("Check opened team page")
    public GitHubPage checkOpenedTeamPage() {
        $(".h1-mktg").shouldHave(text("Build like the best teams on the planet"));
        return this;
    }

    @Step("Check opened careers page")
    public GitHubPage checkOpenedCareersPage() {
        $(byText("Come build the home for all developers")).shouldBe(visible);
        return this;
    }
}