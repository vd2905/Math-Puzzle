package com.example.mathpuzzle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView [] button = new TextView[10];
    TextView textView,textView1,submit;
    ImageView imageView,skip,delete;
    String str1,str,temp;
    ArrayList <String> imgArr = new ArrayList<>();
    List<String> arraylist = new ArrayList<>();

    int level,lastlevel;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int ansarr [] = {10,25,6,14,128,7,50,1025,100,3,212,3011,14,16,1,2,44,45,625,1,13,47,50,34,6,41};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        for(int i=0;i< button.length;i++)
        {
            int id=getResources().getIdentifier("btn"+i,"id",getPackageName());
            button[i]=findViewById(id);
            button[i].setOnClickListener(this);
        }
        textView1=findViewById(R.id.level_show_txt);

        textView=findViewById(R.id.answer_txt);
        delete=findViewById(R.id.delete_button);
        delete.setOnClickListener(this);
        submit=findViewById(R.id.submit_button);
        imageView=findViewById(R.id.puzzle_imageview);
        submit.setOnClickListener(this);
        preferences=getSharedPreferences("mypre",MODE_PRIVATE);
        editor=preferences.edit();
        skip=findViewById(R.id.skip_button);
        skip.setOnClickListener(this);
        lastlevel= preferences.getInt("lastlevel",0);

        if (getIntent().getExtras() != null) {
            level = getIntent().getIntExtra("level", 0);
        }
        textView1.setText("Puzzle "+(level+1));
        String[] images = new String[0];
        try {
            images = getAssets().list("images/");
            imgArr = new ArrayList<String>(Arrays.asList(images));
            imgArr.remove("clock_font.png");
            imgArr.remove("android-logo-mask.png");
            imgArr.remove("android-logo-shine.png");
            imgArr.remove("clock_font.png");
            imgArr.remove("progress_font.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Collections.sort(imgArr);
        InputStream inputStream = null;
        {
            try {
                inputStream =getAssets().open("images/"+imgArr.get(level));

                Drawable drawable = Drawable.createFromStream(inputStream,null);
                System.out.println("input strram="+drawable);
                imageView.setImageDrawable(drawable);
                inputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }



        }


    }

    @Override
    public void onClick(View view) {
        for (int i=0;i< button.length;i++)
        {
            if(button[i].getId()==view.getId()) {
                str1 = String.valueOf(textView.getText());
                textView.setText(str1+i);
            }

        }

        if (view.getId()==delete.getId())
        {
            str1=String.valueOf(textView.getText());
            int n=str1.length()-1;
            if (n>=0)
            {
                String s = str1.substring(0,n);
                textView.setText(""+s);
            }

        }
        if (view.getId()==submit.getId()) {
            str = String.valueOf(textView.getText());
            int n = Integer.parseInt(str);
            if (lastlevel > level) {
                if (ansarr[level] == n) {
                    editor.putInt("lastlevel", lastlevel);
                    editor.putString("levelstatus" + level, "win");
                    editor.commit();
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra("level", level);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("wrong...");
                    builder.show();
                }
            }

            else {
                if (ansarr[level] == n) {
                    editor.putInt("lastlevel", level);
                    editor.putString("levelstatus" + level, "win");
                    editor.commit();
                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra("level", level);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("wrong...");
                    builder.show();
                }
            }
        }
        if (view.getId()==skip.getId())
        {
            editor.putInt("lastlevel",level);
            editor.putString("levelstatus"+level,"skip");
            System.out.println(level);
            editor.commit();
            Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
            intent.putExtra("level",(level+1));
            startActivity(intent);
            finish();
        }
    }
}