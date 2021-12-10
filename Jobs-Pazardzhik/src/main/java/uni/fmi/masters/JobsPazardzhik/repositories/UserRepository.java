package uni.fmi.masters.JobsPazardzhik.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.JobsPazardzhik.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUsername(String username);
	
	UserEntity findUserByUsernameAndPassword(String username, String password);

}
