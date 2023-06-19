package Employee;

import EndPoints.Base_Link;
import Payload_POJO.EmployeeInformation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Employee {

    public static Response Create(EmployeeInformation  payload)
    {
        System.out.println(Base_Link.PostUrl);
        Response response = given().
                                 contentType(ContentType.JSON).
                                 accept(ContentType.JSON).
                                 body(payload).
                            when().
                                 post("http://localhost:3000/employees");

        return response;
    }

    public static Response Read(int id)
    {
        Response response = given().
                                   pathParam("id",id).
                            when().
                                    get(Base_Link.GetUrl);

        return response;
    }

    public static Response Update(EmployeeInformation payload,int id)
    {
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(payload).
                pathParam("id",id).
                when().
                put(Base_Link.UpdateUrl);

        return response;
    }

    public static Response Delete(int id)
    {
//        int id1 = Integer.parseInt(id);
//        System.out.println(id1);
        Response response = given().
                pathParam("id",id).
                            when().
                            delete(Base_Link.DeleteUrl);

        return response;
    }
}
