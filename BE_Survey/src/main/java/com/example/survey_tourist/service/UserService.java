package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.UserDto;
import com.example.survey_tourist.entity.User;
import com.example.survey_tourist.repository.DepartmentRepository;
import com.example.survey_tourist.repository.RoleRepository;
import com.example.survey_tourist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserService implements UserDetailsService {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<UserDto> findAll(){
        return userRepository.findAll().stream().map(user -> mapToDTO(user, new UserDto())).collect(Collectors.toList());
    }

    public User get(final String  id) {
        return userRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final UserDto userDto) {
        final User user = new User();
        mapToEntity(userDto, user);
        return userRepository.save(user).getId();

    }

    public User get(final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void update (final Integer id , final UserDto userDto){
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setAddress(userDto.getAddress());
        user.setUserName(userDto.getUserName());
        user.setDepartment(departmentRepository.findById(userDto.getDepartmentId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        user.setRole(roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        user.setUserName(userDto.getUserName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
    }
    public User registerUser(UserDto userDto) throws Exception {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setRole(roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        user.setDepartment(departmentRepository.findById(userDto.getDepartmentId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));;
        if(userDto.getPasswordAccount().equals(userDto.getCheckPass())== false){
            throw new Exception("Passwords do not match");
        }
        String encryptedPassword = passwordEncoder.encode(userDto.getPasswordAccount());
        user.setPasswordAccount(encryptedPassword);
        // tao thoi gian
        LocalDate now = LocalDate.now();
        user.setCreateAt(now);
        user.setUpdateAt(now);
        userRepository.save(user);
        return user;
    }
    public void delete(final Integer id ){
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate  deleteAt = LocalDate.now();
        user.setDeleteAt(deleteAt);
        user.setIsDelete(true);
        userRepository.save(user);

    }

    private User mapToEntity(final UserDto userDto, User user ){
        user = modelMapper.map(userDto,User.class);
        return user ;
    }
    private UserDto mapToDTO(final User user, UserDto userDto){
        userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    @Transactional
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getNameRole()));
        System.out.println("Check role:" + user.getRole().getNameRole() );
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPasswordAccount(),authorities);
    }

    public User getUser(String userName){
        User user = userRepository.findByUserName(userName);
        return user;
    }
}
