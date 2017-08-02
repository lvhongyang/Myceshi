package com.example.httpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText uname;
    private EditText upwd;
    private TextView mytext;

    private String username;
    private String userpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (EditText) findViewById(R.id.et_name);
        upwd = (EditText) findViewById(R.id.et_pass);
        mytext = (TextView) findViewById(R.id.mytext);

    }


    public void get(View view){

        IsNotNull();

        final String GetPath="http://123.206.70.44:8080/JavaWebTest/Upload_html?user="+username+"       &password="+userpwd;

        new Thread(){
            @Override
            public void run() {

                try {
                    //1.创建对象
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    //2.创建请求对象,把网址封装到请求对象,请求网络的方式
                    HttpGet httpGet = new HttpGet(GetPath);
                    //3.使用客户端对象发送请求,获取服务器的响应
                    //从response对象中拿到服务器给我们返回的信息
                    HttpResponse response = defaultHttpClient.execute(httpGet);
                    StatusLine statusLine = response.getStatusLine();
                    //获取状态码
                    int code = statusLine.getStatusCode();
                    if(code == 200){
                        //5.服务器通过流写给客户端的数据,把它成一个实体
                        HttpEntity entity = response.getEntity();
                        //获取输入流
                        InputStream is = entity.getContent();
                       final String stream = Tools.getTextFromStream(is);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mytext.setText(stream);
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }

    private void IsNotNull(){

        username = uname.getText().toString();
        userpwd = upwd.getText().toString();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(userpwd)){
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return ;
        }
    }
}
