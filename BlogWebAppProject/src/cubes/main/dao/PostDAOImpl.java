package cubes.main.dao;

import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cubes.main.entity.Post;
import cubes.main.entity.Slider;
import cubes.main.entity.Tag;

@Repository
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Post> getPostList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("from Post", Post.class);
		
		List<Post> postList = query.getResultList(); 
		
		return postList;
	}

	@Transactional
	@Override
	public void savePost(Post post) {
		
		Session session = sessionFactory.getCurrentSession();
	
		session.saveOrUpdate(post);
	}

	@Transactional
	@Override
	public Post getPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		return post;
	}

	@Transactional
	@Override
	public void deletePost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Post where id=:id");
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

	@Transactional
	@Override
	public List<Post> getImportantPost() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p where p.isImportant = 1");
		
		List<Post> postList = query.getResultList();
		
		return postList;
	}

	@Transactional
	@Override
	public List<Post> getPostListByCategory(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("from Post post where post.category.id = :id");

		query.setParameter("id", id);
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Post> getPostListByTag(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Tag tag = session.get(Tag.class, id);
		
		Hibernate.initialize(tag.getPosts());
		
		return tag.getPosts();
	}

	@Transactional
	@Override
	public Post getPostWithTag(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Post post = session.get(Post.class, id);
		
		Hibernate.initialize(post.getTags());
		
		return post;
	}

	@Transactional
	@Override
	public List<Post> searchBlogPosts(String text) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p where p.text like :post");
		
		query.setParameter("post", text);
		
		List<Post> posts = query.getResultList();
		
		return posts;
	}

	@Transactional
	@Override
	public Post getPreviousPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("from Post", Post.class);
		
		List<Post> postList = query.getResultList(); 
		
		Post post = session.get(Post.class, id);
		
		Post previous = new Post();
		
		for(int i=postList.size()-1; i>=0; i--) {
			
				if(postList.get(i)==post) {
					
					if(postList.get(i)==postList.get(0)) {
						
						previous = postList.get(postList.size()-1);
					}
					else {
						
						previous = postList.get(i-1);
					}
			}
		}	
		
		return previous;
	}

	@Transactional
	@Override
	public Post getNextPost(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("from Post", Post.class);
		
		List<Post> postList = query.getResultList(); 
		
		Post post = session.get(Post.class, id);
		
		Post next = new Post();
		
		for(int i=0; i<=postList.size()-1; i++) {
			
				if(postList.get(i)==post) {
					
					if(postList.get(i)==postList.get(postList.size()-1)) {
						
						next = postList.get(0);
					}
					else {
						
						next = postList.get(i+1);
					}
			}
		}	
		
		return next;
	}

	@Transactional
	@Override
	public List<Post> getPostListByAuthor(String author) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Post> query = session.createQuery("select p from Post p where p.author=:author");

		query.setParameter("author", author);
		
		return query.getResultList();
	}

}
