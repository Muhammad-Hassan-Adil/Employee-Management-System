package resources;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import domain.Employee;
import services.EmployeeFunctionService;
import java.math.BigDecimal;
import java.util.*;
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
            EmployeeFunctionService.addEmployee(employee);
            String message = "Hello, My name is " + n + "! My id is " + i + ", My Age is: " + age + ", My Salary is: " + sal + "\n";
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
    @Path("/getuserID/{ID}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getEmployee(
            @PathParam("ID") int i
    ) throws ClassNotFoundException {
        try{
            Employee employee = EmployeeFunctionService.getEmployeeByID(i);
            String message = "Hello, My name is " + employee.getName() + "! My id is " + employee.getID() + ", My Age is: " + employee.getAge() + ", My Salary is: " + employee.getSalary() + "\n";
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
    @Path("/getuserName/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getEmployee(
            @PathParam("name") String n
    ) throws ClassNotFoundException {
        try{
            Employee employee = EmployeeFunctionService.getEmployeeByName(n);
            String message = "Hello, My name is " + employee.getName() + "! My id is " + employee.getID() + ", My Age is: " + employee.getAge() + ", My Salary is: " + employee.getSalary() + "\n";
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
    @Path("/updateuserID/{ID}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
public Response updateEmployee(
            @PathParam("ID") int i,
            @QueryParam("name") String n,
            @QueryParam("age") int age,
            @QueryParam("salary") BigDecimal sal
    ) throws ClassNotFoundException {
        try{
            Employee employee = new Employee(n, i, age, sal);
            EmployeeFunctionService.updateEmployeeByID(employee);
            String message = "Hello, My name is " + n + "! My id is " + i + ", My Age is: " + age + ", My Salary is: " + sal + "\n";
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
    // delete employee bby ID
    @Path("/deleteuserID/{ID}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEmployee(
            @PathParam("ID") int i
    ) throws ClassNotFoundException {
        try{
            EmployeeFunctionService.deleteEmployeeByID(i);
            String message = "Hello, My id is " + i + "!\n";
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
    // get all employees
    @Path("/getallusers")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllEmployees() throws ClassNotFoundException {
        try{
            List<Employee> employees = EmployeeFunctionService.getAllEmployees();
            String message = "";
            for (Employee employee : employees) {
                message += "Hello, My name is " + employee.getName() + "! My id is " + employee.getID() + ", My Age is: " + employee.getAge() + ", My Salary is: " + employee.getSalary() + "\n";
            }
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred while inserting into the database").build();
        }
    }
}