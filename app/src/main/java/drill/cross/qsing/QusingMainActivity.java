package drill.cross.qsing;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class QusingMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qusing_main);

        String url="http://localhost:60100/Test/NewFile.jsp";

        URL object= null;
        try {
            object = new URL(url);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            con.setDoOutput(true);

            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json");

            con.setRequestProperty("Accept", "application/json");

            con.setRequestMethod("POST");

            JSONObject cred = new JSONObject();

            JSONObject auth=new JSONObject();

            JSONObject parent=new JSONObject();

            cred.put("username","adm");

            cred.put("password", "pwd");

            auth.put("tenantName", "adm");

            auth.put("passwordCredentials", cred.toString());

            parent.put("auth", auth.toString());

            OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());

            wr.write(parent.toString());

            wr.flush();

//display what returns the POST request

            StringBuilder sb = new StringBuilder();

            int HttpResult =con.getResponseCode();
            AvLog.i("httpResult = "+HttpResult);
            if(HttpResult ==HttpURLConnection.HTTP_OK){

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                    AvLog.i("line \n = "+line+"\n");
                }
                br.close();
                AvLog.i("sb.toString() = "+sb.toString());
            }else{
                AvLog.i("con.getResponseMessage = "+con.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            AvLog.i("MalformedURLException");
        } catch (ProtocolException e) {
            e.printStackTrace();
            AvLog.i("ProtocolException");
        } catch (IOException e) {
            e.printStackTrace();
            AvLog.i("IOException");
        } catch (JSONException e) {
            e.printStackTrace();
            AvLog.i("JSONException");
        }



        /*String url =  "http://localhost:60100/Test/NewFile.jsp";
        // HttpURLConnection 객체 생성.
        HttpURLConnection conn = null;
        try {
            connectUrl = new URL(url);


// URL 연결 (웹페이지 URL 연결.)
        conn = (HttpURLConnection)connectUrl.openConnection();

// TimeOut 시간 (서버 접속시 연결 시간)

// TimeOut 시간 (Read시 연결 시간)

// 요청 방식 선택 (GET, POST)
        conn.setRequestMethod("POST");

// Request Header값 셋팅 setRequestProperty(String key, String value)
        conn.setRequestProperty("NAME", "name");
        conn.setRequestProperty("MDN", "mdn");
        conn.setRequestProperty("APPID", "appid");

// 서버 Response Data를 xml 형식의 타입으로 요청.
        conn.setRequestProperty("Accept", "application/xml");

// 서버 Response Data를 JSON 형식의 타입으로 요청.
        conn.setRequestProperty("Accept", "application/json");

// 타입설정(text/html) 형식으로 전송 (Request Body 전달시 text/html로 서버에 전달.)
        conn.setRequestProperty("Content-Type", "text/html");

// 타입설정(text/html) 형식으로 전송 (Request Body 전달시 application/xml로 서버에 전달.)
        conn.setRequestProperty("Content-Type", "application/xml");

// 타입설정(application/json) 형식으로 전송 (Request Body 전달시 application/json로 서버에 전달.)
        conn.setRequestProperty("Content-Type", "application/json");

// 컨트롤 캐쉬 설정
        conn.setRequestProperty("Cache-Control","no-cache");

// 타입길이 설정(Request Body 전달시 Data Type의 길이를 정함.)
        conn.setRequestProperty("Content-Length", "length");

// User-Agent 값 설정
        conn.setRequestProperty("User-Agent", "test");

// OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
        conn.setDoOutput(true);

// InputStream으로 서버로 부터 응답을 받겠다는 옵션.
        conn.setDoInput(true);

// Request Body에 Data를 담기위해 OutputStream 객체를 생성.
        OutputStream os = conn.getOutputStream();

// Request Body에 Data 셋팅.

// Request Body에 Data 입력.
        os.flush();

// OutputStream 종료.
        os.close();

// 실제 서버로 Request 요청 하는 부분. (응답 코드를 받는다. 200 성공, 나머지 에러)
        int responseCode = conn.getResponseCode();

// 접속해지
        conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qusing_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
