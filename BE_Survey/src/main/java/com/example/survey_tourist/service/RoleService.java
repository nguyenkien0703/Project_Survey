package com.example.survey_tourist.service;

import com.example.survey_tourist.dto.RoleDto;
import com.example.survey_tourist.dto.UserDto;
import com.example.survey_tourist.entity.Role;
import com.example.survey_tourist.entity.User;
import com.example.survey_tourist.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;


    public List<RoleDto> findAll(){
        return roleRepository.findAll().stream().map(role -> mapToDTO(role, new RoleDto())).collect(Collectors.toList());
    }
    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }
    public List<String> findAllNameRoles(){
        return roleRepository.findAll().stream().map(Role::getNameRole).collect(Collectors.toList());
    }

    public Role get(final Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public Integer create(final RoleDto roleDto) {
        final Role role = new Role();
        mapToEntity(roleDto, role);
        return roleRepository.save(role).getId();
    }

    public void update(final Integer id, final RoleDto roleDto){
        final Role role = roleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        role.setNameRole(roleDto.getNameRole());
//        role.setIsDelete(roleDto.getIsDelete());
        roleRepository.save(role);
    }

    public Role registerRole(RoleDto roleDto) throws Exception {
        Role role = new Role();
        role.setNameRole(roleDto.getNameRole());
//        role.setIsDelete(roleDto.getIsDelete());
        roleRepository.save(role);
        return role;
    }



    public void  delete(final Integer id){
        final Role role = roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        role.setIsDelete(true);
        roleRepository.save(role);
    }



    private Role mapToEntity(final RoleDto roleDto, Role role ){
        role = modelMapper.map(roleDto,Role.class);
        return role ;
    }
    private RoleDto mapToDTO(final Role role, RoleDto roleDto){
        roleDto = modelMapper.map(role,RoleDto.class);
        return roleDto;
    }



}
