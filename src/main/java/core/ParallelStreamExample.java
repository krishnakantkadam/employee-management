package core;

import java.util.*;
import java.util.stream.Collectors;
/**
 * we have a list of employees, and we need to:
 *
 * Filter employees earning more than 50,000.
 * Increase their salaries by 10%.
 * Collect the updated employee list.
 * Instead of processing this sequentially, we will use parallel streams to speed up the computation.
 *
 * Why Use Parallelism Here?
 * If the employee list is large (millions of records), parallel processing significantly reduces processing time.
 * Multi-core utilization → Instead of one core processing everything, multiple cores handle different parts of the list simultaneously.
 * */
class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + salary + '}';
    }
}

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", 60000),
            new Employee(2, "Bob", 48000),
            new Employee(3, "Charlie", 75000),
            new Employee(4, "David", 52000),
            new Employee(5, "Eve", 90000)
        );

        // Process employees in parallel
        List<Employee> updatedEmployees = employees.parallelStream()
            .filter(emp -> emp.getSalary() > 50000) // Only employees earning > 50k
            .peek(emp -> emp.setSalary(emp.getSalary() * 1.10)) // Increase salary by 10%
            .collect(Collectors.toList()); // Collect results

        // Print updated employees
        updatedEmployees.forEach(System.out::println);
    }
}