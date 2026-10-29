package Api;

import Constants.EndPoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import Models.User;
import static Api.RestApi.getBaseSpec;
import static io.restassured.RestAssured.given;


public class UserStepsApi {

    @Step("Send POST request to /api/auth/register")
    public static ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .post(EndPoints.USER_AUTHENTICATION_API + "register")
                .then()
                .log().all();
    }

    @Step("Send POST request to /api/auth/login")
    public static ValidatableResponse loginUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .post(EndPoints.USER_AUTHENTICATION_API + "login")
                .then()
                .log().all();
    }



    @Step("Send DELETE request to /api/auth/user")
    public static ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .log().all()
                .delete(EndPoints.USER_DELETION)
                .then()
                .log().all();
    }
}