package testFramework.schemas;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Course{

	@JsonProperty("spartans")
	private List<String> spartans;

	@JsonProperty("streamId")
	private int streamId;

	@JsonProperty("stream")
	private Stream stream;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("trainer")
	private String trainer;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("startDate")
	private String startDate;

	public List<String> getSpartans(){
		return spartans;
	}

	public int getStreamId(){
		return streamId;
	}

	public Stream getStream(){
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

	public int getId(){
		return id;
	}

	public String getStartDate(){
		return startDate;
	}
}