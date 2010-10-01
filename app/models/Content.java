package models;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public abstract class Content {
	private Date timestamp;
	private String text;
	private User owner;
	private Map<User,Vote> votes;
	
	public Content(User o, Date time, String desc) throws MissingArgument{
		if (o == null || time == null ||desc == null) throw new MissingArgument();
		timestamp = time;
		text = desc;
		votes = new HashMap<User,Vote>();
		owner = o;
	}

	public Content(User owner2, String desc) throws MissingArgument {
		this(owner2,Calendar.getInstance().getTime(),desc);
	}
	// Accessor Methods
	public Date getTimestamp (  ) {
		return timestamp;
	}
	public String getText (  ) {
		return text;
	}
	public void setText ( String value  ) {
		text = value;
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
	
	public void delete(){
		for (User user : votes.keySet()){
			user.deleteVote(this);
		}
	}
	
	public void deleteVote(User user) {
		votes.remove(user);
		
	}

	public User getOwner() {
		return owner;
	}
	
	public String toString(){
		return this.getClass().getCanonicalName()+"("+this.getText()+")";
	}
}

