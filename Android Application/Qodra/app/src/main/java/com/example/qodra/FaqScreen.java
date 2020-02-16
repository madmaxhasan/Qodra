package com.example.qodra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FaqScreen extends AppCompatActivity {

    private TextView tvHowToUseQodra;
    private LinearLayout layoutHowToUseQodra;

    private TextView tvWhoBuildThatApp;
    private LinearLayout layoutWhoBuildThatApp;

    private TextView tvHowDoesThisAppWork;
    private LinearLayout layoutHowDoesThisAppWork;

    private TextView tvDocumentation;
    private LinearLayout layoutDocumenation;

    private TextView tvContactUs;
    private LinearLayout layoutContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_faq_screen);

        tvHowToUseQodra = findViewById(R.id.tvHowToUseQodra);
        layoutHowToUseQodra = findViewById(R.id.layoutHowToUseQodra);

        tvWhoBuildThatApp = findViewById(R.id.tvWhoBuildThatApp);
        layoutWhoBuildThatApp = findViewById(R.id.layoutWhoBuildThatApp);

        tvHowDoesThisAppWork = findViewById(R.id.tvHowDoesThisAppWork);
        layoutHowDoesThisAppWork = findViewById(R.id.layoutHowDoesThisAppWork);

        tvDocumentation = findViewById(R.id.tvDocumentation);
        layoutDocumenation = findViewById(R.id.layoutDocumenation);

        tvContactUs = findViewById(R.id.tvContactUs);
        layoutContactUs = findViewById(R.id.layoutContactUs);

        tvHowToUseQodra.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;

            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    layoutHowToUseQodra.setVisibility(View.VISIBLE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = true;
                } else {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = false;
                }
            }
        });

        tvWhoBuildThatApp.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;

            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.VISIBLE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = true;
                } else {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = false;
                }
            }
        });

        tvHowDoesThisAppWork.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;

            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.VISIBLE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = true;
                } else {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = false;
                }
            }
        });

        tvDocumentation.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;

            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.VISIBLE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = true;
                } else {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = false;
                }
            }
        });

        tvContactUs.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;

            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.VISIBLE);
                    isClicked = true;
                } else {
                    layoutHowToUseQodra.setVisibility(View.GONE);
                    layoutWhoBuildThatApp.setVisibility(View.GONE);
                    layoutHowDoesThisAppWork.setVisibility(View.GONE);
                    layoutDocumenation.setVisibility(View.GONE);
                    layoutContactUs.setVisibility(View.GONE);
                    isClicked = false;
                }
            }
        });
    }

    public void startChatScreen(View view) {
        Intent intent = new Intent(FaqScreen.this, ChatScreen.class);
        startActivity(intent);
    }
}
