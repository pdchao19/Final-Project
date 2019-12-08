package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button find;
    TextView fact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find = findViewById(R.id.generate);
        fact = findViewById(R.id.fact);
        final MainActivity activity = this;
        // Request a string response from the provided URL.
        find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(activity);
                String url = "https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=1";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String catFact = response.get("text").toString();
                            fact.setText(catFact);
                            System.out.println(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(request);
            }
        });


    }
}
