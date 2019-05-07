package com.example.task_solo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.task_solo.App;
import com.example.task_solo.R;
import com.example.task_solo.activity.MainActivity;
import com.example.task_solo.adapter.BaseAdapter;
import com.example.task_solo.response.BaseResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsService extends Service {

    ServiceRun serviceRun;
    ExecutorService executorService;
    private int interval = 30000;
    private Handler mHandler;
    String newId;

    @Override
    public void onCreate() {
        super.onCreate();
        newId = "";
        executorService = Executors.newFixedThreadPool(1);
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        serviceRun = new ServiceRun(startId);
        executorService.execute(serviceRun);
        mHandler.postDelayed(serviceRun, interval);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class ServiceRun implements Runnable {
        int startId;

        public ServiceRun(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            App.instance().getApiService().postFeed("9c316183-b6ac-4839-943d-b1f41b22591c", "json", 1, 5).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.body() != null && response.body().getResponse().getStatus().equals("ok")) {
                        newId = response.body().getResponse().getResults().get(1).getId();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    newId = null;
                }
            });

            if (!newId.equals(App.instance().getSharedPref().getString("firstId", null)) && !newId.equals("")) {
                sendNotification();

                App.instance().changeFirstId(newId);
                stopSelf();
            }
            mHandler.postDelayed(serviceRun, interval);
        }
    }

    public void sendNotification() {

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), "1");
        builder.setSmallIcon(R.drawable.ic_launcher_background).
                setContentTitle("News").
                setContentText("Refresh Your New List").
                setContentIntent(resultPendingIntent).
                setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }
}
