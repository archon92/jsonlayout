package com.teachindia.teachindia.jsonlayout;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class clicked extends ActionBarActivity {
    ListView l;
    Answer_Adapter answer_adapter;
    ArrayList<Answer_Model> answerlist;
    TextView tvusername,tvquestion,tvaskedtime,tvquestion_id;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicked);
        tvusername=(TextView)findViewById(R.id.tvusername);
        tvquestion=(TextView)findViewById(R.id.tvquestion);
        tvaskedtime=(TextView)findViewById(R.id.tvaskedtime);
        tvquestion_id=(TextView)findViewById(R.id.tvquestion_id);

        imageView=(ImageView)findViewById(R.id.imageView);
        l = (ListView) findViewById(R.id.listView2);
        answerlist = new ArrayList<>();
        new answerfeed().execute("http://10.163.179.199:8222/MvcApplication1/Enigma/PostComment ");

        String username=getIntent().getExtras().getString("username");
        String question=getIntent().getExtras().getString("question");
        String asked_time=getIntent().getExtras().getString("asked_time");
        String qid1=getIntent().getExtras().getString("qid");
        String u=getIntent().getExtras().getString("image");
        Picasso.with(this).load(u).into(imageView);


        tvusername.setText(username);
        tvquestion.setText(question);
        tvquestion_id.setText(qid1);
        tvaskedtime.setText(asked_time);

    }
    public void answer_this_question(View v){
        Intent x=new Intent(this,answer_question.class);
        x.putExtra("question_id",tvquestion_id.getText().toString());
        startActivity(x);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.testmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){

            case R.id.new_question:
                Intent i=new Intent(this,Ask_question.class);
                startActivity(i);
                break;

            case R.id.refresh:
                answerfeed ref=new answerfeed();
                answerlist.clear();
         //       ref.execute("http://10.163.179.199:8222/MvcApplication1/Enigma/ListQuestions");
                // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);
    }


    public class answerfeed extends AsyncTask<String, Void, Boolean> {


        @Override
        protected Boolean doInBackground(String... params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpResponse response = client.execute(httpGet);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jobj = new JSONObject(data);
                    for (int i = 0; i < jobj.length(); i++) {

                        Answer_Model answer = new Answer_Model();

                        answer.setActualanswer(jobj.getString("object_or_array"));
                        answer.setAnsweredby("The actual question to be displayed");//jobj.getString("validate")
                        answer.setAnsweredtime(jobj.getString("parse_time_nanoseconds"));

                        answerlist.add(answer);


                    }
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


            if (aBoolean == false) {
                Toast.makeText(getApplicationContext(), "data not found", Toast.LENGTH_LONG).show();
            } else {
                Answer_Adapter answer_adapter1 = new Answer_Adapter(getApplicationContext(), R.layout.answer_single, answerlist);
                l.setAdapter(answer_adapter1);
                // question_adapter.notifyDataSetChanged();
            }
        }

    }

}







