package com.example.testeapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.testeapp.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.GithubAuthProvider;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void accessGitHubToken(String accessToken) {
        accessLoginData(
                "github",
                accessToken
        );
    }

    private void accessLoginData(String provider, String... tokens) {
        if (tokens != null && tokens.length > 0 && tokens[0] != null) {
            AuthCredential credential = FacebookAuthProvider.getCredential(tokens[0]);
            credential = provider.equalsIgnoreCase("github") ? GithubAuthProvider.getCredential(tokens[0]) : credential;

        }
    }

    public void sendLoginGitHubData(View view) {

    }
}