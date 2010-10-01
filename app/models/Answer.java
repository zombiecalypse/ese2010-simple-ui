package models;

import java.util.Date;

public class Answer extends Content {
	private final Question question;
	
	public Answer(Question thequestion,User owner,Date t, String desc) throws MissingArgument{
		super(owner,t,desc);
		if (thequestion == null) throw new MissingArgument();
		question = thequestion;
		question.addAnswer(this);
	}
	public Answer(Question thequestion,User owner, String desc) throws MissingArgument{
		super(owner,desc);
		if (thequestion == null) throw new MissingArgument();
		question = thequestion;
		question.addAnswer(this);
	}

	public Question getQuestion() {
		return question;
	}
	
	public void delete(){
		super.delete();
		question.deleteAnswer(this);
	}
}

