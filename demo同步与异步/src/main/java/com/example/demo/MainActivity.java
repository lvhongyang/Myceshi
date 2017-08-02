package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Mytongbu.OnResultListener {

    private Mytongbu mytongbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytongbu = new Mytongbu();
    }


    public void clickListener(View view) {
        getResultAsync();
    }
//    public void getResult() {
//        int cals = mytongbu.calc(500,5);
//
//    }
    public void getResultAsync(){
        mytongbu.calcAsync(500,50,this);
    }

    @Override
    public void OnSuccess(int result) {
        System.out.println("每人获得了"+result+"根黄瓜。");
        int add = 2/result;
        System.out.println(add);
    }
}
