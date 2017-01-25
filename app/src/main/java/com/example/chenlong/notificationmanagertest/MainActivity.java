package com.example.chenlong.notificationmanagertest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //通知栏点击跳转的api  PendingIntent
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                /**
                 * 各类的api设置
                 */
                Notification notification = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("这是一个通知")
//                        .setContentText("这是一个通知的文本")
                        .setWhen(System.currentTimeMillis())    //指定通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        //各个手机ROM厂商定义的默认的通知震动或呼吸灯提示规则
                        .setDefaults(NotificationCompat.DEFAULT_ALL)


                        //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 注意某些功能在小米手机等修改ROM的手机上 部分效果无法显示 可能需要特殊的设置
                        //隐式的取消点击后的通知栏
                         .setAutoCancel(true)
                        //单独指定一个来通知的铃声
                         .setSound(Uri.fromFile(new File("system/media/audio/ringtones/luna.ogg")))
                        //单独指定的震动效果  注意需要权限
                         .setVibrate(new long[]{0,1000,1000,1000})
                        //单独指定呼吸灯
                          .setLights(Color.GREEN,1000,1000)
                        //设置样式 通知栏下面接着一个图片的样式
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.big_image)))
                        //慎用这个属性 优先级高了之后 弹窗的效果容易引起用户极大的反感
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        //设置非常长的文本显示 虽然不推荐 但是还是有提供的api
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("这是一个非常非常长的文本,这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本这是一个非常非常长的文本"))
                        //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

                        .build();


                manager.notify(1, notification);
            }
        });


    }
}
