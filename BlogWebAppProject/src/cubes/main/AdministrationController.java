package cubes.main;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import cubes.main.dao.UserDAO;
import cubes.main.entity.Category;
import cubes.main.entity.ChangePassword;
import cubes.main.entity.Comments;
import cubes.main.entity.Message;
import cubes.main.entity.Post;
import cubes.main.entity.Slider;
import cubes.main.entity.Tag;
import cubes.main.entity.User;

@Controller
@RequestMapping("/administration")
public class AdministrationController {
	
	@Autowired
	private SliderDAO sliderDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private TagDAO tagDAO;
	
	@Autowired
	private CommentsDAO commentsDAO;
	
	@Autowired
	private MessageDAO messageDAO;
	

//////////////////////////////////////////////HERE STARTS USER////////////////////////////////////////////////////////
	
	@RequestMapping("/user-list")
	public String getUserListPage(Principal principal, Model model) {
		
		List<User> listUser = userDAO.getUserList();	
		model.addAttribute("userList", listUser);
		
		model.addAttribute("user", userDAO.getUserByUsername("mimi"));
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		return "user-list";
	}
	
	@RequestMapping("/user-enable")
	public String enableUser(@RequestParam String username) {
		
		userDAO.enableUser(username);
		
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-form")
	public String getUserForm(Model model, Principal principal) {
		
		User user = new User();
		
		model.addAttribute("user", user);
		model.addAttribute("roles", userDAO.getRoles());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		return "user-form";
	}

	@RequestMapping("/user-save")
	public String saveUser(@ModelAttribute User user) {
		
		String passwordEncode = new BCryptPasswordEncoder().encode(user.getPassword());
		
		user.setEnabled(true);
		
		user.setPassword("{bcrypt}" + passwordEncode);
		
		userDAO.saveUser(user);
		
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-delete")
	public String deleteUser(@RequestParam String username) {
		
		userDAO.deleteUser(username);
		
		return "redirect:/administration/user-list";
	}

	@RequestMapping("/user-edit-profile")
	public String getUserEditProfile(Principal principal, Model model) {
		
		User user = userDAO.getUserByUsername(principal.getName());
		
		model.addAttribute("user", user);
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		return "user-edit-profile";
	}

	@RequestMapping("/user-edit")
	public String getUserEdit(@ModelAttribute User user) {
		
		userDAO.saveUser(user);
		
		return "redirect:/administration/user-list";
	}
	
	@RequestMapping("/user-change-password")
	public String getUserChangePassword(Model model, Principal principal) {
		
		model.addAttribute("changePassword", new ChangePassword());
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "user-change-password";
	}
	
	@RequestMapping("/user-change-password-action")
	public String getUserChangePasswordAction(@ModelAttribute ChangePassword changePassword, Principal principal, Model model) {
		
		if(changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {
			
			User user = userDAO.getUserByUsername(principal.getName());
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			if(encoder.matches(changePassword.getOldPassword(), user.getPassword().replace("{bcrypt}", ""))) {
				
				user.setPassword("{bcrypt}" + encoder.encode(changePassword.getNewPassword()));
				
				userDAO.saveUser(user);
			}
			else {
				
				return "user-change-password";
			}
			
		}
		else {
			
			return "user-change-password";
		}
		
		return "redirect:/administration/user-list";
	}
	
//////////////////////////////////////////////HERE ENDS USER//////////////////////////////////////////////////////////
//////////////////////////////////////////////HERE STARTS SLIDER//////////////////////////////////////////////////////
	

	@RequestMapping("/slider-list")
	public String getSliderList(Model model, Principal principal) {
		
		List<Slider> sliderList = sliderDAO.getSliderList();
		
		model.addAttribute("sliderList", sliderList);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());	
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "slider-list";
	}
	
	@RequestMapping("/slider-form")
	public String getSliderForm(Model model, Principal principal) {
		
		Slider slider = new Slider();	
		model.addAttribute("slider", slider);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "slider-form";
	}
	
	@RequestMapping("/slider-form-update")
	public String getSliderUpdateForm(@RequestParam int id, Model model) {
		
		Slider slider = sliderDAO.getSlider(id);
		
		model.addAttribute("slider", slider);
		
		return "slider-form";
	}
	
	@RequestMapping("/slider-save")
	public String saveSlider(@ModelAttribute Slider slider) {
		
		sliderDAO.saveSlider(slider);
		
		return "redirect:/administration/slider-list";
	}
	
	@RequestMapping("/slider-delete")
	public String deleteSlider(@RequestParam int id) {
		
		sliderDAO.deleteSlider(id);
		
		return "redirect:/administration/slider-list";
	}
	
//////////////////////////////////////////////HERE ENDS SLIDER/////////////////////////////////////////////////////////
//////////////////////////////////////////////HERE STARTS POST/////////////////////////////////////////////////////////
	
	@RequestMapping("/post-list")
	public String getPostList(Model model, Principal principal) {
		
		List<Post> postList = postDAO.getPostList();
		
		model.addAttribute("postList", postList);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "post-list";
	}
	
	@RequestMapping("/post-form")
	public String getPostForm(Model model, Principal principal) {
		
		Post post = new Post();
				
		model.addAttribute("post", post);
		
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		model.addAttribute("author", u.getName() + " " + u.getSurname());
		model.addAttribute("authorPicture", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "post-form";
	}
	
	@RequestMapping("/post-form-update")
	public String getPostUpdateForm(@RequestParam int id, Model model, Principal principal) {
		
		Post post = postDAO.getPostWithTag(id);
		
		List<Category> categoryList = categoryDAO.getCategoryList();
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("post", post);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("tagList", tagList);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		model.addAttribute("author", u.getName() + " " + u.getSurname());
				
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "post-form";
	}
	
	@RequestMapping("/post-save")
	public String savePost(@ModelAttribute Post post) {
		
		Category category = categoryDAO.getGategory(post.getCategory().getId());
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for(Tag tag : post.getTags()) {
			
			ids.add(Integer.parseInt(tag.getName()));
		}
		
		List<Tag> tags = tagDAO.getTagsById(ids);
		
		post.setCategory(category);
		post.setTags(tags);
		
		postDAO.savePost(post);
		
		return "redirect:/administration/post-list";
	}
	
	@RequestMapping("/post-delete")
	public String deletePost(@RequestParam int id) {
		
		postDAO.deletePost(id);
		
		return "redirect:/administration/post-list";
	}
	
	
//////////////////////////////////////////////HERE ENDS POST///////////////////////////////////////////////////////////
//////////////////////////////////////////////HERE STARTS CATEGORY/////////////////////////////////////////////////////
	
	@RequestMapping("/category-list")
	public String getCategoryList(Model model, Principal principal) {
		
		List<Category> categoryList = categoryDAO.getCategoryList();
		
		model.addAttribute("categoryList", categoryList);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "category-list";
	}
	
	@RequestMapping("/category-form")
	public String getCategoryForm(Model model, Principal principal) {
		
		Category category = new Category();
		
		model.addAttribute("category", category);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "category-form";
	}
	
	@RequestMapping("/category-form-update")
	public String getCategoryUpdateForm(@RequestParam int id, Model model) {
		
		Category category = categoryDAO.getGategory(id);
		
		model.addAttribute("category", category);
		
		return "category-form";
	}
	
	@RequestMapping("/category-save")
	public String saveCategory(@ModelAttribute Category category) {
		
		categoryDAO.saveCategory(category);
		
		return "redirect:/administration/category-list";
	}
	
	@RequestMapping("/category-delete")
	public String deleteCategory(@RequestParam int id) {
		
		categoryDAO.deleteCategory(id);
		
		return "redirect:/administration/category-list";
	}
	
	
	
//////////////////////////////////////////////HERE ENDS CATEGORY///////////////////////////////////////////////////////
//////////////////////////////////////////////HERE STARTS TAG//////////////////////////////////////////////////////////

	@RequestMapping("/tag-list")
	public String getTagList(Model model, Principal principal) {
		
		List<Tag> tagList = tagDAO.getTagList();
		
		model.addAttribute("tagList", tagList);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "tag-list";
	}
	
	@RequestMapping("/tag-form")
	public String getTagForm(Model model, Principal principal) {
		
		Tag tag = new Tag();
		
		model.addAttribute("tag", tag);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "tag-form";
	}
	
	@RequestMapping("/tag-form-update")
	public String getTagUpdateForm(@RequestParam int id, Model model, Principal principal) {
		
		Tag tag = tagDAO.getTag(id);
		
		model.addAttribute("tag", tag);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
				
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "tag-form";
	}
	
	@RequestMapping("/tag-save")
	public String saveTag(@ModelAttribute Tag tag) {
		
		tagDAO.saveTag(tag);
		
		return "redirect:/administration/tag-list";
	}
	
	@RequestMapping("/tag-delete")
	public String deleteTag(@RequestParam int id) {
		
		tagDAO.deleteTag(id);
		
		return "redirect:/administration/tag-list";
	}
	

//////////////////////////////////////////////HERE ENDS TAG////////////////////////////////////////////////////////////
//////////////////////////////////////////////HERE STARTS COMMENTS/////////////////////////////////////////////////////
	
	@RequestMapping("/comments-list")
	public String getCommentsListPage(Principal principal, Model model) {
		
		List<Comments> listComments = commentsDAO.getCommentsList();
		
		model.addAttribute("commentsList", listComments);
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		return "comments-list";
	}
	
	@RequestMapping("/comments-enable")
	public String enableComment(@RequestParam int id) {
		
		commentsDAO.approveComments(id);
		
		return "redirect:/administration/comments-list";
	}
	
	
	
//////////////////////////////////////////////HERE ENDS COMMENTS///////////////////////////////////////////////////////
//////////////////////////////////////////////HERE STARTS MESSAGE//////////////////////////////////////////////////////

	@RequestMapping("message-list")
	public String getMessageList(Model model, Principal principal) {
		
		model.addAttribute("messageList", messageDAO.getAllMessage());
		model.addAttribute("messageCount", messageDAO.getUnreadMessageCount());
		
		// SLIKA U DESNOM UGLU
		User u = userDAO.getUserByUsername(principal.getName());
		model.addAttribute("pic", u.getImage());
		
		return "message-list";
	}
	
	@RequestMapping("message-seen")
	public String getMarkmessageSeen(@RequestParam int id) {
		
		Message message = messageDAO.getMessage(id);
		
		message.setIsSeen(true);
		
		messageDAO.saveMesage(message);
		
		return "redirect: message-list";
	}

//////////////////////////////////////////////HERE ENDS MESSAGE////////////////////////////////////////////////////////

}
