package com.abpl.parentunetest.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.abpl.parentunetest.MainActivity;
import com.abpl.parentunetest.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GenerateNotification extends AsyncTask<String, Void, Bitmap> {

        private Context mContext;
        private String notificationId,title, message, imageUrl;

        public GenerateNotification(Context context, String notificationId, String title, String message, String imageUrl) {
            super();
            this.mContext = context;
            this.title = title;
            this.message = message;
            this.imageUrl = imageUrl;
            this.notificationId = notificationId;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            InputStream in;
            try {
                URL url = new URL(this.imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            handleNotification(result);

        }

    private void handleNotification(Bitmap result) {
        Intent navigationIntent = new Intent(mContext, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addNextIntentWithParentStack(navigationIntent);



        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "parentunetest_" +
                    "channel_" + System.currentTimeMillis(); // The id of the channel.
            CharSequence name = "p5m"; // The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationManager.createNotificationChannel(mChannel);

            Notification notification = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result).setSummaryText(message))
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),
                            R.mipmap.ic_launcher))

                    .setContentIntent(resultPendingIntent)
                    .setChannelId(CHANNEL_ID)
                    .setContentText(message)
                    .build();
            notificationManager.notify((int) (System.currentTimeMillis() - 10000000), notification);

        } else {

            Notification.BigPictureStyle bigTextStyle = new Notification.BigPictureStyle();
            bigTextStyle.bigPicture(result);
            bigTextStyle.setSummaryText(message);

            Notification notification = new Notification.Builder(mContext)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setStyle(bigTextStyle)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),
                            R.mipmap.ic_launcher))
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(resultPendingIntent)
                    .setContentText(message)
                    .build();
            notificationManager.notify(Integer.parseInt(notificationId), notification);

        }
    }

    }