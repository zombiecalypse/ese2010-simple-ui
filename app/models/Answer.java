package models;

import java.util.Date;

import javax.persistence.ManyToOne;

public class Answer extends Post {
	@ManyToOne
	public Question question;
	
	public Answer(Question thequestion,User owner,Date t, String desc){
		super(owner,t,desc);
		assert !(thequestion == null);
		question = thequestion;
		question.addAnswer(this);
	}
	public Answer(Question thequestion,User owner, String desc){
		super(owner,desc);
		assert !(thequestion == null);
		question = thequestion;
		question.addAnswer(this);
	}

	public Question getQuestion() {
		return question;
	}
	
	public void deleteMe(){
		super.deleteMe();
		question.deleteAnswer(this);
		delete(); 	
	}
}

