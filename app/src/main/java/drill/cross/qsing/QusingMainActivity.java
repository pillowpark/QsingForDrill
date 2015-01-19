package drill.cross.qsing;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class QusingMainActivity extends Activity {
    Button btnOK;
    EditText id, pw;
    String  myResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qusing_main);

        btnOK = (Button) findViewById(R.id.btn_ok);
        id = (EditText) findViewById(R.id.ed_id);
        pw = (EditText) findViewById(R.id.ed_pw);




        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> Paramname = new ArrayList<String>();
                Paramname.add("id");
                Paramname.add("pw");

                ArrayList<String> Paramvalue = new ArrayList<String>();
                Paramvalue.add(id.getText().toString());
                Paramvalue.add(pw.getText().toString());
                AvLog.i("id = "+id.getText().toString());
                AvLog.i("pw = "+pw.getText().toString());
                new AsyncHttpTask(QusingMainActivity.this, getString(R.string.server_path)+"WebServer/NewFile.jsp", mHandler, Paramname, Paramvalue, null, 1,0);

            }
        });
    }

    protected Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            AvLog.i("msg.what state = "+msg.what);
            //Stop progressbar

            if (msg.what == -1) {
                Toast.makeText(QusingMainActivity.this, "통신에서 에러 --;;", Toast.LENGTH_SHORT).show();
            }

            if (msg.what == 1) {
                myResult = msg.obj.toString();
                if (myResult.matches("")) {
                    // Error Login
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(QusingMainActivity.this);
                    builder1.setMessage(getString(R.string.error))
                            .setPositiveButton(getString(R.string.yes), null)
                            .setTitle(getString(R.string.error));
                    builder1.show();
                } else {

                    AvLog.i("결과 = "+myResult);

                }

            }

        }
    };

    private void ConnectionError() {

    }
}