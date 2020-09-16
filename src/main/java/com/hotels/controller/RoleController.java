package com.hotels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.message.response.ResponseMessage;
import com.hotels.model.Role;
import com.hotels.repository.RoleRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/roles")
public class RoleController {


	@Autowired
	RoleRepository roleRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> addRole(@RequestBody Role role){
		Role checkRole = roleRepository.findByName(role.getName()).orElse(null);
		if(checkRole != null) {
			return new ResponseEntity<>(new ResponseMessage("Role already exists !"), HttpStatus.NOT_ACCEPTABLE);
		}
		role.setName("ROLE_" + role.getName().toUpperCase());
		roleRepository.save(role);
		return new ResponseEntity<>(new ResponseMessage("Role created Successfully !"), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRole(@RequestBody Role role){
		Role checkRole = roleRepository.findById(role.getId()).orElse(null);
		if(roleRepository.existsByName(role.getName()) && !checkRole.getName().equals(role.getName())) {
			return new ResponseEntity<>(new ResponseMessage("Role already exists !"), HttpStatus.NOT_ACCEPTABLE);
		}
		checkRole.setName("ROLE_" + role.getName().toUpperCase());
		roleRepository.save(checkRole);
		return new ResponseEntity<>(new ResponseMessage("Role created Successfully !"), HttpStatus.CREATED);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
	
	@GetMapping("/findOne/{id}")
	public Role getRole(@PathVariable Long id) {
		return roleRepository.findById(id).orElse(null);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRole(@PathVariable Long id) {
		roleRepository.deleteById(id);
	}
}
