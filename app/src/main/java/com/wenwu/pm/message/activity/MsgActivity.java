package com.wenwu.pm.message.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.wenwu.pm.R;
import com.wenwu.pm.message.adapter.MsgAdapter;
import com.wenwu.pm.message.bean.Msg;

import java.util.ArrayList;
import java.util.List;


/**
 * @author:wenwudeng
 * @date:20:35 2020/2/18
 * 信息功能的聊天界面
 */
public class MsgActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chat_record_ui);

        initMsg();

        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);

        msgRecyclerView = findViewById(R.id.recycler_view_chatRecord);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        msgAdapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(msgAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND, R.drawable.img);
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

        private void initMsg () {
            Msg msg1 = new Msg("Hello guy!", Msg.TYPE_RECEIVED, R.drawable.timg);
            msgList.add(msg1);

            Msg msg2 = new Msg("Hello,who is that?", Msg.TYPE_SEND, R.drawable.img);
            msgList.add(msg2);

        /*Msg msg3 = new Msg("This is Tom.Nice talking to you", Msg.TYPE_RECEIVED);
        msgList.add(msg3);*/
        }

}
