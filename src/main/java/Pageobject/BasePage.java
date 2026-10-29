package Pageobject;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BasePage {
    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    private final SelenideElement personalCabinetButton = $x(".//p[text()='Личный Кабинет']");
    private final SelenideElement loginButton = $x(".//button[text()='Войти в аккаунт']");


    @Step("Click account button")
    public LoginPage clickPersonalCabinetButton() {
        personalCabinetButton.click();
        return page(LoginPage.class);
    }

    @Step("Click account button, go to personal cabinet page")
    public UserPersonalCabinetPage clickPersonalCabinetButtonGoAccountPage() {
        personalCabinetButton.click();
        return page(UserPersonalCabinetPage.class);
    }

    @Step("Click login button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Выбрать вкладку и проверить её активность по тексту")
    public void selectTabAndAssertActive(String tabName) {
        // Находим вкладку, которая содержит спан с искомым названием
        SelenideElement targetTab = $x(".//div[contains(@class, 'tab_tab') and .//span[text()='" + tabName + "']]");
        // Кликаем, чтобы обойти перекрытия фиксированной шапки сайта
        targetTab.click(ClickOptions.usingJavaScript());
        sleep(600);
        // Находим текущую активную вкладку и проверяем её текст
        $("div[class*='tab_tab_type_current'] span").shouldHave(exactText(tabName));
    }
}