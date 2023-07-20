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

//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String changeMovieEntry(String payLoad){
//        boolean context = App.changeMovieEntryInDatabase(payLoad);
//        if(context)
//            return Response.ok("Successfully Changed!",MediaType.TEXT_PLAIN).build().toString();
//        return Response.notModified("The movie could not be changed ").build().toString();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String deleteMovieEntry(@PathParam("id") int movieId) {
//        boolean context = App.deleteMovieEntryFromDatabase(movieId);
//        if (context) {
//            return Response.ok("Successfully Deleted!", MediaType.TEXT_PLAIN).build().toString();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build().toString();
//        }
//    }
}