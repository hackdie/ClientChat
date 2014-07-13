package com.example.clientchat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ChatTask extends AsyncTask<String, Void, Void> {

	CallBack _mycallback;
	String identificador,llavePrivada,participante;
	
	
	public ChatTask(String _llave_publica , String _llave_privada, String _Room,CallBack mycallback) {
		identificador = _llave_publica;
		llavePrivada = _llave_privada;
		participante = _Room;
		_mycallback = mycallback;
	}

	public String getMjs() {
		JSONObject object = new JSONObject();
		try {
			object.put("accion", "recibir");
			object.put("identificador", identificador);
			object.put("llavePrivada", llavePrivada);
			object.put("participante", participante);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}	

	interface CallBack {
		void doInBackground(String Json_server);
	}

	Socket socket = null;
	
	static boolean flag = false;
	static String json_mje = "";

	@Override
	protected Void doInBackground(String... action) {
		
		Log.e("asd","entro");

		try {
			while (true) {
				socket = new Socket("192.168.1.80", 4444);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);

				out = new PrintWriter(socket.getOutputStream(), true);
				
				if(flag){
					out.println(json_mje + "\0");
					flag = false;
				}
				else{
					out.println(getMjs() + "\0");
				}			

				_mycallback.doInBackground(in.readLine());

				Thread.sleep(100);
			}
		} catch (Exception ex) {
			Log.e("Algnu", ex.toString());
			ex.printStackTrace();
		}
		return null;
	}
}
