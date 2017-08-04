package com.smile.notificationdemo.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smile.notificationdemo.model.Message;
import com.smile.notificationdemo.utils.NotificationUtil;
import com.smile.notificationdemo.utils.PrefUtil;

import java.util.List;

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
//                Intent intent1 = new Intent();
//                intent1.setAction(IntentAction.MESSAGING_REPLY);
//                intent1.putExtra(BundleKey.TEXT, input.getCharSequence(NotificationUtil.KEY_TEXT_REPLY));
//                context.sendBroadcast(intent1);


                String text = input.getString(NotificationUtil.KEY_TEXT_REPLY);

                List<Message> list = new Gson().fromJson(PrefUtil.getMessage(context)
                        , new TypeToken<List<Message>>() {}.getType());
                list.add(new Message(text, System.currentTimeMillis(), null));

                PrefUtil.setMessage(context, new Gson().toJson(list));

                NotificationUtil.messagingUpdate(context, list);
                break;
            }
            case NotificationUtil.BACK:
                //Do something
                Toast.makeText(context, NotificationUtil.BACK, Toast.LENGTH_SHORT).show();
                break;
            case NotificationUtil.NEXT:
                //Do something
                Toast.makeText(context, NotificationUtil.NEXT, Toast.LENGTH_SHORT).show();
                break;
            case NotificationUtil.PAUSE:
                //Do something
                Toast.makeText(context, NotificationUtil.PAUSE, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
