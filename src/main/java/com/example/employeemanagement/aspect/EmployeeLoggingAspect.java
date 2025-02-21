package com.example.employeemanagement.aspect;

import com.example.employeemanagement.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeLoggingAspect {
    
    @Before("execution(* com.example.employeemanagement.service.EmployeeService.saveEmployee(..))")
    public void beforeSavingEmployee(JoinPoint joinPoint) {
        Employee employee = (Employee) joinPoint.getArgs()[0];
        System.out.println("About to save employee: " + employee.getName());
    }

    @After("execution(* com.example.employeemanagement.service.EmployeeService.deleteEmployee(..))")
    public void afterDeletingEmployee(JoinPoint joinPoint) {
        Long employeeId = (Long) joinPoint.getArgs()[0];
        System.out.println("Employee deleted with ID: " + employeeId);
    }

    @Around("execution(* com.example.employeemanagement.service.EmployeeService.getAllEmployees())")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Method execution took: " + (end - start) + " ms");
        return result;
    }
}