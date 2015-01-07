package com.teachindia.teachindia.jsonlayout;

/**
 * Created by ARavi on 12/13/2014.
 */
public class Question_Model {
    private String username;
    private String question;
    private String image;
    private String question_id;

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getAsked_time() {
        return asked_time;
    }

    public void setAsked_time(String asked_time) {
        this.asked_time = asked_time;
    }

    private String asked_time;



    public Question_Model(){

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }



    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }
}
