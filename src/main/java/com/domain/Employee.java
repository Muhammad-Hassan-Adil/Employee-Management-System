package com.domain;
import java.math.BigDecimal;

public class Employee {
    private String name;
    private int ID,age;
    private BigDecimal salary;
    public Employee() {}
    public Employee(String name, int ID, int age, BigDecimal salary) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    }
    public String getSalaryString() {return salary.toString();}
    public String getAgeString() {return Integer.toString(age);}
    public String getIDString() {
        return Integer.toString(ID);
    }
    public int getAge() {
        return age;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
