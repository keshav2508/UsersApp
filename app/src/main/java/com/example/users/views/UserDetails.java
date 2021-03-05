package com.example.users.views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.users.R;

public class UserDetails extends AppCompatActivity {
    ImageView imageView ;
    TextView userName;
    TextView github_url;
    TextView img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        imageView = (ImageView) findViewById(R.id.imageView);
        userName = (TextView) findViewById(R.id.userName);
        github_url = (TextView) findViewById(R.id.github_URL);
        img_url = (TextView) findViewById(R.id.img_url);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("URL");
        String imgurl = bundle.getString("ImageURL");
        String name = bundle.getString("Name");

        userName.setText(name);
        github_url.setText(url);
        img_url.setText(imgurl);


    }
}