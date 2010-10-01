package controllers;

import play.mvc.Controller;

public class Questions extends Controller {
	public static void index(){
		render();
	}
	
	public static void postQuestion(String author,String content){
		System.out.println("posted "+author+":"+content);
		index();
	}

}
