package com.example.employeemanagement.aspect;

import com.example.employeemanagement.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationAspectTest {

    @InjectMocks
    private ValidationAspect validationAspect;

    @Mock
    private JoinPoint joinPoint;

    @Test
    void validateEmployee_WithNegativeSalary_ShouldThrowException() {
        // Arrange
        Employee employee = new Employee();
        employee.setSalary(-1000);
        Object[] args = {employee};
        when(joinPoint.getArgs()).thenReturn(args);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
            validationAspect.validateEmployee(joinPoint)
        );
    }

    @Test
    void validateEmployee_WithPositiveSalary_ShouldNotThrowException() {
        // Arrange
        Employee employee = new Employee();
        employee.setSalary(1000);
        Object[] args = {employee};
        when(joinPoint.getArgs()).thenReturn(args);

        // Act & Assert
        validationAspect.validateEmployee(joinPoint); // Should not throw exception
    }
}
