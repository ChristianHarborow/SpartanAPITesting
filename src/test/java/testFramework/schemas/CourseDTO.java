package testFramework.schemas;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseDTO{

	@JsonProperty("spartans")
	private List<String> spartans;

	@JsonProperty("stream")
	private String stream;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("trainer")
	private String trainer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("links")
	private List<LinksItem> links;

	@JsonProperty("id")
	private int id;

	@JsonProperty("startDate")
	private String startDate;

	public List<String> getSpartans(){
		return spartans;
	}

	public String getStream(){
		return stream;
	}

	public String getEndDate(){
		return endDate;
	}

	public String getTrainer(){
		return trainer;
	}

	public String getName(){
		return name;
	}

	public List<LinksItem> getLinks(){
		return links;
	}

	public int getId(){
		return id;
	}

	public String getStartDate(){
		return startDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CourseDTO courseDTO = (CourseDTO) o;
		return id == courseDTO.id
				&& Objects.equals(spartans, courseDTO.spartans)
				&& Objects.equals(stream, courseDTO.stream)
				&& Objects.equals(endDate, courseDTO.endDate)
				&& Objects.equals(trainer, courseDTO.trainer)
				&& Objects.equals(name, courseDTO.name)
				&& Objects.equals(links, courseDTO.links)
				&& Objects.equals(startDate, courseDTO.startDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(spartans, stream, endDate, trainer, name, links, id, startDate);
	}
}