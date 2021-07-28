package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Tag;

public interface TagDAO {
	
	public List<Tag> getTagList(); // LIST OF TAG
	
	public void saveTag(Tag tag); // SAVE TAG
	
	public Tag getTag(int id); // UPDATE TAG
	
	public void deleteTag(int id); // DELETE TAG
	
	public List<Tag> getTagsById(List<Integer> ids); // TAG COLLECTION FOR @ManyToMany
	
	public List<Tag> getTagListWithPosts(); // FOR Blog-Page

}
