package com.example.employeedirectory.service;

import com.example.employeedirectory.entity.Employee;
import com.example.employeedirectory.entity.Message;
import com.example.employeedirectory.repository.EmployeeRepository;
import com.example.employeedirectory.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void saveEmployee(Employee employee, String username, String password) {
        employeeRepository.save(employee);
        jdbcTemplate.update("INSERT INTO users (username, password, enabled) VALUES (?, ?, true)", username, password);
        jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?, 'ROLE_EMPLOYEE')", username);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
