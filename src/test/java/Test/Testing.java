package Test;

import Employee.Employee;
import Payload_POJO.EmployeeInformation;
import Utillities.Xls_Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Objects;

public class Testing {
    EmployeeInformation payload = new EmployeeInformation();

    @BeforeClass
    public void setup()
    {

    }
    @Test(enabled = false)
    public void Creating_new_data() throws JsonProcessingException {
        Xls_Reader Reader = new Xls_Reader("D:\\IntelliJ\\EmployeeAPI\\src\\test\\resources\\TestData.xlsx");


        int rowcount = Reader.getRowCount("CREATE");
        for (int row=2;row<=rowcount;row++)
        {
            String Execution_Status = Reader.getCellData("CREATE","Execution Status",row);
            String First_Name = Reader.getCellData("CREATE","first_name",row);
            String Last_Name = Reader.getCellData("CREATE","last_name",row);
            String Email = Reader.getCellData("CREATE","email",row);
            if (Execution_Status.equals("Y"))
            {
                if(First_Name != null && !First_Name.isEmpty())
                {
                    payload.setFirst_name(First_Name);
                }
                if (Last_Name != null && !Last_Name.isEmpty())
                {
                    payload.setLast_name(Last_Name);
                }
                if (Email != null && !Email .isEmpty())
                {
                    payload.setEmail(Email);
                }

               Response Res = Employee.Create(payload);

                Res.then().log().all().statusCode(201);
            }
            }




    }

    @Test(enabled = false)
    public void Getting_data()
    {
        Xls_Reader Reader = new Xls_Reader("D:\\IntelliJ\\EmployeeAPI\\src\\test\\resources\\TestData.xlsx");
        int rowcount = Reader.getRowCount("GET");
        for (int row=2;row<=rowcount;row++)
        {
            String Execution_Status = Reader.getCellData("GET","Execution Status",row);
            String id = Reader.getCellData("GET","id",row);
            float val = Float.parseFloat(id);
            int id1 = (int) val;
            Response Res = Employee.Read(id1);
            Res.then().log().all().statusCode(200);
        }

    }

    @Test(enabled = true)
    public void Updating_data()
    {
        Xls_Reader Reader = new Xls_Reader("D:\\IntelliJ\\EmployeeAPI\\src\\test\\resources\\TestData.xlsx");
        int rowcount = Reader.getRowCount("UPDATE");
        for (int row=2;row<=rowcount;row++)
        {
            String Execution_Status = Reader.getCellData("UPDATE","Execution Status",row);
            String First_Name = Reader.getCellData("UPDATE","first_name",row);
            String Last_Name = Reader.getCellData("UPDATE","last_name",row);
            String Email = Reader.getCellData("UPDATE","email",row);
            String id = Reader.getCellData("UPDATE","id",row);
            float val = Float.parseFloat(id);
            int id1 = (int) val;
            System.out.println(id1);
            if (Execution_Status.equals("Y"))
            {
                if(!First_Name.isEmpty())
                {
                    payload.setFirst_name(First_Name);
                }
                if (!Last_Name.isEmpty())
                {
                    payload.setLast_name(Last_Name);
                }
                if (!Email .isEmpty())
                {
                    payload.setEmail(Email);
                }

                Response Res = Employee.Update(payload,id1);
                Res.then().log().body().statusCode(200);
            }
        }

    }


    @Test(enabled = false)
    public void Test_case_04_Deleting_data()
    {
        Xls_Reader Reader = new Xls_Reader("D:\\IntelliJ\\EmployeeAPI\\src\\test\\resources\\TestData.xlsx");
        int rowcount = Reader.getRowCount("DELETE");
        for (int row=2;row<=rowcount;row++)
        {
            String Execution_Status = Reader.getCellData("DELETE","Execution Status",row);
            String id = Reader.getCellData("DELETE","id",row);
            float val = Float.parseFloat(id);
            int id1 = (int) val;
            if (Execution_Status.equalsIgnoreCase("Y"))
            {
               Response Res = Employee.Delete(id1);
                Res.then().log().all().statusCode(200);
            }

        }

    }
}
