package com.smile.notificationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.smile.notificationdemo.utils.NotificationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NotificationAdapter.OnItemClickListener {

    @BindView(R.id.cb_sound)
    CheckBox cbSound;
    @BindView(R.id.cb_lock)
    CheckBox cbLock;
    @BindView(R.id.cb_head)
    CheckBox cbHead;
    @BindView(R.id.cb_auto_cancel)
    CheckBox cbAutoCancel;
    @BindView(R.id.cb_only)
    CheckBox cbOnly;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<String> list = new ArrayList<>();

    private List<NotificationCompat.MessagingStyle.Message> messageList = new ArrayList<>();

    private MessageReceiver receiver= new MessageReceiver();
    private IntentFilter filter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        list.add("Normal");
        list.add("Normal with action");
        list.add("Big Text");
        list.add("Inbox");
        list.add("Big Picture");
        list.add("Messaging");
        list.add("Media");
        list.add("Custom View");
        list.add("Cancel");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NotificationAdapter adapter = new NotificationAdapter(getApplicationContext(), list);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        messageList.add(new NotificationCompat.MessagingStyle.Message("What do you plan to do on the weekend?", System.currentTimeMillis(), "Smile"));
        messageList.add(new NotificationCompat.MessagingStyle.Message("Sleep.", System.currentTimeMillis(), "Tracy"));
        messageList.add(new NotificationCompat.MessagingStyle.Message("Go to Beijing..", System.currentTimeMillis(), "SB"));
        messageList.add(new NotificationCompat.MessagingStyle.Message("Play football", System.currentTimeMillis(), "Naocan"));
        messageList.add(new NotificationCompat.MessagingStyle.Message("Coding...", System.currentTimeMillis(), "Developer"));

        filter.addAction(IntentAction.MESSAGING_REPLY);

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                NotificationUtil.normal(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case 1:
                NotificationUtil.normalWithAction(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked());
                break;
            case 2:
                NotificationUtil.bigText(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case 3:
                NotificationUtil.group(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case 4:
                NotificationUtil.bigPicture(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case 5:
                NotificationUtil.messaging(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), messageList);
                break;
            case 6:
                NotificationUtil.media(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case 7:
                break;
            default:
                NotificationUtil.cancel(this);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, SecondActivity.class));
        return super.onOptionsItemSelected(item);
    }

    class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null||intent.getAction() == null)
                return;
            switch (intent.getAction()){
                case IntentAction.MESSAGING_REPLY:
                    messageList.add(new NotificationCompat.MessagingStyle.Message(intent.getStringExtra(BundleKey.TEXT), System.currentTimeMillis(), null));
                    NotificationUtil.messaging(MainActivity.this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), messageList);
                    break;
            }
        }
    }
}
