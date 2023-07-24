package com.resources;
import com.domain.Employee;
import com.services.EmployeeFunctionService;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/restfullapp")
public class Resource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieTitlesFromApp()  throws ClassNotFoundException{
       return new Gson().toJson(EmployeeFunctionService.getAllEmployees());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createEmployee(String employee) throws ClassNotFoundException{
        EmployeeFunctionService.addEmployee(new Gson().fromJson(employee, Employee.class));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void changeEmployeeData(String employee) throws ClassNotFoundException{
        EmployeeFunctionService.updateEmployeeByID(new Gson().fromJson(employee, Employee.class));
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteEmployee(String id) throws ClassNotFoundException {
        EmployeeFunctionService.deleteEmployeeByID(new Gson().fromJson(id, Employee.class).getID());
    }
}