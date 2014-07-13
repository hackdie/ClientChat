package com.example.Adapters;

import java.util.ArrayList;

import com.example.clientchat.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListRoomsAdapter extends BaseAdapter {

	ArrayList<String> salas;
	int tot_rooms = 0;
	LayoutInflater inflator;

	public ListRoomsAdapter(Context context) {
		salas = new ArrayList<String>();
		inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return salas.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return tot_rooms;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;

		String nombre = salas.get(position);
		vi = inflator.inflate(R.layout.item_list, null);

		TextView txt_nombre = (TextView) vi.findViewById(R.id.txt_room);
		txt_nombre.setText(nombre);

		return vi;
	}

	public void limpiarlista() {
		salas.clear();
		notifyDataSetChanged();
	}

	public void addRow(ArrayList<String> Nombre) {
		tot_rooms++;

		for (int x = 0; x < Nombre.size(); x++) {
			salas.add(Nombre.get(x));
		}

		notifyDataSetChanged();
	}

}
