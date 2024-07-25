package testFramework.schemas;

import java.util.List;
import java.util.Objects;

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

	public Integer getCourseId() {
		for (LinksItem item : links) {
			if (item.getRel().equals("course")) {
				String[] split = item.getHref().split("/");
				return Integer.parseInt(split[split.length - 1]);
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SpartanDTO that = (SpartanDTO) o;
		return graduated == that.graduated
				&& id == that.id
				&& Objects.equals(firstName, that.firstName)
				&& Objects.equals(lastName, that.lastName)
				&& Objects.equals(stream, that.stream)
				&& Objects.equals(university, that.university)
				&& Objects.equals(degree, that.degree)
				&& Objects.equals(course, that.course)
				&& Objects.equals(links, that.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, stream, university, graduated, degree, course, links, id);
	}
}