import Api.UserStepsApi;
import Api.UserStepsApiChecks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import Models.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Pageobject.BasePage;
import Randomdata.UserGeneratorData;
import java.io.IOException;
import static Browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;


@Epic("User's registration")
public class RegistrationUserTest {
    private ValidatableResponse response;
    private User user;
    private BasePage basePage;
    private static String accessToken;

    @Before
    public void setUp() throws IOException {
        //initialization our Browser
        initDriver();
        Configuration.timeout = 4000;

        //creation of user
        user = UserGeneratorData.getRandomUser();
        response = UserStepsApi.createUser(user);
        accessToken = response.extract().path("accessToken");

        //create an instance of BasePage class
        basePage = open(BasePage.BASE_URL, BasePage.class);

        ValidatableResponse loginResponse = UserStepsApi.loginUser(user);
        accessToken = loginResponse.extract().path("accessToken");
    }

    @After
    public void clearState() {

        //login user
        response = UserStepsApi.loginUser(user);
        UserStepsApiChecks.checkUserLoginByValidCredentialsSuccess(response);

        //user deletion
        if (accessToken != null) {
            response = UserStepsApi.deleteUser(StringUtils.substringAfter(accessToken, " "));
            UserStepsApiChecks.checkUserDeleteByValidCredentials(response);
        }
        //clear browser
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Register user by valid credentials")
    public void registerUserByValidCredentials() {
        User user = UserGeneratorData.getRandomUser();
        Allure.addAttachment("Generated email", user.getEmail());
        Allure.addAttachment("Generated password", user.getPassword());
        Allure.addAttachment("Generated name", user.getName());
        basePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        $x(".//button[text()='Зарегистрироваться']").click();
        WebDriverRunner.url().contains("/login");
        $(".text_type_main-default").shouldBe(Condition.visible);
    }


    @Test
    @DisplayName("Register user by invalid password")
    public void registerUserByInvalidPassword() {
        boolean isDisplayed = basePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), "yes7")
                .clickRegisterButton(Condition.visible);
        assertTrue(isDisplayed);
    }

    @Test
    @DisplayName("Register user is displayed password error")
    public void registerUserIsDisplayedPasswordError() {
        boolean isDisplayed = basePage.clickLoginButton()
                .clickRegisterLink()
                .fillRegisterForm(user.getName(), user.getEmail(), "yes7")
                .isDisplayedPasswordError();
        assertTrue(isDisplayed);
    }
}