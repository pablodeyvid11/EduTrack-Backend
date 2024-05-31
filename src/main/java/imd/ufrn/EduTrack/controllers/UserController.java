package imd.ufrn.EduTrack.controllers;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.EduTrack.models.User;
import imd.ufrn.EduTrack.models.records.RecoveryJWTRecord;
import imd.ufrn.EduTrack.models.records.SigninRecord;
import imd.ufrn.EduTrack.models.records.SignonRecord;
import imd.ufrn.EduTrack.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> signon(@RequestBody(required = true) @Valid SignonRecord signonRecord) {

		try {
			User userCreated = service.register(signonRecord);
			URI uri = URI.create("/user/" + userCreated.getId());
			return ResponseEntity.created(uri).build();
		} catch (NoSuchAlgorithmException e) {
			return ResponseEntity.internalServerError().body("Error on register user");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> signin(@RequestBody(required = true) @Valid SigninRecord signonRecord) {

		try {
			RecoveryJWTRecord token = service.login(signonRecord);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error on Login");
		}
	}
}
