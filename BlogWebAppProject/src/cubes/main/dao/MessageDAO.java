package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Message;

public interface MessageDAO {
	
	public List<Message> getAllMessage(); // ALL MESSAGE
	
	public void saveMesage(Message message); // SAVE MESSAGE
	
	public long getUnreadMessageCount(); // COUNT UNREAD MESSAGE
	
	public Message getMessage(int id); // FOR ADMINCONTROLLOR TO SEE MESSAGE

}
