package com.example.qodra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatScreen extends AppCompatActivity {

    public List<ItemChat> listChats = new ArrayList<>();
    private String API_URL = "http://220.158.204.63:8000/api/chatterbot/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RelativeLayout btnSend;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chat_screen);

        btnSend = findViewById(R.id.buttonSend);
        editText = findViewById(R.id.edChat);
        recyclerView = findViewById(R.id.rvChatScreen);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setReverseLayout(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();

                if (!message.equals("")) {
                    LoadChatData(message);
                }
                editText.setText("");
            }
        });

        adapter = new ChatAdapter(listChats, this);
        recyclerView.setAdapter(adapter);
    }

    public void startFAQScreen(View view) {
        Intent intent = new Intent(ChatScreen.this, FaqScreen.class);
        startActivity(intent);
    }

    // Volley String POST REQUEST
    private void LoadChatData(final String message) {

        ItemChat user = new ItemChat(message, "USER");
        listChats.add(user);

        // String Request
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String text = jsonObject.getString("text");
                            ItemChat bot = new ItemChat(text, "BOT");
                            listChats.add(bot);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Oops. Netword error!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Oops. Server error!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "Oops. Auth Failure error!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Oops. Parse error!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "Oops. No Connection error!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(getApplicationContext(), "Oops. Timeout error!", Toast.LENGTH_LONG).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("statement", message);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
}