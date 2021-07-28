package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Comments;

public interface CommentsDAO {

	public List<Comments> getCommentsList(); // LIST OF COMMENTS
	
	public void approveComments(int id); // APPROVE COMMENT
	
	public void saveComment(Comments comments); // SAVE COMMENT ON BLOG-POST-PAGE
	
	public List<Comments> getCommentsListByPost(int id); // COMMENTS ON ONE BLOG 
	
	public long getNumberOfComments(int id); // NUMBER OF COMMENTS FOR EACH POST
}
