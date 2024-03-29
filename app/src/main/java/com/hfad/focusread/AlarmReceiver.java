package com.hfad.focusread;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    /** method for the reminder dialog when the notification is activated**/
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, BookListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "focusRead")
                .setSmallIcon(R.drawable.fr_logo2).setContentTitle("Focus Read").setContentText("Alarm")
                .setAutoCancel(true).setDefaults(NotificationCompat.DEFAULT_ALL).setPriority(NotificationCompat
                        .PRIORITY_HIGH).setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());
    }
}
