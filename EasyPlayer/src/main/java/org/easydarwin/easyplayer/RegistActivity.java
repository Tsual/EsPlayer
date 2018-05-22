package org.easydarwin.easyplayer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.esaydarwin.rtsp.player.R;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText et3;

    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        et1 = findViewById(R.id.rg_et_lid);
        et2 = findViewById(R.id.rg_et_pwd);
        et3 = findViewById(R.id.rg_et_name);


        bt1 = findViewById(R.id.rg_bt_rg);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lid = et1.getText().toString();
                String pwd = et2.getText().toString();
                String name = et3.getText().toString();

                Map m = new HashMap();
                m.put("lid", lid);
                m.put("pwd", pwd);
                m.put("name", name);

                PostUtil.Regist(m, registHandler, RegistActivity.this);
            }
        });

    }

    Handler registHandler = new Handler() {
        String alert = "";

        @Override
        public String toString() {
            return alert;
        }

        @Override
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            if (Boolean.FALSE.toString().equals(data.getString("result"))) {
                alert = data.getString("cause");
            }

            String jsonString = data.getString("value");
            try {
                JSONObject json = new JSONObject(jsonString);
                if ("Error".equals(json.getString("excuteResult"))) {
                    alert = json.getString("message");
                } else {
                    LoginUser user = LoginUser.getCurrentUser();
                    user.isLogin = true;
                    user.LoginToken = json.getString("userLoginToken");
                    JSONObject ext = (JSONObject) json.get("extResult");
                    user.Name = ext.getString("Name");
                    user.LID = ext.getString("LID");
                    user.UID = Integer.parseInt(ext.getString("UID"));

                    Intent in = new Intent();
                    in.setClassName(getApplicationContext(), "org.easydarwin.easyplayer.PlaylistActivity");
                    startActivity(in);
                }
            } catch (Exception ex) {
                alert = "返回报文出错:" + ex.getMessage();
            }
        }
    };
}
