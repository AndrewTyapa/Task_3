package Pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class BasePage {
    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement personalCabinetButton;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__list__2A-mT")
    private ElementsCollection menuIngredients;
    @FindBy(how = How.XPATH, using = ".//div[contains(@class,'tab_tab_type_current')]")
    private SelenideElement currentSectionOfIngredients;

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

    @Step("Verify that the 'Buns' tab is active and section is displayed")
    public void verifyBunsTabIsActive() {
        SelenideElement bunTab = menuIngredients.get(0).lastChild();
        bunTab.scrollIntoView(true);
        bunTab.click();
        currentSectionOfIngredients.should(Condition.visible);
    }

    @Step("Verify that the 'Sauces' tab is active and section is displayed")
    public void verifySaucesTabIsActive() {
        SelenideElement sauceTab = menuIngredients.get(1).lastChild();
        sauceTab.scrollIntoView(true);
        sauceTab.click();
        currentSectionOfIngredients.should(Condition.visible);
    }

    @Step("Verify that the 'Fillings' tab is active and section is displayed")
    public void verifyFillingsTabIsActive() {
        SelenideElement fillingTab = menuIngredients.get(2).lastChild();
        fillingTab.scrollIntoView(true);
        fillingTab.click();
        currentSectionOfIngredients.should(Condition.visible);
    }
}