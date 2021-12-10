package uni.fmi.masters.JobsPazardzhik;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uni.fmi.masters.JobsPazardzhik.entities.UserEntity;
import uni.fmi.masters.JobsPazardzhik.repositories.UserRepository;

@Service
public class ApplicationUserDetailService implements UserDetailsService{

	private UserRepository userRepository;
	
	public ApplicationUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username + " was not found!");
		}
		
		return new UserPrincipal(user);
		
	}

}
