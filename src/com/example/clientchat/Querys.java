package com.example.clientchat;

import org.json.JSONException;
import org.json.JSONObject;

public class Querys {
	
	Client _cliente;

	public void actualizarUsuario(String... data) {
		JSONObject comando = new JSONObject();
		try {
			JSONObject inf = new JSONObject();

			inf.put("status", data[1]);
			inf.put("usuario", data[2]);
			inf.put("IP", data[3]);
			inf.put("puerto", data[4]);

			comando.put("informacion", inf);
			comando.put("accion", "actualizar");
			comando.put("identificador", data[0]);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		_cliente.setAction(comando.toString());
	}
	
	public String listarUsuario(){
		JSONObject comando = new JSONObject();
		try {
			comando.put("accion", "listar");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return comando.toString();
	}
	
	
	
	

}
