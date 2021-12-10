package uni.fmi.masters.JobsPazardzhik.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"jobs"})
public class UserEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username", length = 255, nullable = false, unique = true)
	private String username;
	@Column(name="password", length = 32, nullable = false)
	private String password;
	@Column(name="telephone",length = 10,nullable = false)
	private String telephone;
	@Column(name="email", length = 255, nullable = false, unique = true)
	private String email;
	@Column(name="avatar_location", length = 255)
	private String avatarLocation;
	
	@OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
	private List<JobsEntity> jobs;
		
	public List<JobsEntity> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobsEntity> jobs) {
		this.jobs = jobs;
	}

	public UserEntity() {	}
	
	public UserEntity(String username, String password, String email,String telephone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
	}
	
	public UserEntity(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarLocation() {
		return avatarLocation;
	}

	public void setAvatarLocation(String avatarLocation) {
		this.avatarLocation = avatarLocation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
