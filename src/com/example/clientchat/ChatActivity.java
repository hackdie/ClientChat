package com.example.clientchat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clientchat.ChatTask.CallBack;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends Activity implements CallBack {

	Button buttonEnviar;
	TextView txt_mjes;
	EditText txt_send_mjes;
	
	AsyncTask<String, Void, Void> hilo;

	String llave_publica, llave_privada, Room;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);

		txt_mjes = (TextView) findViewById(R.id.txt_chat);
		txt_send_mjes = (EditText) findViewById(R.id.txtSendMje);
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);

		Intent notif = getIntent();
		llave_publica = notif.getStringExtra("llave_publica");
		llave_privada = notif.getStringExtra("llave_privada");
		Room = notif.getStringExtra("Room");
		
		hilo = new ChatTask(llave_publica, llave_privada, Room, this).execute("");

		buttonEnviar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (txt_send_mjes.getText().toString() != "") {
					String mje = txt_send_mjes.getText().toString();
					
					ChatTask.flag = true;
					ChatTask.json_mje = enviar_mjes(mje);
					
					txt_send_mjes.setText("");

				} else {
					Toast.makeText(ChatActivity.this, "Ingresa un mensaje",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	public void onResume() {
	    super.onResume(); 
	    
	    if(!hilo.getStatus().equals(AsyncTask.Status.RUNNING))
	    	hilo = new ChatTask(llave_publica, llave_privada, Room, this).execute("");
	    
	}
	
	
	@Override
	protected void onStop() {
	    super.onStop();  // Always call the superclass method first
	    hilo.cancel(true);
	}
	

	public String enviar_mjes(String mjs) {
		
		JSONObject mje = new JSONObject();
		try {
			JSONObject informacionMsj = new JSONObject();
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fecha = df3.format(c.getTime());
			
			informacionMsj.put("horaFecha", fecha);
			informacionMsj.put("mensaje", mjs );
			informacionMsj.put("remitente", llave_publica);
			
			
			mje.put("informacionMsj", informacionMsj);
			mje.put("accion", "enviar");
			mje.put("identificador", Room);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return mje.toString();
	}	int tot_mjes = 0;
	boolean flag_load = true;

	@Override
	public void doInBackground(final String Json_server) {

		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				try {
					JSONObject _json = new JSONObject(Json_server);
					Log.e("asd",_json.toString());
					
					String status = _json.getString("status");
					if (status.compareTo("ok") == 0) {
						JSONArray mjes = _json.getJSONArray("recibidoMsj");

						if (mjes.length() > tot_mjes) {
							tot_mjes = mjes.length();
							
							if (flag_load) {
								flag_load = false;

								for (int x = 0; x < tot_mjes; x++) {
									JSONObject mje = mjes.getJSONObject(x);
									
									if(mje.getString("remitente").compareTo(llave_publica) == 0)
										txt_mjes.append("->");
									txt_mjes.append(mje.getString("mensaje")+ "\n");
								}

							} else {
								JSONObject mje = mjes.getJSONObject(mjes
										.length() - 1);
								if(mje.getString("remitente").compareTo(llave_publica) == 0)
									txt_mjes.append("->");
								txt_mjes.append(mje.getString("mensaje") + "\n");
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception ex) {
					Log.e("error", ex.toString());
					ex.printStackTrace();
				}
			}
		});
	}
}