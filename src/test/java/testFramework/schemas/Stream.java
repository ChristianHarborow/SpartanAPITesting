package testFramework.schemas;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stream{

	@JsonProperty("courses")
	private List<String> courses;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public List<String> getCourses(){
		return courses;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}