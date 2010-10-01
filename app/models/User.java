package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
	private String name;
	private final Set<Question> questions;
	private final Set<Answer> answers;
	private final Set<Content> votes;
	

	public User(String the_name) throws MissingArgument {
		if (the_name == "" ||
				the_name == null) throw new MissingArgument();
		name = the_name;
		questions = new HashSet<Question>();
		answers = new HashSet<Answer>();
		votes = new HashSet<Content>();
	}
	
	public String getName(  ) {
		return name;
	}
	
	public void deleteVote(Content c){
		votes.remove(c);
	}
	
	public void delete() {
		for (Content content : votes) {
			content.deleteVote(this);
		}
		for (Question q : questions) {
			q.delete();
		}
		for (Answer a : answers) {
			a.delete();
		}
	}
	public Question askQuestion(Date time,String desc) throws MissingArgument{
		Question question = new Question(this, time, desc);
		questions.add(question);
		return question;
	}
	
	public Answer answerQuestion(Question q,Date time,String desc) throws MissingArgument{
		Answer answer = new Answer(q,this, time, desc);
		answers.add(answer);
		return answer;
	}
	
	public Question askQuestion(String desc) throws MissingArgument{
		Question question = new Question(this,desc);
		questions.add(question);
		return question;
	}
	
	public String toString(){
		return name;
	}
	
	public Answer answerQuestion(Question q,String desc) throws MissingArgument{
		Answer answer = new Answer(q,this, desc);
		answers.add(answer);
		return answer;
	}
	
	public void vote(Vote v,Content c){
		c.vote(v, this);
		votes.add(c);
	}
}

