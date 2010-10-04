package controllers;


import java.util.List;

import models.Post;
import models.Question;
import play.mvc.Controller;

public class Questions extends Controller {
	
	public static void index(){
		Question newestQuestion = Question.find("order by postedAt desc").first();
        List<Question> olderQuestions = Question.find(
            "order by postedAt desc"
        ).from(1).fetch(9);
        render(newestQuestion, olderQuestions);
	}
	
	public static void postQuestion(String author,String content){
		index();
	}
	
	public static void show(Long id) {
	    Question q = Question.findById(id);
	    render(q);
	}

}
