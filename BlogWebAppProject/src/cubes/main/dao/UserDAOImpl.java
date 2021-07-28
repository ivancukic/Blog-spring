package cubes.main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cubes.main.entity.Post;
import cubes.main.entity.Role;
import cubes.main.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<User> getUserList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<User> query = session.createQuery("from users", User.class);
		
		List<User> listUser = query.getResultList(); 
		
		return listUser;
	}

	@Transactional
	@Override
	public User getUserByUsername(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, username);
		
		return user;
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(user);
	}

	@Transactional
	@Override
	public void deleteUser(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, username);
		
		session.delete(user);
	}

	@Transactional
	@Override
	public void enableUser(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, username);
		
		user.setEnabled(!user.getEnabled());
		
		session.saveOrUpdate(user);
		
	}

	@Transactional
	@Override
	public List<Role> getRoles() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Role> query = session.createQuery("from roles", Role.class);
		
		List<Role> listRole = query.getResultList(); 
		
		return listRole;
		
	}

	
}
