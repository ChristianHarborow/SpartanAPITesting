package testFramework.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class LinksItem{

	@JsonProperty("method")
	private String method;

	@JsonProperty("rel")
	private String rel;

	@JsonProperty("href")
	private String href;

	public String getMethod(){
		return method;
	}

	public String getRel(){
		return rel;
	}

	public String getHref(){
		return href;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LinksItem linksItem = (LinksItem) o;
		return Objects.equals(method, linksItem.method)
				&& Objects.equals(rel, linksItem.rel)
				&& Objects.equals(href, linksItem.href);
	}

	@Override
	public int hashCode() {
		return Objects.hash(method, rel, href);
	}
}