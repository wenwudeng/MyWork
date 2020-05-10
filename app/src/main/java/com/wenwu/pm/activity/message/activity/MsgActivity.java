package com.wenwu.pm.activity.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.adapter.MsgAdapter;
import com.wenwu.pm.activity.message.bean.Msg;
import com.wenwu.pm.utils.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * @author:wenwudeng
 * @date:20:35 2020/2/18
 * 私信聊天界面
 */
public class MsgActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter msgAdapter;

    private Toolbar toolbar;

    private Socket socket;

    private String chatUserPhoto;
    private String userPhoto;
    private String username;
    private boolean isFirst = true;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chat_record_ui);

        /*socket*/
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
      //  connect();
        initView();
    }
    public void initView() {

        //获取从个人主页的用户名、头像
        Intent intent = getIntent();
        username = intent.getStringExtra("userName");
        chatUserPhoto = intent.getStringExtra("photo");
        userPhoto = JsonUtil.loginJson.getData().getPhoto();

        initMsg();

        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);

        toolbar = findViewById(R.id.msg_chat_object);
        if (username != null) {
            toolbar.setTitle(username);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        msgRecyclerView = findViewById(R.id.recycler_view_chatRecord);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        msgAdapter = new MsgAdapter(msgList,this);
        msgRecyclerView.setAdapter(msgAdapter);

        connect();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(sendMsg(), Msg.TYPE_SEND,userPhoto);
                    msgList.add(msg);
                    //当有新消息时,刷新RecyclerView中的显示
                    msgAdapter.notifyItemChanged(msgList.size() - 1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });
    }

    public void connect() {
        try {
            socket = new Socket("192.168.1.109", 9999);
            /*连接成功后主动发送信息*/
            if (isFirst) {//判断是不是第一次连接
                PrintWriter out=new PrintWriter(socket.getOutputStream());
                out.println("username");
                isFirst = false;
            }
        } catch (IOException e) {
            System.out.println("连接Socket服务端失败");
            e.printStackTrace();
        }
    }


    public String sendMsg() {
        try {
            //主线程用来发送信息
            ClientThread thread=new ClientThread(socket);
            thread.start();
            PrintWriter out=new PrintWriter(socket.getOutputStream());

                out.println(JsonUtil.loginJson.getData().getUserName()+","+username+","+content);
                System.out.println("客户端发送："+content);
                out.flush();

        }catch(Exception e){
            System.out.println("服务器连接异常====");
            e.printStackTrace();
        }
        return content;
    }

     class ClientThread extends Thread {
        private Socket socket;

        public ClientThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader);
                try {
                    // 信息的格式：发送人,收发人,信息体
                    while (true) {
                        String msg=br.readLine();
                        String[] str = msg.split(",");
                        System.out.println(str[0] + "对您说：" + str[2]);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Msg msg1 = new Msg(str[2], Msg.TYPE_RECEIVED,chatUserPhoto);
                                msgList.add(msg1);
                                //当有新消息时,刷新RecyclerView中的显示
                                msgAdapter.notifyItemChanged(msgList.size() - 1);
                                //将RecyclerView定位到最后一行
                                msgRecyclerView.scrollToPosition(msgList.size() - 1);
                            }
                        });


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }

/*    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private void initMsg () {
            Msg msg1 = new Msg("你好吗？", Msg.TYPE_RECEIVED, chatUserPhoto);
            msgList.add(msg1);

            Msg msg2 = new Msg("我一点也不好", Msg.TYPE_SEND, userPhoto);
            msgList.add(msg2);

            Msg msg3 = new Msg("为什么？", Msg.TYPE_RECEIVED,chatUserPhoto);
            msgList.add(msg3);

            Msg msg4 = new Msg("因为毕业设计", Msg.TYPE_SEND, userPhoto);
            msgList.add(msg4);

            Msg msg5 = new Msg("......", Msg.TYPE_RECEIVED, chatUserPhoto);
            msgList.add(msg5);
        }

}
