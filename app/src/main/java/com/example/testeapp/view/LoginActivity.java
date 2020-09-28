package com.example.testeapp.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView myRepo;
    Button login;
    String github_client_id;
    String github_app_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txGreeting = findViewById(R.id.txGreetings);
        myRepo = findViewById(R.id.txMyRepo);
        login = findViewById(R.id.btnLogin);

        github_client_id = getString(R.string.github_app_id);
        github_app_url = getString(R.string.github_app_url);

        String string = "Olá! Sou " + "<b>" + getString(R.string.myName) + "</b>" + " e esse é meu aplicativo referente ao teste!";

        myRepo.setOnClickListener(this);
        login.setOnClickListener(this);

        txGreeting.setText(Html.fromHtml(string));


    }

    @Override
    public void onClick(View view) {
        if (view == myRepo) {
            String url = myRepo.getText().toString();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (view == login) {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" + "?client_id=" + github_client_id + "&scope=repo&redirect_uri" + github_app_url));
            startActivity(intent);
        }
    }
}