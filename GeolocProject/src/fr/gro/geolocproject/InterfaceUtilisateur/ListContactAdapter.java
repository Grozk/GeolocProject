package fr.gro.geolocproject.InterfaceUtilisateur;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.geolocproject.R;

public class ListContactAdapter extends ArrayAdapter<ListContactObject> {

	Context ctx = null;
	ArrayList<ListContactObject> listContact = null;

	public ListContactAdapter(Context context, int resource,
			ArrayList<ListContactObject> listContact) {
		super(context, resource);
		this.ctx = context;
		this.listContact = listContact;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	    LayoutInflater inflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.list_contact_item, parent, false);

	    TextView textView = (TextView) rowView.findViewById(R.id.list_contact_item_textview);
	    CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.list_contact_item_checkbox);
	    
		String textFormat = listContact.get(position).getName() + " : "
				+ listContact.get(position).getNumber();

		textView.setText(textFormat);
		checkBox.setClickable(true);
		if (listContact.get(position).isChecked()){
			checkBox.setChecked(true);
		} else {
			checkBox.setChecked(false);
		}

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
