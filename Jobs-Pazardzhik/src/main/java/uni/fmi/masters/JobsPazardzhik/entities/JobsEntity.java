package uni.fmi.masters.JobsPazardzhik.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jobs")
public class JobsEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title",length = 255,nullable = false)
	private String title;
	
	@Column(name = "field",length = 255,nullable = false)
	private String field;
	
	@Column(name = "position",length = 255,nullable = false)
	private String position;
	
	@Column(name = "expirience",length = 10,nullable = false)
	private String expirience;
	
	@Column(name = "suitable",length = 50,nullable = false)
	private String suitable;
	
	@Column(name = "place", length = 255, nullable = false)
	private String place;
	
	@Column(name = "description",length = 255,nullable = false)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserEntity user;

	public JobsEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public JobsEntity(String title,String field,String position,String expirience,String suitable,String place,String description,UserEntity user) {
		this.title = title;
		this.field = field;
		this.position = position;
		this.expirience = expirience;
		this.suitable = suitable;
		this.place = place;
		this.description = description;
		this.user = user;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExpirience() {
		return expirience;
	}

	public void setExpirience(String expirience) {
		this.expirience = expirience;
	}

	public String getSuitable() {
		return suitable;
	}

	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
}
