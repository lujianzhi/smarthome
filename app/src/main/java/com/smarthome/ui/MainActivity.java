package com.smarthome.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText id; // id
    private EditText name; // 设备名称
    private EditText value; // 值
    private EditText highValue; // 阀值上限
    private EditText lowValue; // 阀值下限
    private EditText state; // 开关状态 0:关 1：开
    private EditText image; // 图片
    private EditText eqComment;// 备注
    private EditText isRemind; // 是否提醒 0:不提醒 1:提醒
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initViews();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPostHttp(id.getText().toString(), name.getText().toString(),
                        value.getText().toString(), highValue.getText().toString(),
                        lowValue.getText().toString(), state.getText().toString(),
                        image.getText().toString(), eqComment.getText().toString(),
                        isRemind.getText().toString());

            }
        });


    }

    private void initViews() {
        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.name);
        value = (EditText) findViewById(R.id.value);
        highValue = (EditText) findViewById(R.id.highValue);
        lowValue = (EditText) findViewById(R.id.lowValue);
        state = (EditText) findViewById(R.id.state);
        image = (EditText) findViewById(R.id.image);
        eqComment = (EditText) findViewById(R.id.eqComment);
        isRemind = (EditText) findViewById(R.id.isRemind);
        submit = (Button) findViewById(R.id.submit);
    }

    private void requestPostHttp(String id,String name,String value,String highValue,String lowValue,
                                 String state,String image,String eqComment,String isRemind){
        try {
            String path = "";
            // 新建HttpPost对象
            HttpPost httpPost = new HttpPost(path);
            // Post参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("value", value));
            params.add(new BasicNameValuePair("highValue", highValue));
            params.add(new BasicNameValuePair("lowValue", lowValue));
            params.add(new BasicNameValuePair("state", state));
            params.add(new BasicNameValuePair("image", image));
            params.add(new BasicNameValuePair("eqComment", eqComment));
            params.add(new BasicNameValuePair("isRemind", isRemind));
            // 设置字符集
            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            // 设置参数实体
            httpPost.setEntity(entity);
            // 获取HttpClient对象
            HttpClient httpClient = new DefaultHttpClient();
            // 获取HttpResponse实例
            HttpResponse httpResp = httpClient.execute(httpPost);
            // 判断是够请求成功
            if (httpResp.getStatusLine().getStatusCode() == 200) {
                // 获取返回的数据
                String result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
                Log.i("lawson", "HttpPost方式请求成功，返回数据如下：");
                Log.i("lawson", result);
                Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_SHORT).show();
            } else {
                Log.i("lawson", "HttpPost方式请求失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
