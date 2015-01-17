package fr.gro.geolocproject.InterfaceUtilisateur;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.geolocproject.R;

public class ListSelectedContactAdapter extends ArrayAdapter<ListContactObject> {

	Context ctx = null;
	ArrayList<ListContactObject> listContact = null;

	public ListSelectedContactAdapter(Context context, int resource,
			ArrayList<ListContactObject> listContact) {
		super(context, resource);
		this.ctx = context;
		this.listContact = listContact;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	    LayoutInflater inflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.list_contact_selected_item, parent, false);

	    TextView textView = (TextView) rowView.findViewById(R.id.list_contact_selected_item_textview);
	    
		String textFormat = listContact.get(position).getName();

		textView.setText(textFormat);

	    return rowView;
	  }

	@Override
	public int getCount() {
		System.out.println("Taille de la liste : "+listContact.size());
		return listContact.size();
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	
	@Override
	public ListContactObject getItem(int position){
		return listContact.get(position);
	}

}
