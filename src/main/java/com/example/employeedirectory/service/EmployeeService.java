package com.example.employeedirectory.service;

import com.example.employeedirectory.entity.Employee;
import com.example.employeedirectory.entity.Message;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
    void saveEmployee(Employee employee, String username, String password);
    void saveEmployee(Employee employee);
    List<Message> getAllMessages(); // Add this method
    void saveMessage(Message message);
}
