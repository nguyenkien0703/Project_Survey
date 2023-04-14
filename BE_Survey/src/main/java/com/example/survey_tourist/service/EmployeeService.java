package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.EmployeeDto;
import com.example.survey_tourist.entity.User;
import com.example.survey_tourist.repository.DepartmentRepository;
import com.example.survey_tourist.repository.EmployeeRepository;
import com.example.survey_tourist.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void delete(final Integer id) {
        final User user = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate deleteAt = LocalDate.now();
        user.setDeleteAt(deleteAt);
        user.setIsDelete(true);
        employeeRepository.save(user);
    }

    public Page<EmployeeDto> search(String name, String email, String phone, Integer department,Integer role, int pageNumber, int pageSize) {
        Specification<User> spec = Specification.where((root, query, builder) -> builder.equal(root.get("isDelete"), false));
        if (name != null) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("userName"), "%" + name + "%"));
        }
        if (email != null) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("email"), "%" + email + "%"));
        }
        if (phone != null) {
            spec = spec.and((root, query, builder) -> builder.like(root.get("phoneNumber"), "%" + phone + "%"));
        }
        if (department != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("department").get("id"), department));
        }if (role != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("role").get("id"), role));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> pageResult = employeeRepository.findAll(spec, pageable);
        Page<EmployeeDto> pageDto = pageResult.map(user -> {
            EmployeeDto employeeDto = modelMapper.map(user, EmployeeDto.class);
            employeeDto.setDepartmentId(user.getDepartment().getId());
            employeeDto.setDepartmentName(user.getDepartment().getNameDepartment());
            employeeDto.setRoleId(user.getRole().getId());
            employeeDto.setRoleName(user.getRole().getNameRole());
            return employeeDto;
        });
        return pageDto;
    }

    public Long count(String name, String email, String phone, Integer department,Integer role) {
        String query = "SELECT COUNT(*) FROM User WHERE isDelete = false";
        if (name != null) {
            query += " AND userName LIKE '%" + name + "%'";
        }
        if (email != null) {
            query += " AND email LIKE '%" + email + "%'";
        }
        if (phone != null) {
            query += " AND phoneNumber LIKE '%" + phone + "%'";
        }
        if (department != null) {
            query += " AND department_id = " + department;
        }
        if (role != null) {
            query += " AND role_Id = " + role;
        }
        return entityManager.createQuery(query, Long.class).getSingleResult();
    }

    public User addEmployees(EmployeeDto employeeDto) throws Exception {
        User user = new User();
        user.setNameAccount(employeeDto.getNameAccount());
        user.setUserName(employeeDto.getUserName());
        user.setRole(roleRepository.findById(employeeDto.getRoleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        user.setPhoneNumber(employeeDto.getPhoneNumber());
        user.setAddress(employeeDto.getAddress());
        user.setESex(employeeDto.getESex());
        user.setEmail(employeeDto.getEmail());
        user.setDepartment(departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        String encryptedPassword = passwordEncoder.encode(employeeDto.getPasswordAccount());
        user.setPasswordAccount(encryptedPassword);
        // tao thoi gian
        LocalDate createAt = LocalDate.now() ;

        user.setCreateAt(createAt);
        employeeRepository.save(user);
        return user;
    }

    public Optional<EmployeeDto> getById(Integer id){
        Optional<User> user = employeeRepository.findById(id);
        Optional<EmployeeDto> employeeDto = user.map(user1->{
            EmployeeDto employeeDto1 = modelMapper.map(user1,EmployeeDto.class);
            employeeDto1.setRoleId(user1.getRole().getId());
            employeeDto1.setDepartmentId(user1.getDepartment().getId());
            return employeeDto1;
        });
        return employeeDto;
    }

    public void updateEmployee(Integer employeeId, EmployeeDto employeeDto) {
        final User user = employeeRepository.findById(employeeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setNameAccount(employeeDto.getNameAccount());
        user.setUserName(employeeDto.getUserName());
        user.setRole(roleRepository.findById(employeeDto.getRoleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        user.getRole().getId();
        user.setPhoneNumber(employeeDto.getPhoneNumber());
        user.setAddress(employeeDto.getAddress());
        user.setESex(employeeDto.getESex());
        user.setEmail(employeeDto.getEmail());
        user.setDepartment(departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        String encryptedPassword = passwordEncoder.encode(employeeDto.getPasswordAccount());
        user.setPasswordAccount(encryptedPassword);
        LocalDate updateAt = LocalDate.now() ;
        user.setUpdateAt(updateAt);
        employeeRepository.save(user);
    }
}
