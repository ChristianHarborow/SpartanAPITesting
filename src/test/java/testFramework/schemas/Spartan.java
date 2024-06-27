package testFramework.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Spartan{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("university")
	private String university;

	@JsonProperty("graduated")
	private boolean graduated;

	@JsonProperty("degree")
	private String degree;

	@JsonProperty("course")
	private Course course;

	@JsonProperty("id")
	private int id;

	@JsonProperty("courseId")
	private int courseId;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getUniversity(){
		return university;
	}

	public boolean isGraduated(){
		return graduated;
	}

	public String getDegree(){
		return degree;
	}

	public Course getCourse(){
		return course;
	}

	public int getId(){
		return id;
	}

	public int getCourseId(){
		return courseId;
	}
}