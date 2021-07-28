package cubes.main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cubes.main.entity.Comments;
import cubes.main.entity.User;

@Repository
public class CommentsDAOImpl implements CommentsDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Comments> getCommentsList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Comments> query = session.createQuery("from Comments", Comments.class);
		
		List<Comments> listComments = query.getResultList(); 
		
		return listComments;
	}

	@Transactional
	@Override
	public void approveComments(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Comments comments = session.get(Comments.class, id);
		
		comments.setIsApproved(!comments.getIsApproved());
		
		session.saveOrUpdate(comments);
		
	}

	@Transactional
	@Override
	public void saveComment(Comments comments) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(comments);
	}

	@Transactional
	@Override
	public List<Comments> getCommentsListByPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Comments> query = session.createQuery("select c from Comments c where c.post.id =:id", Comments.class);
		
		query.setParameter("id", id);
		
		List<Comments> listComments = query.getResultList();
		
		return listComments;
	}

	@Transactional
	@Override
	public long getNumberOfComments(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query queryCount = session.createQuery("select count(post.id) from Post post where post.comments.id=:id");
		
		queryCount.setParameter("id", id);
		
		Comments comment = new Comments();
		
		comment.setCount((long) queryCount.uniqueResult());
		
		return comment.getCount();
	}



}
