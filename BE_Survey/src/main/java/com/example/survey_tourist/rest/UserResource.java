package com.example.survey_tourist.rest;

import com.example.survey_tourist.dto.JwtRequest;
import com.example.survey_tourist.dto.UserDto;
import com.example.survey_tourist.entity.User;
//import com.example.survey_tourist.service.AuthService;
import com.example.survey_tourist.repository.UserRepository;
import com.example.survey_tourist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable final String  id){
        return ResponseEntity.ok(userService.get(id));
    }

//    @GetMapping("/me")
//    public ResponseEntity<User> me(){
//        return ResponseEntity.ok(authService.getCurrentUser());
//    }

    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody @Valid final UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final  Integer id,@RequestBody @Valid UserDto userDto ){
        userService.update(id,userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/user-info")
    @CrossOrigin(value = "*",methods = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserLogin(@RequestParam(required = false, defaultValue = "") String userName) {
        User user = userService.getUser(userName);
        System.out.println(user.getUserName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setRoleId(user.getRole().getId());
        userDto.setUserName(user.getUserName());
        userDto.setRoleName(user.getRole().getNameRole());
        return ResponseEntity.ok(userDto);
    }
}
