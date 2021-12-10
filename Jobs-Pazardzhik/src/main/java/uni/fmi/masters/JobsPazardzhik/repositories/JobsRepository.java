package uni.fmi.masters.JobsPazardzhik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.fmi.masters.JobsPazardzhik.entities.JobsEntity;

@Repository
public interface JobsRepository extends JpaRepository<JobsEntity, Integer>{

}
