package com.example.employeemanagement.aspect;

import com.example.employeemanagement.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
    @Before("execution(* com.example.employeemanagement.service.EmployeeService.saveEmployee(..))")
    public void validateEmployee(JoinPoint joinPoint) {
        Employee employee = (Employee) joinPoint.getArgs()[0];
        if (employee.getSalary() < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }
}