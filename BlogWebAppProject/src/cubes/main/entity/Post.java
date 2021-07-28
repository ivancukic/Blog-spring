package cubes.main.entity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Post {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String title;
	@Column
	private String text;
	@Column
	private String image;
	@Column
	private String author;
	@Column
	private String authorImage;
	@Column
	private String date;
	@Column
	private int commentCounter;
	@Column
	private boolean isImportant;
	
	@JoinColumn(name = "idCategory")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Category category;
		
	@OneToMany(mappedBy = "post")
	private List<Comments> comments;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "id_post"), inverseJoinColumns = @JoinColumn(name = "id_tag"))
	private List<Tag> tags;
	
	public Post() {
		
		// Date
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		Date today = Calendar.getInstance().getTime();
	    date = format.format(today);
	}

	public Post(String title, String text, String image, String author, String date, int commentCounter,
			boolean isImportant) {
		
		this.title = title;
		this.text = text;
		this.image = image;
		this.author = author;
		this.date = date;
		this.commentCounter = commentCounter;
		this.isImportant = isImportant;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorImage() {
		return authorImage;
	}

	public void setAuthorImage(String authorImage) {
		this.authorImage = authorImage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCommentCounter() {
		return commentCounter;
	}

	public void setCommentCounter(int commentCounter) {
		this.commentCounter = commentCounter;
	}

	public boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	
}
