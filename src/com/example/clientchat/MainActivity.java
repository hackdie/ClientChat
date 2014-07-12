package com.example.clientchat;

import java.io.IOException;
import java.io.InputStream;

import com.example.Adapters.ListRoomsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;


public class MainActivity extends Activity {
	
	static String llavePrivada;
	static String llavePublica;
	
	
	Client objClient;
	Querys query;
	ListView listRooms;
	ListRoomsAdapter _roomadapter;
	
	
	String[] tempo_rooms = new String[] {"root","room1","room2","room3","room4"};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//new ClientTask().execute("");
		
		listRooms = (ListView) findViewById(R.id.lisRooms);
		_roomadapter = new ListRoomsAdapter(this,tempo_rooms);
		
		
		listRooms.setAdapter(_roomadapter);
		
		
		listRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	    		  //Toast.makeText(MainActivity.this,"Fecha de recibido:", Toast.LENGTH_SHORT).show();
	    		  
	    		  Intent intent = new Intent(MainActivity.this,ChatActivity.class);
	    		  
	    		  //varibles
	    		  
	    		  startActivity(intent);
	    	  }
		});
		
		
			
	}
}
