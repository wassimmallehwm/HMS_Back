package com.hotels.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.message.request.LoginForm;
import com.hotels.message.request.SignUpForm;
import com.hotels.message.response.JwtResponse;
import com.hotels.message.response.ResponseMessage;
import com.hotels.model.Role;
import com.hotels.model.User;
import com.hotels.repository.RoleRepository;
import com.hotels.repository.UserRepository;
import com.hotels.security.jwt.JwtProvider;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestHeader (name="Authorization") String token) {
		String jwt = null;
		token = token.replace("Bearer ", "");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(token != null && jwtProvider.validateJwtToken(token)) {
			jwt = jwtProvider.generateJwtToken(authentication);
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		}
		return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
	}
	
//	@GetMapping("/validateToken")
//	public ResponseEntity<?> validateToken(@RequestHeader (name="Authorization") String token) {
//		token = token.replace("Bearer ", "");
//		if(token != null && jwtProvider.validateJwtToken(token)) {
//			return new ResponseEntity<>(true, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
//	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
		user.setPassword(null);
		return ResponseEntity.ok(new JwtResponse(jwt, user, userDetails.getAuthorities()));
	}
	
//	@GetMapping("/currentUser")
//	public User getOneByUsername() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
//		user.setPassword(null);
//		return user;
//	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			Role userRole = roleRepository.findByName(strRoles.iterator().next().getName())
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);
		});

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody User user) {
		User oldUser = userRepository.findById(user.getId()).orElse(null);
		if (userRepository.existsByUsername(user.getUsername()) && !user.getUsername().equals(oldUser.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(user.getEmail()) && !user.getEmail().equals(oldUser.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		Set<Role> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			Role userRole = roleRepository.findByName(strRoles.iterator().next().getName())
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);
		});

		oldUser.setRoles(roles);
		oldUser.setEmail(user.getEmail());
		oldUser.setName(user.getName());
		oldUser.setUsername(user.getUsername());
		userRepository.save(oldUser);

		return new ResponseEntity<>(new ResponseMessage("User updated successfully!"), HttpStatus.OK);
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userRepository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/findOne/{id}")
	public User getOne(@PathVariable Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}