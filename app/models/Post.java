package models;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@javax.persistence.Entity
public abstract class Post extends Model {
	public String title;
	public Date postedAt;
	    
    @Lob
	public String content;
	    
	@ManyToOne
	public User author;
	
	private Date timestamp;
	private Map<User,Vote> votes;
	
	public Post(User o, Date time, String desc){
		assert ! (o == null || time == null ||desc == null);
		timestamp = time;
		content = desc;
		votes = new HashMap<User,Vote>();
		author = o;
	}

	public Post(User owner2, String desc){
		this(owner2,Calendar.getInstance().getTime(),desc);
	}
	// Accessor Methods
	public Date getTimestamp (  ) {
		return timestamp;
	}
	public String getText (  ) {
		return content;
	}
	public void setText ( String value  ) {
		content = value;
	}
	// Operations
	/**
	 * Vote the given content UP or DOWN. Keeps track of the users, that voted, so that
	 * <ul><li> they can't vote twice for the same thing
	 * <li>it is easier to delete a vote.
	 */
	public void vote (Vote v, User user) {
		votes.put(user,v);
	}
	
	public int getRating() {
		int result = 0;
		for (Entry<User,Vote> entry : votes.entrySet()) {
			if (entry.getValue() == Vote.UP){
				result += 1;
			}
			else {
				result -= 1;
			}
		}
		return result;
	}
	
	public void deleteMe(){
		for (User user : votes.keySet()){
			user.deleteVote(this);
		}
		delete();
	}
	
	public void deleteVote(User user) {
		votes.remove(user);
		
	}

	public User getOwner() {
		return author;
	}
	
	public String toString(){
		return this.getClass().getCanonicalName()+"("+this.getText()+")";
	}
}

