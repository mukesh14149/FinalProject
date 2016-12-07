package com.example.showjoke;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PresentJoke extends AppCompatActivity {
    private static final String JOKE = "joke";

    public static Intent launchIntent(Context context, String joke) {
        Intent intent = new Intent(context, PresentJoke.class);
        intent.putExtra(JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.present_joke_activity);
        String joke = getIntent().getStringExtra(JOKE);
        if (joke == null)
            joke = "No joke found";

        TextView textView = (TextView) findViewById(R.id.jokes);
        textView.setText(joke);
    }
}
