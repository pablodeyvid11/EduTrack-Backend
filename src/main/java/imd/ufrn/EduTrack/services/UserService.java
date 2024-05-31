package imd.ufrn.EduTrack.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import imd.ufrn.EduTrack.models.User;
import imd.ufrn.EduTrack.models.UserDetailsImpl;
import imd.ufrn.EduTrack.models.records.RecoveryJWTRecord;
import imd.ufrn.EduTrack.models.records.SigninRecord;
import imd.ufrn.EduTrack.models.records.SignonRecord;
import imd.ufrn.EduTrack.repositories.GenericRepository;
import imd.ufrn.EduTrack.repositories.UserRepository;
import jakarta.validation.Valid;

@Service
public class UserService extends GenericService<User> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@Override
	public GenericRepository<User> getRepository() {
		return repository;
	}

	public User register(@Valid SignonRecord signonRecord) throws NoSuchAlgorithmException {
		User user = new User();
		user.setName(signonRecord.name());
		user.setEmail(signonRecord.email());
		user.setUsername(signonRecord.username());
		user.setPassword(passwordEncoder.encode(signonRecord.password()));
		user.setBio(signonRecord.bio());

		return insert(user);
	}

	public RecoveryJWTRecord login(SigninRecord loginUserDto) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginUserDto.username(), loginUserDto.password());

		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return new RecoveryJWTRecord(jwtTokenService.generateToken(userDetails));
	}
}
