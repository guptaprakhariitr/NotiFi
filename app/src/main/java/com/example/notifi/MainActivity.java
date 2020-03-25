package com.example.notifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
   static MyRecyclerViewAdapter adapter;
   static int k=0;
  static TextView t;
    private NotificationReciever1 nReceiver;
   static ArrayList<String> title_text = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       t=findViewById(R.id.count);
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.notif_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, title_text);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        nReceiver = new NotificationReciever1();
        IntentFilter filter = new IntentFilter();
        filter.addAction("/home/prakhar/AndroidStudioProjects/NotiFi/app/src/main/java/com/example/notifi/NotificationReceiver.java");
        registerReceiver(nReceiver,filter);
        t.setText("Total notiFs = 0");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }
    public static void add2(String s)
    { k=adapter.getItemCount()+1;
    s=k+" ."+s;
        title_text.add(s);
        t.setText("Total notiFs = "+ k);
        adapter.notifyDataSetChanged();
    }
    public static class NotificationReciever1 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String receivedNotificationCode = intent.getStringExtra("title");
            add2(receivedNotificationCode);
        }
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
public void intent_need(View v)
{
    Intent i=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
    startActivity(i);
}
}
