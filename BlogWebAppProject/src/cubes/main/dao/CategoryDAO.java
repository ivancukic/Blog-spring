package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Category;

public interface CategoryDAO {
	
	public List<Category> getCategoryList(); // LIST OF CATEGORY
	
	public void saveCategory(Category category); // SAVE CATEGORY
	
	public Category getGategory(int id); // UPDATE CATEGORY
	
	public void deleteCategory(int id); // DELETE CATEGORY
	
	public List<Category> getCategoriesForFilter(); // LIST OF ALL CATEGORIES WITH POSTS

}
