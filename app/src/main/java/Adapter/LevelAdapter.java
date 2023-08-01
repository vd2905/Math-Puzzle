package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathpuzzle.Config;
import com.example.mathpuzzle.LevelActivity;
import com.example.mathpuzzle.R;
import com.example.mathpuzzle.SecondActivity;

public class LevelAdapter extends BaseAdapter {
    Activity context;
    int lock;
    ImageView imageView;
    TextView textView;
    SharedPreferences preferences;
    public LevelAdapter(LevelActivity levelActivity, int lock, SharedPreferences preferences) {
        this.context=levelActivity;
        this.lock=lock;
        this.preferences= context.getSharedPreferences("mypre", Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.activity_level_item,viewGroup,false);

        imageView=view.findViewById(R.id.item_txt_view);
        imageView.setImageResource(lock);
        textView=view.findViewById(R.id.item_txt_view1);

        if (Config.cnt==0) {
            int lastlevel = preferences.getInt("lastlevel",-1);
            String status = preferences.getString("levelstatus"+i,"pending");
            if (status.equals("win")) {
                imageView.setImageResource(R.drawable.tick);
                textView.setText("" + (i + 1));
                textView.setVisibility(View.VISIBLE);
            }else if (status.equals("skip") || i == lastlevel + 1) {
                imageView.setImageResource(0);
                textView.setText("" + (i + 1));
                textView.setVisibility(View.VISIBLE);
            }
            if (status.equals("win") || status.equals("skip") || i == lastlevel + 1) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SecondActivity.class);
                        intent.putExtra("level", i);
                        context.startActivity(intent);
                        context.finish();
                    }
                });
            }
        }

        if (Config.cnt==1) {
            int lastlevel = preferences.getInt("lastlevel",-1);
            String status = preferences.getString("levelstatus"+(i+20),"pending");
            if (status.equals("win")) {
                imageView.setImageResource(R.drawable.tick);
                textView.setText("" + (i + 21));
                textView.setVisibility(View.VISIBLE);
            }else if (status.equals("skip") || i+20 == lastlevel + 1) {
                imageView.setImageResource(0);
                textView.setText("" + (i + 21));
                textView.setVisibility(View.VISIBLE);
            }
            if (status.equals("win") || status.equals("skip") || i+20 == lastlevel + 1) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SecondActivity.class);
                        intent.putExtra("level", i+20);
                        context.startActivity(intent);
                        context.finish();
                    }
                });
            }
        }
        return view;
    }
}