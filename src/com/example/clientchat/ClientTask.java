/**AsyncTask sirve para no utilizar objetos Thread ni objetos Handler. 
 * Es mas sencillo de leer.
 */

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

public class ClientTask extends AsyncTask<String, Void, Void> {

	CallBack _mycallback;

	ClientTask(CallBack mycallback) {
		_mycallback = mycallback;
	}

	public String regUser() {
		JSONObject object = new JSONObject();

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String user = df3.format(c.getTime());

		try {

			JSONObject object2 = new JSONObject();

			object2.put("status", "online");
			object2.put("usuario", user);
			object2.put("IP", "192.168.1.144");
			object2.put("puerto", "4444");

			object.put("informacion", object2);
			object.put("accion", "actualizar");
			object.put("identificador", "0");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	public String findUsers() {
		JSONObject object = new JSONObject();
		try {
			object.put("accion", "listar");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
	}

	interface CallBack {
		void doInBackground(String Json_server);
	}

	Socket socket = null;
	static boolean flag_registro = true;

	@Override
	protected Void doInBackground(String... action) {
		try {
			while (true) {
				socket = new Socket("192.168.1.80", 4444);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);

				out = new PrintWriter(socket.getOutputStream(), true);
				if (flag_registro) {
					out.println(regUser() + "\0");
					flag_registro = false;
				} else {
					out.println(findUsers() + "\0");
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