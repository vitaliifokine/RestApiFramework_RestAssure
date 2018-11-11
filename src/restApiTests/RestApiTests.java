package restApiTests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestApiTests {
    
    @Feature("REST API - LAUREATS")
    @Test
    public void getNobelLaureat() {
        RestAssured.baseURI = "http://api.nobelprize.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/v1/laureate.json");
        String responseBody = response.getBody().asString();
        assertThat(response.getBody().asString(),
                containsString("Paul Hermann"));
    }

    @Feature("REST API - CITIES FROM COUNTRIES")
    @Test
    public void getCityFromCountryStatusValidation() {
        RestAssured.baseURI = "http://api.nobelprize.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/v1/laureate.json");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
    }

    @Feature("REST API - NOBEL PRIZE LAUREAT")
    @Test
    public void getFirstLaureatLastNameJSON() {
        RestAssured.baseURI = "http://api.nobelprize.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/v1/laureate.json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> firstNamesOfAllLaureates = jsonPathEvaluator.getList("laureates.firstname");
        List<String> lastNamesOfAllLaureates = jsonPathEvaluator.getList("laureates.surname");
        Assert.assertEquals(firstNamesOfAllLaureates.get(0) + " " +
                        lastNamesOfAllLaureates.get(0), "Wilhelm Conrad Röntgen",
                "Correct laureate first & last name received in the Response");
    }

    @Feature("REST API - NOBEL PRIZE LAUREAT")
    @Test
    public void getFirstLaureatLastNameJSON1() {
        RestAssured.baseURI = "http://api.nobelprize.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/v1/laureate.json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> firstNamesOfAllLaureates = jsonPathEvaluator.getList("laureates.firstname");
        List<String> lastNamesOfAllLaureates = jsonPathEvaluator.getList("laureates.surname");
       for (int ind=1; ind<firstNamesOfAllLaureates.size(); ind++){
           System.out.println(firstNamesOfAllLaureates.get(ind) + " " + lastNamesOfAllLaureates.get(ind));
       }

    }

    @Feature("REST API - NOBEL PRIZE LAUREAT")
    @Test
    public void getFirstLaureatLastNameJSON2() {
        RestAssured.baseURI = "http://api.nobelprize.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/v1/laureate.json");
        response.print();


    }

    @Feature("REST API - NOBEL PRIZE LAUREAT")
    @Test
    public void getFirstLaureatLastNameJSON3() {
        RestAssured.baseURI = "http://api.nobelprize.org";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/v1/laureate.json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> firstNamesOfAllLaureates = jsonPathEvaluator.getList("laureates.firstname");
        List<String> lastNamesOfAllLaureates = jsonPathEvaluator.getList("laureates.surname");
        List<String> died = jsonPathEvaluator.getList("laureates.diedCountry");
        for (int ind=1; ind<firstNamesOfAllLaureates.size(); ind++){
            if (died.get(ind)=="Germany") {
                System.out.println(firstNamesOfAllLaureates.get(ind) + " " + lastNamesOfAllLaureates.get(ind) + " " + died.get(ind));
            }
        }
    }
}