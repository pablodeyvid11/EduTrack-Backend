package imd.ufrn.EduTrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import imd.ufrn.EduTrack.models.User;
import imd.ufrn.EduTrack.models.UserDetailsImpl;
import imd.ufrn.EduTrack.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		return new UserDetailsImpl(user);
	}

}