package com.resources;
import com.domain.Employee;
import com.services.EmployeeFunctionService;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;


@Path("/restfullapp")
public class Resource {

    private static final Logger logger = Logger.getLogger(Resource.class.getName());
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieTitlesFromApp()  throws ClassNotFoundException{
       return new Gson().toJson(EmployeeFunctionService.getAllEmployees());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createNewMovieEntry(String employee) throws ClassNotFoundException{
        logger.info("Kesa hai");
        Employee employee1 = new Gson().fromJson(employee, Employee.class);
        logger.info(employee1.getIDString());
        EmployeeFunctionService.addEmployee(employee1);
        logger.info(employee);
        return employee;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String changeMovieEntry(String employee) throws ClassNotFoundException{
        Employee employee1 = new Gson().fromJson(employee, Employee.class);
        EmployeeFunctionService.updateEmployeeByID(employee1);
        return employee;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteMovieEntry(String id) throws ClassNotFoundException {
        EmployeeFunctionService.deleteEmployeeByID(Integer.parseInt(id));
        return id;
    }
}