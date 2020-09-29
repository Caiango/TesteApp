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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testeapp.R;
import com.example.testeapp.retrofit.AccessToken;
import com.example.testeapp.retrofit.GitHubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView myRepo;
    Button login;
    String github_client_id;
    String github_app_url;
    String github_client_secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txGreeting = findViewById(R.id.txGreetings);
        myRepo = findViewById(R.id.txMyRepo);
        login = findViewById(R.id.btnLogin);


        //variáveis que recebem client ID, Client Secret e app url callback
        github_client_id = "SEU GITHUB CLIENT ID";
        github_app_url = "testeapp://callback";
        github_client_secret = "SEU GITHUB CLIENT SECRET";

        String string = "Olá! Sou " + "<b>" + getString(R.string.myName) + "</b>" + " e esse é meu aplicativo referente ao teste!";

        myRepo.setOnClickListener(this);
        login.setOnClickListener(this);

        txGreeting.setText(Html.fromHtml(string));

    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();

        if (uri != null && uri.toString().startsWith(github_app_url)) {

            String code = uri.getQueryParameter("code");

            if (code != null) {

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://github.com/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                GitHubClient client = retrofit.create(GitHubClient.class);

                Call<AccessToken> accessTokenCall = client.getAccessToken(

                        github_client_id,
                        github_client_secret,
                        code

                );


                accessTokenCall.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                        AccessToken accessToken = response.body();

                        //Aqui está o TOKEN!
                        accessToken.getAccessToken();
                        accessToken.getTokenType();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == myRepo) {
            String url = myRepo.getText().toString();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } else if (view == login) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" + "?client_id=" + github_client_id + "&scope=repo&redirect_uri" + github_app_url));
            startActivity(intent);
        }
    }
}