package dm848.comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Persistent controller entity with JPA markup. Users are stored in an H2
 * relational database.
 */
@Entity
@Table(name = "T_COMMENT")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Long nextId = 0L;

	@Id
	protected Long id;

	@Column(name = "video_id")
	protected Long videoId;

	@Column(name = "user_id")
	protected String userId;

	protected String comment;


	/**
	 * This is a very simple, and non-scalable solution to generating unique
	 * ids. Not recommended for a real application. Consider using the
	 * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
	 * 
	 * @return The next available id.
	 */
	protected static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}

	/**
	 * Default constructor for JPA only.
	 */
    public Comment() {
    }

	public Comment(String comment, Long videoId, String userId) {
		id = getNextId();
		this.videoId = videoId;
		this.userId = userId;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	protected void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	protected void setUserId(String id) {
		this.userId = id;
	}

	public Long getVideoId() {
		return videoId;
	}

	protected void setVideoId(Long id) {
		this.videoId = id;
	}

	@Override
	public String toString() {
		return comment;
	}

}
