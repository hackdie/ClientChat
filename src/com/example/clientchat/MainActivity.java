package com.example.clientchat;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.clientchat.ClientTask.CallBack;
import com.example.Adapters.ListRoomsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.os.AsyncTask;
import android.os.Bundle;


public class MainActivity extends Activity implements CallBack  {
	
	String llavePrivada;
	String llavePublica;
	
	ListView listRooms;
	ListRoomsAdapter _roomadapter;
	
	ArrayList<String> id_salas;
	ArrayList<String> nombre_salas;
	
	
	AsyncTask<String, Void, Void> hilo;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		id_salas = new ArrayList<String>();
		nombre_salas = new ArrayList<String>();
		
		
		hilo = new ClientTask(this).execute("");
		
		listRooms = (ListView) findViewById(R.id.lisRooms);
		_roomadapter = new ListRoomsAdapter(this);
		
		listRooms.setAdapter(_roomadapter);
		
		listRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	    		  //Toast.makeText(MainActivity.this,"->" + id_salas.get(position), Toast.LENGTH_SHORT).show();
	    		  
	    		  hilo.cancel(true);
	    		  
	    		  Intent intent = new Intent(MainActivity.this,ChatActivity.class);
	    	      intent.putExtra("llave_publica", llavePublica);
	    	      intent.putExtra("llave_privada", llavePrivada);
	    	      intent.putExtra("Room", id_salas.get(position));
	    		  startActivity(intent);
	    	  }
		});
	}
	
	boolean flag = true;
	int users_online = 0;
	String nombre="";
	
	@Override
	public void doInBackground(String Json_server) {
		try {
			JSONObject _json = new JSONObject(Json_server);
			String status = _json.getString("status");
			if(flag){
				if(status.compareTo("ok")==0){
					String _identificador = _json.getString("identificador");
					String _llavePrivada = _json.getString("llavePrivada");
					
					llavePublica = _identificador;
					llavePrivada = _llavePrivada;
					
					flag = false;
				}
			}
			else{
				if(status.compareTo("ok")==0){
					
					JSONObject users = _json.getJSONObject("informacion");
					
					if(users_online != users.length()){
						users_online = users.length();
						id_salas.clear();
						
						runOnUiThread(new Runnable() {  
				            @Override
				            public void run() {
				            	_roomadapter.limpiarlista();
				            }
			    		});
						
						Log.e("asd",users.toString());
						
						Iterator<String> iter = users.keys();
					    while (iter.hasNext()) {
					        
					    	String key = iter.next();
					        id_salas.add(key);
					        
					        JSONObject user = users.getJSONObject(key);
					        nombre = user.getString("usuario");
					        //Log.e("nombre",nombre);
					        nombre_salas.add(user.getString("usuario"));
					    }
					    
					    
					    runOnUiThread(new Runnable() {  
				            @Override
				            public void run() {
				            	_roomadapter.addRow(nombre_salas);
				            	nombre_salas.clear();
				            }
			    		});
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResume() {
	    super.onResume(); 
	    
	    if(!hilo.getStatus().equals(AsyncTask.Status.RUNNING)){
	    	ClientTask.flag_registro = false;
	    	hilo = new ClientTask(this).execute("");
	    }   
	}
}
