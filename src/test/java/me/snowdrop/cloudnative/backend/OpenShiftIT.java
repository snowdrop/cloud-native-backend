package me.snowdrop.cloudnative.backend;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.arquillian.cube.openshift.impl.enricher.AwaitRoute;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyString;

@RunWith(Arquillian.class)
public class OpenShiftIT  {

    @AwaitRoute(path = "/health")
    @RouteURL("cloud-native-backend")
    private URL baseURL;

    @Before
    public void setup() throws Exception {
        RestAssured.baseURI = baseURL + "api/notes";
    }

    @Test
    public void testPostGetAndDelete() {
        //create a new note
        Integer id = given()
                .contentType(ContentType.JSON)
                .body(new HashMap<String, String>() {{
                    put("title", "excellent");
                    put("content", "cherry");
                }})
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("id", not(isEmptyString()))
                .body("title", CoreMatchers.is("excellent"))
                .extract()
                .response()
                .path("id");

        //fetch the note by id
        when().get(id.toString())
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.is(id))
                .body("title", CoreMatchers.is("excellent"));

        //delete the note
        when().delete(id.toString())
                .then()
                .statusCode(200);
    }
}
