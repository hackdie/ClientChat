package com.example.Adapters;

import com.example.clientchat.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListRoomsAdapter extends BaseAdapter {
	
	Activity activity;  
	String[] arraydatos; 
	
	
	public ListRoomsAdapter(Activity myactivity, String[] _datos ){
		activity = myactivity;
		arraydatos = _datos;
	}
	
	
	@Override
	public int getCount() {
		return arraydatos.length;
	}

	@Override
	public Object getItem(int position) {
		return arraydatos[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		LayoutInflater inflator = activity.getLayoutInflater();	
		
		String nombre = arraydatos[position];
		vi = inflator.inflate(R.layout.item_list, null);
		
		TextView txt_nombre = (TextView)vi.findViewById(R.id.txt_room);
		txt_nombre.setText(nombre);
		
		return vi;
	}

}
