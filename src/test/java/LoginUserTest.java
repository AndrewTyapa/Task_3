import Api.UserStepsApi;
import Api.UserStepsApiChecks;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Pageobject.BasePage;
import Randomdata.UserGeneratorData;
import java.io.IOException;
import static Browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertFalse;


@Epic("Login user")
public class LoginUserTest {
    private ValidatableResponse response;
    private User user;
    private BasePage basePage;
    private static String accessToken;

    @Before
    public void setUp() throws IOException {
        //initialization our Browser
        initDriver();
        Configuration.timeout = 4000;
        //create an instance of BasePage class
        basePage = open(BasePage.BASE_URL, BasePage.class);

        //creation of user
        user = UserGeneratorData.getRandomUser();
        response = UserStepsApi.createUser(user);
        accessToken = response.extract().path("accessToken");

        basePage = null;
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
        //user deletion`
        if (accessToken != null) {
            response = UserStepsApi.deleteUser(accessToken);
        }
        //clear browser
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Login user by login button 'Войти в аккаунт'")
    public void loginUserByLoginButton() {
        boolean isDisplayed = basePage.clickLoginButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by personal cabinet button 'Личный кабинет'")
    public void loginUserByAccountButton() {
        accessToken = response.extract().path("accessToken");
        boolean isDisplayed = basePage.clickPersonalCabinetButton()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user on the registration page")
    public void loginUserByRegisterPage() {
        accessToken = response.extract().path("accessToken");
        boolean isDisplayed = basePage.clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user on the password recovery page")
    public void loginUserByForgotPasswordPage() {
        accessToken = response.extract().path("accessToken");
        boolean isDisplayed = basePage.clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink()
                .fillLoginForm(user.getEmail(), user.getPassword())
                .clickLoginButton(Condition.hidden);
        assertFalse(isDisplayed);
    }
}