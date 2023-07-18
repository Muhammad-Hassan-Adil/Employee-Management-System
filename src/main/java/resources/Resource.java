package resources;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.Employee;
import services.DBConnectionService;

import java.math.BigDecimal;
import java.sql.*;
@Path("/restfullapp")
public class Resource {
    @Path("/adduser")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response addEmployee(
            @QueryParam("name") String n,
            @QueryParam("ID") int i,
            @QueryParam("age") int age,
            @QueryParam("salary") BigDecimal sal
    ) throws ClassNotFoundException {
        try{
            Employee employee = new Employee(n, i, age, sal);
            String message = "Hello, My name is " + n + "! My id is " + i + ", My Age is: " + age + ", My Salary is: " + sal + "\n";
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
}