package cubes.main;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentsDAO;
import cubes.main.dao.MessageDAO;
import cubes.main.dao.PostDAO;
import cubes.main.dao.SliderDAO;
import cubes.main.dao.TagDAO;
import cubes.main.entity.Comments;
import cubes.main.entity.Message;
import cubes.main.entity.Post;


@Controller
@RequestMapping("/")
public class FrontController {
	
	@Autowired
	private SliderDAO sliderDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private TagDAO tagDAO;
	
	@Autowired
	private CommentsDAO commentsDAO;
	
	@RequestMapping({"/", "index-page"})
	public String getIndexPage(Model model) {
		
		model.addAttribute("sliders", sliderDAO.getSliderOnIndexPage());
		model.addAttribute("posts", postDAO.getImportantPost());
		model.addAttribute("postList", postDAO.getPostList());
		model.addAttribute("categoryList", categoryDAO.getCategoryList());
		
		return "front/index-page";
	}
	
	@RequestMapping("blog-page")
	public String getBlogPage(Model model) {
		
		model.addAttribute("postList", postDAO.getPostList());
		
		// CATEGORIES		
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		// TAGS
		model.addAttribute("tagList", tagDAO.getTagListWithPosts());
		
		// LATEST POST
		
		List<Post> reversePostList = postDAO.getPostList();		
		Collections.reverse(reversePostList);
		
		model.addAttribute("reversePostList", reversePostList);
		
		return "front/blog-page";
	}
	
	@RequestMapping("/post-page-filter")
	public String getPostPageFilter(@RequestParam int id, @RequestParam String type, Model model) {
		
		if(type.equalsIgnoreCase("category")) {
			
			model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
			model.addAttribute("tagList", tagDAO.getTagListWithPosts());
			model.addAttribute("postList", postDAO.getPostListByCategory(id));
		}
		else if(type.equalsIgnoreCase("tag")) {
			
			model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
			model.addAttribute("tagList", tagDAO.getTagListWithPosts());
			model.addAttribute("postList", postDAO.getPostListByTag(id));
		}
		
		return "front/blog-page";
	}
	
	@RequestMapping("/blog-post-page")
	public String getBlogPostPage(@RequestParam int id, Model model) {
		
		Post post = postDAO.getPost(id);
		
		model.addAttribute("postList", postDAO.getPostList());
		model.addAttribute("post", post);
		
		// CATEGORIES		
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		// TAGS
		model.addAttribute("tagList", tagDAO.getTagListWithPosts());
		
		// LATEST POST
		List<Post> reversePostList = postDAO.getPostList();		
		Collections.reverse(reversePostList);
		
		model.addAttribute("reversePostList", reversePostList);
		
		
		// PREVIOUS POST
		Post prevPost = postDAO.getPreviousPost(id);
		
		model.addAttribute("previousName", prevPost.getTitle());
		
		model.addAttribute("previousPost", prevPost.getId());
		
		// NEXT POST
		Post nexPost = postDAO.getNextPost(id);
				
		model.addAttribute("nextName", nexPost.getTitle());
				
		model.addAttribute("nextPost", nexPost.getId());
		
		// COMMENTS POST
		
		model.addAttribute("comments", new Comments());
		model.addAttribute("commentsList", commentsDAO.getCommentsListByPost(id));		
		
		return "front/blog-post-page";
	}
	
	@RequestMapping("/previous-next-page")
	public String getPreviousPage(@RequestParam int id, Model model) {
		
		Post previousPost = postDAO.getPost(id);	
		model.addAttribute("previousPost", previousPost);
		
		Post post = postDAO.getPost(id);
		
		model.addAttribute("postList", postDAO.getPostList());
		model.addAttribute("post", post);
		
		// CATEGORIES		
		model.addAttribute("categoryList", categoryDAO.getCategoriesForFilter());
		// TAGS
		model.addAttribute("tagList", tagDAO.getTagListWithPosts());
		
		// LATEST POST
		List<Post> reversePostList = postDAO.getPostList();		
		Collections.reverse(reversePostList);
		
		model.addAttribute("reversePostList", reversePostList);
		
		
		// PREVIOUS POST
		Post prevPost = postDAO.getPreviousPost(id);
		
		model.addAttribute("previousName", prevPost.getTitle());
		
		model.addAttribute("previousPost", prevPost.getId());
	
		// NEXT POST
		Post next = postDAO.getNextPost(id);
						
		model.addAttribute("nextName", next.getTitle());
						
		model.addAttribute("nextPost", next.getId());
		
		// COMMENTS POST
		
		model.addAttribute("comments", new Comments());
		model.addAttribute("commentsList", commentsDAO.getCommentsListByPost(id));

		
		
		return "front/blog-post-page";
	}
	
	@RequestMapping("author-page")
	public String getAuthorPage(@RequestParam String author, Model model) {
		
		List<Post> authorList = postDAO.getPostListByAuthor(author);
		Post post = new Post();
		
		model.addAttribute("authorName", author);
		model.addAttribute("authorList", authorList);
		model.addAttribute("authorImage", post.getAuthorImage());
		
		return "front/author-page";
	}
	
	@RequestMapping("comments-save")
	public String saveComments(@RequestParam int postId,@ModelAttribute Comments comments, Model model) {
		
		comments.setPost(postDAO.getPost(postId));
		
		commentsDAO.saveComment(comments);
		
		
		
		model.addAttribute("comments", new Comments());
		model.addAttribute("commentsList", commentsDAO.getCommentsListByPost(postId));
		
		return "front/blog-post-page";
	}
	
	@RequestMapping("contact-page")
	public String getContactPage(Model model) {
		
		model.addAttribute("message", new Message());
		model.addAttribute("categoryList", categoryDAO.getCategoryList());
		
		return "front/contact-page";
	}
	
	@RequestMapping("message-save")
	public String saveMessage(@ModelAttribute Message message) {
		
		messageDAO.saveMesage(message);
		
		return "redirect: contact-page";
	}
	
	@RequestMapping("/search-page")
	public String searchBlogPosts(@RequestParam String text, Model model) {
		
		List<Post> thePosts = postDAO.searchBlogPosts(text);
		
		model.addAttribute("search", text);
		model.addAttribute("listPosts", thePosts);
		model.addAttribute("word", text);
		
		return "front/search-page";
	}

}
