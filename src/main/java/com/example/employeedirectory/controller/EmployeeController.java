package com.example.employeedirectory.controller;

import com.example.employeedirectory.entity.Employee;
import com.example.employeedirectory.entity.Message;
import com.example.employeedirectory.service.EmployeeService;
import com.example.employeedirectory.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/showMyLoginPage")
    public String showLoginPage() {
        return "fancy-login";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userRole", userDetails.getAuthorities().toString());

        List<Message> messages = messageService.getAllMessages();
        if (messages == null) {
            messages = List.of(); // Initialize to an empty list if null
        }
        model.addAttribute("messages", messages);

        return "home";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/home";
    }

    @GetMapping("/systems")
    public String showSystemsPage() {
        return "systems";
    }

    @GetMapping("/leaders")
    public String showLeadersPage() {
        return "leaders";
    }

    @GetMapping("/add-employee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            employeeService.saveEmployee(employee, username, password);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return "error"; // Redirect to an error page
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "fancy-login";
    }

    @GetMapping("/some-page")
    public String somePage() {
        // Redirect to home or another page to prevent accessing the previous page
        return "redirect:/home";
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam("content") String content) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Message message = new Message();
        message.setContent(content);
        message.setSentBy(username);
        message.setSentAt(LocalDateTime.now());

        messageService.saveMessage(message);
        return "redirect:/home";
    }
}
