package com.example.sainttropez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        registerForContextMenu(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        ImageView imageView = findViewById(R.id.imageView);
        switch (item.getItemId()){
            case R.id.top:
                imageView.setImageResource(R.drawable.toppage);
                return true;
            case R.id.lunch01:
                imageView.setImageResource(R.drawable.lunch01);
                return true;
            case R.id.lunch02:
                imageView.setImageResource(R.drawable.lunch02);
                return true;
            case R.id.dinner01:
                imageView.setImageResource(R.drawable.dinner01);
                return true;
            case R.id.dinner02:
                imageView.setImageResource(R.drawable.dinner02);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.sms:
                String number = "999-9999-9999";
                Uri uri = Uri.parse("sms:" + number);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.putExtra("sms_body", "Hello！！");
                startActivity(intent);
                break;
            case R.id.mail:
                String[] email = {"nobody@example.com"};
                String subject = "予約問い合わせ";
                String text = "以下の通り予約希望します。";
                Uri uri_mail = Uri.parse("mailto:");
                Intent intent_mail = new Intent(Intent.ACTION_SENDTO);
                intent_mail.setData(uri_mail);
                intent_mail.putExtra(Intent.EXTRA_EMAIL, email);
                intent_mail.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent_mail.putExtra(Intent.EXTRA_TEXT, text);
                PackageManager pm;
                pm = getApplicationContext().getPackageManager();
                if (intent_mail.resolveActivity(pm) != null){
                    startActivity(intent_mail);
                }
                break;
            case R.id.share:
                String shareText = "美味しいレストランを紹介します。";
                Intent intent_share = new Intent(Intent.ACTION_SEND);
                intent_share.setType("text/plain");
                intent_share.putExtra(Intent.EXTRA_TEXT, shareText);
                Intent chooser = new Intent();
                chooser = Intent.createChooser(intent_share, null);
                PackageManager pm_share;
                pm_share = getApplicationContext().getPackageManager();
                if(intent_share.resolveActivity(pm_share) != null){
                    startActivity(chooser);
                }
                break;
            case R.id.browse:
                String url = "http://www.yahoo.co.jp";
                Intent intent_browse = new Intent(Intent.ACTION_VIEW);
                intent_browse.setData(Uri.parse(url));
                PackageManager pm_browse;
                pm_browse = getApplicationContext().getPackageManager();
                if(intent_browse.resolveActivity(pm_browse) != null){
                    startActivity(intent_browse);
                }
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
