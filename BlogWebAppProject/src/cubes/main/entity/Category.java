package cubes.main.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Category {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String description;
	
	@Transient
	private List<Post> postsOnBlogPage;
	
	@Transient
	private long counter;
	
	public Category() {
		
	}

	public Category(String name, String description) {
		
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPostsOnBlogPage() {
		return postsOnBlogPage;
	}

	public void setPostsOnBlogPage(List<Post> postsOnBlogPage) {
		this.postsOnBlogPage = postsOnBlogPage;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}
	
	
	

}
