package com.example.mathpuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView textView1,textView2,textView3;
    Button buy,nothanks;
    ImageView share,email;
    SharedPreferences preferences;
    int lastlevel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.continue_txt);
        textView2=findViewById(R.id.puzzle_txt);
        textView3=findViewById(R.id.buy_txt);
        share=findViewById(R.id.share);
        email=findViewById(R.id.email);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        share.setOnClickListener(this);
        email.setOnClickListener(this);
        preferences=getSharedPreferences("mypre",MODE_PRIVATE);
        lastlevel=preferences.getInt("lastlevel",-1);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId()==share.getId())
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,textView1.getText());
            intent.putExtra(Intent.EXTRA_SUBJECT,"Puzzle");
            startActivity(Intent.createChooser(intent,"share"));

        }
        if (view.getId()==email.getId())

        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("messgae/rfc822");
            intent.putExtra(Intent.EXTRA_TEXT,textView1.getText());
            intent.putExtra(Intent.EXTRA_SUBJECT,"Puzzle Game");
            startActivity(Intent.createChooser(intent,"share"));
        }
        if (view.getId()==textView1.getId())
        {
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("level",lastlevel+1);
            startActivity(intent);

        }
        if (view.getId()==textView2.getId())
        {
            Intent intent = new Intent(MainActivity.this,LevelActivity.class);

            startActivity(intent);


        }
        if (view.getId()==textView3.getId())
        {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.buy_pro_layout);
            buy=dialog.findViewById(R.id.buy_button);
            nothanks=dialog.findViewById(R.id.nO_thx_button);
            dialog.show();
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            nothanks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });

        }

    }
}