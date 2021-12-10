package uni.fmi.masters.JobsPazardzhik.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.fmi.masters.JobsPazardzhik.entities.JobsEntity;
import uni.fmi.masters.JobsPazardzhik.entities.UserEntity;
import uni.fmi.masters.JobsPazardzhik.repositories.JobsRepository;

@RestController
public class JobsController {

	JobsRepository jobsRepo;
	
	public JobsController(JobsRepository jobsRepo) {
		this.jobsRepo = jobsRepo;
	}
	
	@PostMapping(path = "/jobs/add")
	public String addJobs(
			@RequestParam(value = "title")String title,
			@RequestParam(value="field")String field,
			@RequestParam(value ="position") String position,
			@RequestParam(value = "expirience")String expirience,
			@RequestParam(value = "suitable") String suitable,
			@RequestParam(value = "place")String place,
			@RequestParam(value = "description")String description,HttpSession session) {
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		
		if(user != null) {
			JobsEntity newJob = new JobsEntity(title, field, position, expirience, suitable, place, description, user);
			
			newJob = jobsRepo.saveAndFlush(newJob);
			
			if(newJob != null) {
				return String.valueOf(newJob.getId());
			}
			return "Error: Insert Unsuccesfull!";
		}
		return "Error: there is no logged user!";
		
	}
	
	@GetMapping(path = "/jobs/all")
	public List<JobsEntity> getAllJobs(){
		return jobsRepo.findAll();
	}
	
	@DeleteMapping(path="/jobs/delete")
	public ResponseEntity<Boolean> deleteJob(@RequestParam(value = "id") int id,HttpSession session){
		UserEntity user = (UserEntity) session.getAttribute("user");
		if(user == null) {
			return new ResponseEntity<Boolean>(false,HttpStatus.UNAUTHORIZED);
		}
		
		Optional<JobsEntity> jobOptional = jobsRepo.findById(id);
		
		if(jobOptional.isPresent()) {
			JobsEntity job = jobOptional.get();
			
			if(job.getUser().getId() == user.getId()) {
				jobsRepo.delete(job);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			}
			
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		
	}

}
