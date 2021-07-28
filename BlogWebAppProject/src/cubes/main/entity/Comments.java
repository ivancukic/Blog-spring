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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Comments {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String text;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String date;
	@Column
	private boolean isApproved;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idPost")
	private Post post;
	
	
	@Transient
	private long count;
	
	public Comments() {
		
		// Date
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		Date today = Calendar.getInstance().getTime();
		date = format.format(today);
	}

	public Comments(String text, String name, String email, String date, boolean isApproved) {
		
		this.text = text;
		this.name = name;
		this.email = email;
		this.date = date;
		this.isApproved = isApproved;

	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	

	@Override
	public String toString() {
		return "Comments [id=" + id + ", text=" + text + ", isApproved=" + isApproved + "]";
	}

	
	
	
}
