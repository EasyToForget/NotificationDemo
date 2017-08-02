package com.smile.notificationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.smile.notificationdemo.utils.NotificationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_normal, R.id.btn_big_text, R.id.btn_inbox, R.id.btn_big_picture, R.id.btn_messaging, R.id.btn_media, R.id.btn_decorated, R.id.btn_decorated_media})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                NotificationUtil.normal(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case R.id.btn_big_text:
                NotificationUtil.bigText(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case R.id.btn_inbox:
                NotificationUtil.inbox(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case R.id.btn_big_picture:
                NotificationUtil.bigPicture(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case R.id.btn_messaging:
                NotificationUtil.messaging(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case R.id.btn_media:
                NotificationUtil.media(this, cbSound.isChecked(), cbLock.isChecked(), cbHead.isChecked(), cbAutoCancel.isChecked(), cbOnly.isChecked());
                break;
            case R.id.btn_decorated:
                break;
            case R.id.btn_decorated_media:
                break;
        }
    }
}
