package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Questions that a user may ask.
 * @author aaron
 *
 */
public class Question extends Content {
	private Set<Answer> answers;

	public Question(User owner,Date t, String desc) throws MissingArgument{
		super(owner,t,desc);
		answers = new HashSet<Answer>();
	}
	
	public Question(User owner, String desc) throws MissingArgument{
		super(owner,desc);
	}
	
	public Set<Answer> getAnswers() {
		return answers;
	}
	
	public void addAnswer(Answer a){
		answers.add(a);
	}
	
	public void deleteAnswer(Answer a){
		answers.remove(a);
	}
}

