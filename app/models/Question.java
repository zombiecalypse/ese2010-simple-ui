package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 * Questions that a user may ask.
 * @author aaron
 *
 */
public class Question extends Post {

	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	public List<Answer> answers;

	public Question(User owner,Date t, String desc){
		super(owner,t,desc);
		this.answers = new ArrayList<Answer>();
	}
	
	public Question(User owner, String desc){
		super(owner,desc);
		this.answers = new ArrayList<Answer>();
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public Question addAnswer(Answer a){
		this.answers.add(a);
		this.save();
		return this;
	}
	
	public Question deleteAnswer(Answer a){
		answers.remove(a);
		this.save();
		return this;
	}
	
}

