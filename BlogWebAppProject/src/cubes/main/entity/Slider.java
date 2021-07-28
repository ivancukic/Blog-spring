package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Slider {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String image;
	@Column
	private String slideTitle;
	@Column
	private String linkTitle;
	@Column
	private String linkUrl;
	@Column
	private boolean isOnIndexPage;
	
	public Slider() {
		
	}

	public Slider(String image, String slideTitle, String linkTitle, String linkUrl, boolean isOnIndexPage) {
		
		this.image = image;
		this.slideTitle = slideTitle;
		this.linkTitle = linkTitle;
		this.linkUrl = linkUrl;
		this.isOnIndexPage = isOnIndexPage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSlideTitle() {
		return slideTitle;
	}

	public void setSlideTitle(String slideTitle) {
		this.slideTitle = slideTitle;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public boolean getIsOnIndexPage() {
		return isOnIndexPage;
	}

	public void setIsOnIndexPage(boolean isOnIndexPage) {
		this.isOnIndexPage = isOnIndexPage;
	}

	

}
