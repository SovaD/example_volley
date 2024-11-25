package com.example.example_volley;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    private static final String TAG = "taggg";
    private String url = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        //query queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//success
                List<Post> posts = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject postJson = response.getJSONObject(i);
                        Post post = new Post(postJson.getInt("id"), postJson.getString("title"), postJson.getString("body"));
                        posts.add(post);
                    }
                    for (Post post : posts) {
                        Log.i(TAG, post.toString());
                        //listview or recyclerView
                    }


                } catch (Exception ex) {
                    Log.e(TAG, "Error in server: " + ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //success
//                        textView.setText("Answer from server: "+response);
//                    }
//                },
//                new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //error
//                textView.setText("Error: "+error.getMessage());
//            }
//        });
//
//        requestQueue.add(stringRequest);
    }
}