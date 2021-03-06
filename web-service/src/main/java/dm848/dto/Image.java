package dm848.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import dm848.service.WebImageService;

/**
 * User DTO - used to interact with the {@link WebImageService}.
 */
@JsonRootName("Image")
public class Image {


	protected Long id;
	protected String name;
	protected String link;
	protected String description;
	protected String date;
	protected String userName;
	protected String embeddedLink; // Null by default
	protected int count; // Null by default


	/**
	 * Default constructor for JPA only.
	 */
    public Image() {
    }

	public long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	protected void setLink(String link) {
		this.link = link;
	}

	public String getEmbeddedLink() {
		return embeddedLink;
	}

	public void setEmbeddedLink(String link) {
		this.embeddedLink = link;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescription(String desc) {
		this.description = desc;
	}

	public String getUserName() { return userName; }

	protected void setUserName(String username) {
		this.userName = username;
	}

	public String getDate() { return date; }

	public int getCount() {	return count; }

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return name + " [" + link + "]";
	}

}
