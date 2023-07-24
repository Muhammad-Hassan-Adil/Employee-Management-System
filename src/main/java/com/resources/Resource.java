package com.resources;
import com.domain.Employee;
import com.services.EmployeeFunctionService;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.*;

@Path("/restfullapp")
public class Resource {
    private static final Logger logger = LogManager.getLogger(Resource.class.getName());
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieTitlesFromApp()  throws ClassNotFoundException{
        ThreadContext.put("httpMethod", "GET");
        try {
            String employees = new Gson().toJson(EmployeeFunctionService.getAllEmployees());
            logger.info("Employees Retrieved");
            return employees;
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
            return null;
        }
        finally {
            ThreadContext.remove("httpMethod");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createEmployee(String employee) throws ClassNotFoundException{
        ThreadContext.put("httpMethod", "POST");
        try {
            EmployeeFunctionService.addEmployee(new Gson().fromJson(employee, Employee.class));
            logger.info("Employee Added");
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        finally {
            ThreadContext.remove("httpMethod");
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void changeEmployeeData(String employee) throws ClassNotFoundException{
        ThreadContext.put("httpMethod", "PUT");
        try {
            EmployeeFunctionService.updateEmployeeByID(new Gson().fromJson(employee, Employee.class));
            logger.info("Employee Updated");
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        finally {
            ThreadContext.remove("httpMethod");
        }
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteEmployee(String id) throws ClassNotFoundException {
        ThreadContext.put("httpMethod", "DELETE");
        try {
            EmployeeFunctionService.deleteEmployeeByID(new Gson().fromJson(id, Employee.class).getID());
            logger.info("Employee Deleted");
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        finally {
            ThreadContext.remove("httpMethod");
        }
    }
}