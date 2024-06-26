package testFramework.schemas;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpartanDTO{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("stream")
	private String stream;

	@JsonProperty("university")
	private String university;

	@JsonProperty("graduated")
	private boolean graduated;

	@JsonProperty("degree")
	private String degree;

	@JsonProperty("course")
	private String course;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private int id;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getStream(){
		return stream;
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

	public String getCourse(){
		return course;
	}

	public List<LinksItem> getLinks(){
		return links;
	}

	public int getId(){
		return id;
	}
}