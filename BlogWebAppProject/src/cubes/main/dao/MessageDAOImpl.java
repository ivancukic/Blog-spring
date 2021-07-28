package cubes.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Message;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Message> getAllMessage() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Message> query = session.createQuery("from Message");
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public void saveMesage(Message message) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(message);
	}

	@Transactional
	@Override
	public long getUnreadMessageCount() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("select count(*) from Message m where m.isSeen=0 ");
		
		return (long) query.uniqueResult();
	}

	@Transactional
	@Override
	public Message getMessage(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Message.class, id);
	}

}
