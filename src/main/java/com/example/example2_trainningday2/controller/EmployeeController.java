package com.example.example2_trainningday2.controller;

import com.example.example2_trainningday2.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        if (isExit(employee.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Id already exists");
        }
        if (emailExit(employee.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        int maxId = getMaxEmployeeId();
        employee.setId(maxId + 1);
        employeeList.add(employee);
        return ResponseEntity.ok("success");
    }

    private int getMaxEmployeeId() {
        int maxId = 0;
        for (Employee employee : employeeList) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        return maxId;
    }

    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {

        if (emailExit(employee.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        for (Employee e : employeeList) {
            if (e.getId() == employee.getId()) {
                e.setEmail(employee.getEmail() != null ? employee.getEmail() : e.getEmail());
                e.setName(employee.getName() != null ? employee.getName() : e.getName());
                e.setDepartment(employee.getDepartment() != null ? employee.getDepartment() : e.getDepartment());
                e.setRoles(employee.getRoles() != null ? employee.getRoles() : e.getRoles());
                return new ResponseEntity<>(e, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        if (!isExit(id)) {
            return null;
        }
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeList.remove(employee);
                break;
            }
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("delete successfully");
    }

    private boolean isExit(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean emailExit(String email) {
        for (Employee employee : employeeList) {
            if (employee.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Employee findId(int id) {
        for (Employee e : employeeList) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Employee employee = findId(id);
        if (employee == null) {
            return new ResponseEntity<>("id not found, please try again!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
