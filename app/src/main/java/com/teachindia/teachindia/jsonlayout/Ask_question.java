package com.teachindia.teachindia.jsonlayout;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ARavi on 12/14/2014.
 */
public class Ask_question extends Activity{
   ArrayList<String> myquestionids= new ArrayList<String>();
    public static  EditText etquestiontitle;
    public String created_time;
    Button post;
    public int hour;
    public int minutes;
    public int seconds;
    public int month;
    public int day;
    public int year;
    public Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_question_layout);
        etquestiontitle=(EditText)findViewById(R.id.etquestiontitle);
        post=(Button)findViewById(R.id.buttonquestionpost);

    }
    public void post_question(View v){
        /*minutes=c.get(Calendar.MINUTE);
        seconds=c.get(Calendar.SECOND);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        year=c.get(Calendar.YEAR);
        created_time=day+"/"+month+"/"+year+" "+hour+":"+minutes+":"+seconds;*/
        ask_question_async ask=new ask_question_async();
        ask.execute("http://10.163.179.199:8222/MvcApplication1/Enigma/PostQuestion");//My server url to hit
        Toast.makeText(getApplicationContext(),"successfully posted",Toast.LENGTH_LONG).show();
        finish();
      }
    public class ask_question_async extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost post= new HttpPost(params[0]);
            /*hour= c.get(Calendar.HOUR);
            minutes=c.get(Calendar.MINUTE);
            seconds=c.get(Calendar.SECOND);
            month=c.get(Calendar.MONTH);
            day=c.get(Calendar.DAY_OF_MONTH);
            year=c.get(Calendar.YEAR);
            String created_time=day+"/"+month+"/"+year+" "+hour+":"+minutes+":"+seconds;*/

            String json="";
            try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("UserId",1);
            //jsonObject.put("time",created_date);
            jsonObject.put("QuestionMessage",Ask_question.etquestiontitle.getText());
            jsonObject.put("QuestionBoardId",1);
            //jsonObject.put("Date",created_time);
            json=jsonObject.toString();
            StringEntity se=new StringEntity(json);
            //se.setContentType("application/json");
            post.setEntity(se);
            HttpResponse response=httpClient.execute(post);
            int statuscode=response.getStatusLine().getStatusCode();
            HttpEntity response_entity=response.getEntity();
            String rquestion_id= EntityUtils.toString(response_entity);
            JSONObject question_id=new JSONObject(rquestion_id);
            myquestionids.add(question_id.get("question_id").toString());




            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
