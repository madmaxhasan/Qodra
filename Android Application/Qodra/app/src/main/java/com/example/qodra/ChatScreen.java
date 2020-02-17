package com.example.qodra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import java.util.Locale;
import java.util.Map;

public class ChatScreen extends AppCompatActivity {

    public List<ItemChat> listChats = new ArrayList<>();
    private String API_URL = "http://220.158.204.63:8000/api/chatterbot/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RelativeLayout btnSend, btnVoice;
    private EditText editText;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chat_screen);

        checkPermission();

        // Variables
        btnSend = findViewById(R.id.buttonSend);
        btnVoice = findViewById(R.id.buttonVoice);
        editText = findViewById(R.id.edChat);
        recyclerView = findViewById(R.id.rvChatScreen);

        // Setting Linear Layout for RecyclerView
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        linearLayoutManager.setReverseLayout(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Chat Adapter
        adapter = new ChatAdapter(listChats, this);

        // Creating Speech Recognizer Variable and Intent
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Passing Language Model to Intent
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        // Speech Recognition Listener
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {
                if (error == SpeechRecognizer.ERROR_AUDIO) {
                    Toast.makeText(getApplicationContext(), "Audio Recording Error.", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS) {
                    Toast.makeText(getApplicationContext(), "Insufficient Permissions", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_NETWORK) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_NETWORK_TIMEOUT) {
                    Toast.makeText(getApplicationContext(), "Network Operation Timed Out.", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_NO_MATCH) {
                    Toast.makeText(getApplicationContext(), "No Recognition Result Matched", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_RECOGNIZER_BUSY) {
                    Toast.makeText(getApplicationContext(), "Recognition Service Busy", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_SPEECH_TIMEOUT) {
                    Toast.makeText(getApplicationContext(), "No Speech Input", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_SERVER) {
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
                } else if (error == SpeechRecognizer.ERROR_CLIENT) {
                    Toast.makeText(getApplicationContext(), "Client Errors", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    String statement = matches.get(0);              // Getting the first result and the Best one
                    editText.setText(statement);                    // Set the statement to EditText
                    editText.setHint("Ask me from place to place");
                    editText.setSelection(statement.length());
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        // Send Button Listener
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();

                if (!message.equals("")) {
                    ItemChat user = new ItemChat(message, "USER");  // Add data to USER CHAT
                    listChats.add(user);

                    adapter.notifyDataSetChanged();     // Refresh the adapter
                    recyclerView.setAdapter(adapter);

                    GetBotReply(message);               // Getting BOT response from API

                    recyclerView.setAdapter(adapter);
                }
                editText.setText("");
            }
        });

        // Voice Button Listener
        btnVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:             // Button Pressed Stoped
                        speechRecognizer.stopListening();
                        break;

                    case MotionEvent.ACTION_DOWN:           // Button Pressed
                        editText.setHint("Listening...");
                        speechRecognizer.startListening(speechRecognizerIntent);
                        break;
                }
                return false;
            }
        });
    }

    public void startFAQScreen(View view) {
        Intent intent = new Intent(ChatScreen.this, FaqScreen.class);
        startActivity(intent);
    }

    // Volley String POST REQUEST
    private void GetBotReply(final String message) {

        // String Request
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);   // Parse JSON DATA and grab BOT Response
                            String text = jsonObject.getString("text");

                            ItemChat bot = new ItemChat(text, "BOT");   // Add data to BOT CHAT
                            listChats.add(bot);

                            adapter.notifyDataSetChanged();     // Refresh the adapter
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

    // Asking for Permission on RECORD AUDIO
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
}