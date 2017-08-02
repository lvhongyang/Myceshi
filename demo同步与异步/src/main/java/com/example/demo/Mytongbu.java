package com.example.demo;
import android.os.SystemClock;
import android.preference.PreferenceManager;

/**
 * date: 2017/7/24.
 * author: 吕宏洋（）
 * function:
 */
public class Mytongbu {
    int result;
    int add = 2/result;

//    public int calc(final int total, final int Pcount) {
//        //因为计算复杂为耗时操作
//        new Thread() {
//            @Override
//            public void run() {
//                SystemClock.sleep(2000);
//
//                result = total / Pcount;
//            }
//        }.start();
//        return result;
//    }

    public void calcAsync(final int total, final int Pcount, final OnResultListener Object){
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                result = total / Pcount;
                Object.OnSuccess(result);
            }
        }.start();

    }

    public interface OnResultListener{
        void OnSuccess(int result);
    }

}
