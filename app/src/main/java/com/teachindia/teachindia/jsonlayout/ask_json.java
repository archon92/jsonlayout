package com.teachindia.teachindia.jsonlayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by ARavi on 12/22/2014.
 */
public class ask_json {
    private int hour;
    private int minutes;
    private int seconds;
    private int month;
    private int day;
    private int year;
    private Calendar c;

    public String ask_json() throws JSONException {

        hour= c.get(Calendar.HOUR);
        minutes=c.get(Calendar.MINUTE);
        seconds=c.get(Calendar.SECOND);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        year=c.get(Calendar.YEAR);
        String created_date=day+"/"+month+"/"+year+" "+hour+":"+minutes+":"+seconds;

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("UserId",1);
        //jsonObject.put("time",created_date);
        jsonObject.put("QuestionMessage",Ask_question.etquestiontitle.getText());
        jsonObject.put("QuestionBoardId",1);

        return jsonObject.toString();

    }
}
