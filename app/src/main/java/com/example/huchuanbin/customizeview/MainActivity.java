package com.example.huchuanbin.customizeview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.view1)
    public void view1() {
        intent = new Intent(this, View1Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.view2)
    public void view2() {
        intent = new Intent(this, View2Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.view3)
    public void view3() {
        intent = new Intent(this, View3Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.view4)
    public void view4() {
        intent = new Intent(this, View4Activity.class);
        startActivity(intent);
    }

}
