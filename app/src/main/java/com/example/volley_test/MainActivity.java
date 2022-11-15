package com.example.volley_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Button buttonParse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        buttonParse = findViewById(R.id.button_parse);
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(v -> jsonParse());
    }

    private void jsonParse(){
        String url = "https://dev.farizdotid.com/api/daerahindonesia/kecamatan?id_kota=3273";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("kecamatan");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject kecamatan = jsonArray.getJSONObject(i);
                    String nama = kecamatan.getString("nama");

                    mTextViewResult.append( "Kecamatan : " + nama + "\n\n");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> error.printStackTrace());
        mQueue.add(request);
    }

}