package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@javax.persistence.Entity
public class User extends Model {
	
	private String name;
	
	@OneToMany(targetEntity=Post.class,mappedBy="author", cascade=CascadeType.ALL)
	public List<Question> questions;
	
	@OneToMany(targetEntity=Post.class,mappedBy="author", cascade=CascadeType.ALL)
	public List<Answer> answers;
	
	@ManyToMany
	public List<Post> votes;
	

	public User(String the_name){
		assert ! (the_name == "" ||
				the_name == null);
		name = the_name;
		questions = new ArrayList<Question>();
		answers = new ArrayList<Answer>();
		votes = new ArrayList<Post>();
	}
	
	public String getName(  ) {
		return name;
	}
	
	public void deleteVote(Post c){
		votes.remove(c);
	}
	
	public void deleteMe() {
		for (Post post : votes) {
			post.deleteVote(this);
		}
		for (Question q : questions) {
			q.deleteMe();
		}
		for (Answer a : answers) {
			a.deleteMe();
		}
		delete();
	}
	public Question askQuestion(Date time,String desc){
		Question question = new Question(this, time, desc);
		questions.add(question);
		return question;
	}
	
	public Answer answerQuestion(Question q,Date time,String desc){
		Answer answer = new Answer(q,this, time, desc);
		answers.add(answer);
		return answer;
	}
	
	public Question askQuestion(String desc){
		Question question = new Question(this,desc);
		questions.add(question);
		return question;
	}
	
	public String toString(){
		return name;
	}
	
	public Answer answerQuestion(Question q,String desc){
		Answer answer = new Answer(q,this, desc);
		answers.add(answer);
		return answer;
	}
	
	public void vote(Vote v,Post c){
		c.vote(v, this);
		votes.add(c);
	}
}

