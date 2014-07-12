package com.example.clientchat;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;


public class MainActivity extends Activity {
	
	static String llavePrivada;
	static String llavePublica;
	
	Button buttonEnviar;
	Client objClient;
	Querys query;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new ClientTask().execute("");
		
		//query.actualizarUsuario("0","online","hackdie","192.168.1.80","4444");
		
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
		
		buttonEnviar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				objClient.setAction("0","online","hackdie","192.168.1.80","4444");
			}
		});	
	}
}
