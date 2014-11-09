package fr.gro.geolocproject.metier;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geolocproject.R;

import fr.gro.geolocproject.InterfaceUtilisateur.ListContactActivity;
import fr.gro.geolocproject.InterfaceUtilisateur.ListContactObject;

public class PageGaucheFragment extends Fragment implements OnClickListener {

	static final int PICK_CONTACT_REQUEST = 1; // The request code
	private static final int RESULT_OK = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.page_gauche_layout, container, false);

		EditText etNumAChercher = (EditText) v
				.findViewById(R.id.layoutGauche_EditText_numAChercher);
		Button buttonRepertoire = (Button) v
				.findViewById(R.id.layoutGauche_buttonRepertoire);

		buttonRepertoire.setOnClickListener(this);

		return v;
	}

	// private void launchMultiplePhonePicker() {
	// Intent intent = new Intent(Intent.ACTION_PICK);
	// intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
	// startActivityForResult(intent, PICK_CONTACT_REQUEST);
	// }

	@Override
	public void onClick(View arg0) {
		// Mettez le nom de l'Activity dans la quelle vous etes actuellement
		Intent intent = new Intent(this.getActivity(),
				ListContactActivity.class);

		// On demarre l'autre Activity
		startActivityForResult(intent, PICK_CONTACT_REQUEST);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == PICK_CONTACT_REQUEST) {
			Toast.makeText(this.getActivity(), "Activity result",
					Toast.LENGTH_SHORT).show();
			// // Make sure the request was successful
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				ArrayList<ListContactObject> listContactSelect = (ArrayList<ListContactObject>) extras.get("listContact");
				for (ListContactObject item : listContactSelect){
					Toast.makeText(getActivity(), "Numero."+item.getNumber(),
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

}
