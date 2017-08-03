package com.smile.notificationdemo.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.smile.notificationdemo.MainActivity;
import com.smile.notificationdemo.R;

/**
 * @author Smile Wei
 * @since 2017/8/2.
 */

public class NotificationUtil {
    private final static int ID_FOR_NORMAL = 1;
    private final static int ID_FOR_BIG_TEXT = 2;
    private final static int ID_FOR_INBOX = 3;
    private final static int ID_FOR_BIG_PICTURE = 4;
    private final static int ID_FOR_MESSAGING = 5;
    private final static int ID_FOR_MEDIA = 6;
    private final static int ID_FOR_CUSTOM_VIEW = 7;

    public static void normal(Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.cry)
                .setTicker(context.getString(R.string.app_name)).setWhen(System.currentTimeMillis())
                .setContentTitle("This is normal title")
                .setContentText("This is normal message")
                .setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent);

        if (isSound) {
            builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        } else {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }

        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }

        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? ID_FOR_NORMAL : (int) System.currentTimeMillis(), builder.build());
    }


    public static void bigText(Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText("This is big big big big big big big big big big big big " +
                "big big big big big big big big big big big big big big big big " +
                "big big big big big big big big message");
        style.setBigContentTitle("This is big title");
        style.setSummaryText(context.getString(R.string.app_name));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.cry)
                .setTicker(context.getString(R.string.app_name)).setWhen(System.currentTimeMillis())
                .setContentTitle("This is big title")
                .setContentText("This is big message")
                .setStyle(style)
                .setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent);

        if (isSound) {
            builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        } else {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }

        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }

        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? ID_FOR_BIG_TEXT : (int) System.currentTimeMillis(), builder.build());
    }

    public static void inbox(Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.addLine("This is first inbox message");
        style.addLine("This is second inbox message");
        style.addLine("This is third inbox message");
        style.addLine("This is fourth inbox message");
        style.addLine("This is fifth inbox message");
        style.addLine("This is sixth inbox message");
        style.addLine("This is seventh inbox message");
        style.setBigContentTitle("This is inbox title");
        style.setSummaryText(context.getString(R.string.app_name));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.cry)
                .setTicker(context.getString(R.string.app_name)).setWhen(System.currentTimeMillis())
                .setContentTitle("This is inbox title")
                .setContentText("This is inbox message")
                .setCategory(NotificationCompat.CATEGORY_EMAIL)
                .setStyle(style)
                .setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent);
        if (isSound) {
            builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        } else {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }
        builder.addAction(R.drawable.cry, "Reply", null);
        builder.addAction(R.drawable.cry, "Delete", null);
        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? ID_FOR_INBOX : (int) System.currentTimeMillis(), builder.build());
    }

    public static void bigPicture(Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.google));
        style.setBigContentTitle("This is big picture title");
        style.setSummaryText("This is big picture message");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.cry)
                .setTicker(context.getString(R.string.app_name)).setWhen(System.currentTimeMillis())
                .setContentTitle("This is big picture title")
                .setContentText("This is big picture message")
                .setStyle(style)
                .setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent);
        if (isSound) {
            builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        } else {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }
        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? ID_FOR_BIG_PICTURE : (int) System.currentTimeMillis(), builder.build());
    }

    public static void messaging(Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("Smile");
        style.addMessage("Hello, how are you?" +
                // "A notification is a message you can display to the user outside of your application's normal UI. " +
                //"When you tell the system to issue a notification, " +
                // "it first appears as an icon in the notification area. " +
                // "To see the details of the notification, the user opens the notification drawer. " +
                "Both the notification area and the notification drawer are system-controlled areas that the user can view at any time.", System.currentTimeMillis(), "Smile");
        style.addMessage("How old are you?", System.currentTimeMillis() - 1000000, "Wei");
        style.setConversationTitle("This is messaging title");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.cry)
                .setTicker(context.getString(R.string.app_name)).setWhen(System.currentTimeMillis())
                .setContentTitle("This is messaging title")
                .setContentText("This is messaging message")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setStyle(style)
                .setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent);
        if (isSound) {
            builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        } else {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }
        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? ID_FOR_MESSAGING : (int) System.currentTimeMillis(), builder.build());
    }

    public static void media(Context context, boolean isSound, boolean isShowLock, boolean isHeads, boolean isAutoCancel, boolean isOnly) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.google);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        style.setShowCancelButton(true);
        style.setCancelButtonIntent(pendingIntent);
        style.setShowActionsInCompactView(0, 1, 2);

        style.setMediaSession(new MediaSessionCompat(context, "MediaSession",
                new ComponentName(context, Intent.ACTION_MEDIA_BUTTON), null).getSessionToken());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.cry)
                .setTicker(context.getString(R.string.app_name)).setWhen(System.currentTimeMillis())
                .setContentTitle("This is media title")
                .setContentText("This is media message")
                .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
                .setStyle(style)
                .setOngoing(true)
                //.setAutoCancel(isAutoCancel)
                .setContentIntent(pendingIntent);

        builder.addAction(R.drawable.back, "", null);
        builder.addAction(R.drawable.pause, "", null);
        builder.addAction(R.drawable.next, "", null);


        if (isSound) {
            builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.message));
        } else {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }
        if (isShowLock) {
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        //builder.setPriority(isHeads ? NotificationCompat.PRIORITY_MAX : NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(isOnly ? ID_FOR_MEDIA : (int) System.currentTimeMillis(), builder.build());

    }

    public static void cancel(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

}
