package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.EmployeeDto;
import com.example.survey_tourist.entity.Department;
import com.example.survey_tourist.entity.Role;
import com.example.survey_tourist.entity.User;
import com.example.survey_tourist.service.DepartmentService;
import com.example.survey_tourist.service.EmployeeService;
import com.example.survey_tourist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/roles-department")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getRoleDepartment() throws Exception {
        List<Role> roles = roleService.findAllRoles();
        List<Department> departments = departmentService.findAllDepartments();
        Map<String, Object> response = new HashMap<>();
        response.put("roles", roles);
        response.put("departments", departments);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Integer id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search-count")
//    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> searchEmployees(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String phone,
            @RequestParam(required = false) Integer department,
            @RequestParam(required = false) Integer role,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<EmployeeDto> employeePage = employeeService.search(name, email, phone, department, role, pageNumber,
                pageSize);
        Long employeeCount = employeeService.count(name, email, phone, department, role);
        Map<String, Object> response = new HashMap<>();
        response.put("data", employeePage);
        response.put("count", employeeCount);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/create-employee")
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@Valid @RequestBody EmployeeDto employeeDto) throws Exception {
        User user = employeeService.addEmployees(employeeDto);
    }

    @GetMapping(value = "/getEmployee/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public Optional<EmployeeDto> getById(@PathVariable final Integer id) {
        Optional<EmployeeDto> employeeServiceById = employeeService.getById(id);
        return employeeServiceById;
    }

    @PutMapping(value = "/update-employee/{id}")
    @CrossOrigin(value = "*", methods = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer employeeId,
            @Valid @RequestBody EmployeeDto updateEmployeeDto) {
        try {
            employeeService.updateEmployee(employeeId, updateEmployeeDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
