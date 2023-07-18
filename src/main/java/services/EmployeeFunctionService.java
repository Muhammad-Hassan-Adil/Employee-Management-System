package services;
import domain.Employee;
import java.sql.*;
import java.util.*;
public class EmployeeFunctionService {
    public Employee getEmployeeByID(int ID) throws ClassNotFoundException{
        try {
            Connection connection = DBConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
            while (resultSet.next()) {
                employee.setName(resultSet.getString("name"));
                employee.setID(resultSet.getInt("ID"));
                employee.setAge(resultSet.getInt("age"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
            }
            return employee;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Employee getEmployeeByName(String name) throws ClassNotFoundException{
        try {
            Connection connection = DBConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
            while (resultSet.next()) {
                employee.setName(resultSet.getString("name"));
                employee.setID(resultSet.getInt("ID"));
                employee.setAge(resultSet.getInt("age"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
            }
            return employee;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void addEmployee(Employee employee) throws ClassNotFoundException{
        try {
            Connection connection = DBConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT IGNORE INTO Employee (name, ID, age, salary) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getID());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setBigDecimal(4, employee.getSalary());
            preparedStatement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmployeeByID(int id) throws ClassNotFoundException{
        try {
            Connection connection = DBConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Employee SET name = ?, age = ?, salary = ? WHERE ID = ?");
            preparedStatement.setString(1, "Hassan");
            preparedStatement.setInt(2, 20);
            preparedStatement.setDouble(3, 100000);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployeeByID(int ID) throws ClassNotFoundException{
        try {
            Connection connection = DBConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Employee WHERE ID = ?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //function to get all employees
    public static List<Employee> getAllEmployees() throws ClassNotFoundException{
        try {
            Connection connection = DBConnectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setName(resultSet.getString("name"));
                employee.setID(resultSet.getInt("ID"));
                employee.setAge(resultSet.getInt("age"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                employees.add(employee);
            }
            return employees;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

