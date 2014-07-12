/**AsyncTask sirve para no utilizar objetos Thread ni objetos Handler. 
 * Es mas sencillo de leer.
 */

package com.example.clientchat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ClientTask extends AsyncTask<String, Void , Void>{
	//Recibe un String que es la accion en sintaxis JSON
	final String serverAddress = "192.168.1.80";
	final int serverPort = 4444;
	
	
	
	public String p(){
		JSONObject object = new JSONObject();
  	  try {
  	    
  	    JSONObject object2 = new JSONObject();
  	    
  	    object2.put("status", "online");
  	    object2.put("usuario", "hackdie");
  	    object2.put("IP", "192.168.1.144");
  	    object2.put("puerto", "4444");
  	    
  	    object.put("informacion",object2);
  	    object.put("accion", "actualizar");
  	    object.put("identificador", "0");
  	    
  	  } catch (JSONException e) {
  	    e.printStackTrace();
  	  }
  	return object.toString();
	}
	
	public String s(){
		JSONObject object = new JSONObject();
  	  try {
  	    object.put("accion","listar");
  	  } catch (JSONException e) {
  	    e.printStackTrace();
  	  }
  	  return object.toString();
	}
	
	
	@Override
	protected Void doInBackground(String... action)
	{
		Socket socket = null;
		try{
			socket = new Socket(serverAddress, serverPort);
			PrintWriter out;
			InputStream is;
			while(true){
				
				out = new PrintWriter(socket.getOutputStream());
				out.println(p() + "\0");
				out.flush();
				
				is = socket.getInputStream();
				String a = getMensaje(is);
				Log.e("prueba",a);
			}
			
		} catch(UnknownHostException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String getMensaje(InputStream is){
		try{
			InputStreamReader in = new InputStreamReader(is);
			StringBuilder sb=new StringBuilder();
			BufferedReader br = new BufferedReader(in);
			String read = br.readLine();

			while(read != null) {
			    sb.append(read);
			    read =br.readLine();
			}
			return sb.toString();
		}	
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}	
}