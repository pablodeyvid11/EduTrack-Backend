package imd.ufrn.EduTrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserAuthenticationFilter userAuthenticationFilter;

	public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = { 
			"/user/login", 
			"/user", 
			"/swagger-ui/index.html",
			"/swagger-ui/swagger-initializer.js",
			"/v3/api-docs/swagger-config",
			"/v3/api-docs" };

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(requests -> requests.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
						.permitAll().anyRequest().authenticated())
				.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
		return defaultSecurityFilterChain;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}