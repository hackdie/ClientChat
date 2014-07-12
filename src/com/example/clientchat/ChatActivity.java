package com.example.clientchat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends Activity {
	
	
	Button buttonEnviar;
	TextView txt_mjes;
	EditText txt_send_mjes;
	
	
	static ArrayList<String> root_mje;
	static ArrayList<String> users_root;
	static ArrayList<String> user1_mje;
	static ArrayList<String> users_room1;
	static ArrayList<String> user2_mje;
	static ArrayList<String> users_room2;
	static ArrayList<String> user3_mje;
	static ArrayList<String> users_room3;
	static ArrayList<String> user4_mje;
	static ArrayList<String> users_room4;
	
	String User,Room;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		
		llenardatos();
		
		txt_mjes = (TextView)findViewById(R.id.txt_chat);
		txt_send_mjes = (EditText)findViewById(R.id.txtSendMje);
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
		
		Intent notif = getIntent();
        User = notif.getStringExtra("User");
        Room = notif.getStringExtra("Room");
		
        if(Room.compareTo("Root")==0){cargarMjes(root_mje,users_root);}
        else if(Room.compareTo("Room1")==0){cargarMjes(user1_mje,users_room1);}
        else if(Room.compareTo("Room2")==0){cargarMjes(user2_mje,users_room2);}
        else if(Room.compareTo("Room3")==0){cargarMjes(user3_mje,users_room3);}
        else if(Room.compareTo("Room4")==0){cargarMjes(user4_mje,users_room4);}
        else{}
        
        
		
		
		
		
		buttonEnviar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				if(txt_send_mjes.getText().toString() != ""){
					txt_mjes.append(txt_send_mjes.getText() + "\n");
					txt_send_mjes.setText("");
				}
				else{
					Toast.makeText(ChatActivity.this,"Ingresa un mensaje", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	
	public void cargarMjes(ArrayList<String> mjes, ArrayList<String> _users ){
		int tot = _users.size();
		
		for(int x = 0; x< tot ; x++){
			if(_users.get(x).compareTo(User)==0){
				txt_mjes.append("-> ");
			}
			txt_mjes.append(mjes.get(x) + "\n");
		}
	}
	
	
	public void llenardatos(){
		
		root_mje = new ArrayList<String>();
		users_root = new ArrayList<String>();;
		user1_mje = new ArrayList<String>();;
		users_room1 = new ArrayList<String>();;
		user2_mje = new ArrayList<String>();;
		users_room2 = new ArrayList<String>();;
		user3_mje = new ArrayList<String>();;
		users_room3 = new ArrayList<String>();;
		user4_mje = new ArrayList<String>();;
		users_room4 = new ArrayList<String>();;
		
		root_mje.add("Hola mje1 para root");
		root_mje.add("Hola mje2 para root");
		root_mje.add("Hola mje3 para root");
		root_mje.add("Hola mje4 para root");
		
		users_root.add("hackdie");
		users_root.add("2014 07 08 15:25:16");
		users_root.add("2014 07 08 15:26:16");
		users_root.add("2014 07 08 15:27:16");
		
		
		user1_mje.add("Hola mje1 para user1");
		user1_mje.add("Hola mje2 para user1");
		user1_mje.add("Hola mje3 para user1");
		user1_mje.add("Hola mje4 para user1");
		
		users_room1.add("hackdie");
		users_room1.add("2014 07 08 15:25:16");
		users_room1.add("2014 07 08 15:26:16");
		users_room1.add("2014 07 08 15:27:16");
		
		user2_mje.add("Hola mje1 para user2");
		user2_mje.add("Hola mje2 para user2");
		user2_mje.add("Hola mje3 para user2");
		user2_mje.add("Hola mje4 para user2");
		
		users_room2.add("2014 07 08 15:25:16");
		users_room2.add("hackdie");
		users_room2.add("2014 07 08 15:26:16");
		users_room2.add("2014 07 08 15:27:16");
		
		user3_mje.add("Hola mje1 para user3");
		user3_mje.add("Hola mje2 para user3");
		user3_mje.add("Hola mje3 para user3");
		user3_mje.add("Hola mje4 para user3");
		
		users_room3.add("2014 07 08 15:25:16");
		users_room3.add("2014 07 08 15:26:16");
		users_room3.add("hackdie");
		users_room3.add("2014 07 08 15:27:16");
		
		user4_mje.add("Hola mje1 para user4");
		user4_mje.add("Hola mje2 para user4");
		user4_mje.add("Hola mje3 para user4");
		user4_mje.add("Hola mje4 para user4");
		
		users_room4.add("2014 07 08 15:25:16");
		users_room4.add("2014 07 08 15:26:16");
		users_room4.add("hackdie");
		users_room4.add("2014 07 08 15:27:16");
		
	}

}
