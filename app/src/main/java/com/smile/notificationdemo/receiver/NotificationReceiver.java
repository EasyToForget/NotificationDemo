package com.smile.notificationdemo.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import com.smile.notificationdemo.BundleKey;
import com.smile.notificationdemo.IntentAction;
import com.smile.notificationdemo.utils.NotificationUtil;

/**
 * @author Smile Wei
 * @since 2017/8/4.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null)
            return;
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        switch (intent.getAction()){
            case NotificationUtil.REPLY:{
                //get the content from the input
                Bundle input = RemoteInput.getResultsFromIntent(intent);
                if (input == null)
                    return;
                //Do something
                Toast.makeText(context, input.getCharSequence(NotificationUtil.KEY_TEXT_REPLY), Toast.LENGTH_SHORT).show();
                manager.cancel(NotificationUtil.ID_FOR_NORMAL_ACTION);
                break;
            }
            case NotificationUtil.MAKE_AS_READ:
                //Do something
                Toast.makeText(context, NotificationUtil.MAKE_AS_READ, Toast.LENGTH_SHORT).show();
                manager.cancel(NotificationUtil.ID_FOR_NORMAL_ACTION);
                break;
            case NotificationUtil.DELETE:
                //Do something
                Toast.makeText(context, NotificationUtil.DELETE, Toast.LENGTH_SHORT).show();
                manager.cancel(NotificationUtil.ID_FOR_NORMAL_ACTION);
                break;
            case NotificationUtil.REPLY_MESSAGING:{
                //get the content from the input
                Bundle input = RemoteInput.getResultsFromIntent(intent);
                if (input == null)
                    return;
                //Do something
                Intent intent1 = new Intent();
                intent1.setAction(IntentAction.MESSAGING_REPLY);
                intent1.putExtra(BundleKey.TEXT, input.getCharSequence(NotificationUtil.KEY_TEXT_REPLY));
                context.sendBroadcast(intent1);
                break;
            }
        }
    }
}
