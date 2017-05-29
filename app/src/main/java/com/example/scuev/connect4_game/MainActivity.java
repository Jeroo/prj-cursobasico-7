package com.example.scuev.connect4_game;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().getDecorView().setSystemUiVisibility
        (
              View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        //Get evens from buttons
        findViewById(R.id.about_button).setOnClickListener(this);
        findViewById(R.id.play_game_button_two).setOnClickListener(this);
        findViewById(R.id.exit_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intentLocal;

        switch (v.getId()){

            case R.id.play_game_button_two:
                intentLocal = new Intent(MainActivity.this, Connect4Activity.class);
                startActivity(intentLocal);
                break;
            case R.id.about_button:
                intentLocal = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intentLocal);

                break;
            case R.id.exit_button:
                Toast.makeText(MainActivity.this,"Bye...",Toast.LENGTH_SHORT).show();
                finish();

                break;
            default:
                break;
        }
    }
}
