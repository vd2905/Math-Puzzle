package com.example.mathpuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Adapter.LevelAdapter;

public class LevelActivity extends AppCompatActivity {
    GridView gridView;
    Button button ,pbutton;
    TextView textView;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        gridView=findViewById(R.id.Level_grid_view);
        textView=findViewById(R.id.select_puzzle);
        button=findViewById(R.id.next_button);
        pbutton=findViewById(R.id.previus_button);
        preferences=getSharedPreferences("mypre",MODE_PRIVATE);

        LevelAdapter puzzle_adapter = new LevelAdapter(LevelActivity.this, Config.lock,preferences);
        gridView.setAdapter(puzzle_adapter );
        Typeface typeface = Typeface.createFromAsset(LevelActivity.this.getAssets(),Config.font);
        textView.setTypeface(typeface);
        if (Config.cnt == 1) {
            pbutton.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
            pbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Config.cnt--;
                    Intent intent = new Intent(LevelActivity.this, LevelActivity.class);

                    startActivity(intent);
                    finish();
                }
            });


        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Config.cnt++;
                Intent intent = new Intent(LevelActivity.this, LevelActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }

}