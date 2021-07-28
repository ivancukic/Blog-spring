package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Post;
import cubes.main.entity.Slider;

public interface PostDAO {

	public List<Post> getPostList(); // POSTS LIST
	
	public void savePost(Post post); // SAVE POST
	
	public Post getPost(int id); // UPDATE POST
	
	public void deletePost(int id); // DELETE POST
	
	public List<Post> getImportantPost(); // LIST OF IMPORTANT POST
	
	public Post getPostWithTag(int id);
	
	public List<Post> getPostListByCategory(int id); // POST BY CATEGORY
	
	public List<Post> getPostListByTag(int id); // POST BY TAG
	
	public List<Post> searchBlogPosts(String theSearchPost); // SEARCH FOR BLOG POSTS
	
	public Post getPreviousPost(int id); // PREVIOUS POST
	
	public Post getNextPost(int id); // NEXT POST
	
	public List<Post> getPostListByAuthor(String author); // POST LIST OF AUTHOR
	
}
