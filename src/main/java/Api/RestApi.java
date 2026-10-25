package Api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static Constants.EndPoints.BASE_URL;
import static io.restassured.http.ContentType.JSON;

public class RestApi {

    protected static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}