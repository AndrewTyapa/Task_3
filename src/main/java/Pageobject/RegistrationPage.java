package Pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default']")
    public ElementsCollection registerForm;
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    public SelenideElement passwordError;

    @Step("Fill name field")
    public void setNameField(String name) {

        registerForm.get(0).click();
        registerForm.get(0).clear();
        registerForm.get(0).setValue(name);
    }

    @Step("Fill email field")
    public void setEmailField(String email) {
        registerForm.get(1).click();
        registerForm.get(1).clear();
        registerForm.get(1).setValue(email);
    }

    @Step("Fill password field")
    public void setPasswordField(String password) {
        registerForm.get(2).click();
        registerForm.get(2).clear();
        registerForm.get(2).setValue(password);
        registerForm.get(2).pressTab(); // Смещаем фокус, активируя кнопку формы
    }

    @Step("Fill register form")
    public RegistrationPage fillRegisterForm(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        return this;
    }

    @Step("Click register button")
    public boolean clickRegisterButton(Condition condition) {
        registerButton.click();
        registerButton.shouldBe(condition);
        return registerButton.isDisplayed();
    }

    @Step("Is displayed password error")
    public boolean isDisplayedPasswordError() {
        registerButton.click();
        return passwordError.isDisplayed();
    }

    @Step("Click login link")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return page(LoginPage.class);
    }
}
