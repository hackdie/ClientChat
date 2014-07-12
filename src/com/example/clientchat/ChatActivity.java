package com.example.clientchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChatActivity extends Activity {
	
	
	Button buttonEnviar;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
		
		
		buttonEnviar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				//objClient.setAction("0","online","hackdie","192.168.1.80","4444");
			}
		});
		
		
	}

}
