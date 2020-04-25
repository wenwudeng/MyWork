package com.wenwu.pm.activity.message.activity;

import android.content.Intent;
import android.os.Bundle;
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

    private String chatUserPhoto;
    private String userPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chat_record_ui);
        initView();

    }

    public void initView() {
        //获取从个人主页的用户名、头像
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        chatUserPhoto = intent.getStringExtra("photo");
        userPhoto = JsonUtil.loginJson.getData().getPhoto();

        initMsg();


        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);

        toolbar = findViewById(R.id.msg_chat_object);
        if (name != null) {
            toolbar.setTitle(name);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        msgRecyclerView = findViewById(R.id.recycler_view_chatRecord);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        msgAdapter = new MsgAdapter(msgList,this);
        msgRecyclerView.setAdapter(msgAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                 /*   Msg msg = new Msg(sendMsg(content), Msg.TYPE_SEND, R.drawable.img);
                    msgList.add(msg);*/
                    //当有新消息时,刷新RecyclerView中的显示
                    msgAdapter.notifyItemChanged(msgList.size() - 1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);

                    inputText.setText("");//清空输入框中的内容
                }
            }
        });
    }


    public String  sendMsg(String out) {

        new Thread(new Runnable() {
            String info = null;
            @Override
            public void run() {
                try {
                    //1、创建socket并指定端口
                    Socket socket = new Socket("192.168.0.104", 8888);
                    //2、向服务端发请求
                    PrintWriter os = new PrintWriter(socket.getOutputStream());
                    //  Scanner scanner = new Scanner(System.in);
                    //   System.out.println("你好，服务端！");
                    os.write(out);
                    os.flush();//输出缓冲区
                    socket.shutdownOutput();
                    //3、等待服务端的回应获取输入流，读取客户端信息(字节流转字符流)，并保存至缓冲区
                    InputStreamReader is = new InputStreamReader(socket.getInputStream());
                    BufferedReader br = new BufferedReader(is);

                    while ((info = br.readLine()) != null) {
                        System.out.println("服务端的回信：" + info);
                    }
                   // socket.shutdownInput();//关闭输入流
                    //4.关闭资源
                    os.close();
                    is.close();
                    br.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return out;
    }

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
