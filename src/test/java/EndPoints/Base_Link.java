package EndPoints;

import static io.restassured.RestAssured.*;

public class Base_Link {

    public static String baseURI = "http://localhost:3000";
    public static String PostUrl = baseURI+"/employees";
    public static String GetUrl = baseURI+"/employees/{id}";
    public static String UpdateUrl = baseURI+"/employees/{id}";
    public static String DeleteUrl = baseURI+"/employees/{id}";

    
}
