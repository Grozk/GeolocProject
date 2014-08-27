package fr.gro.geolocproject.metier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.geolocproject.R;

public class PageDroiteFragment extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.page_droite_layout, container, false);

		// SharedPreferences prfs = PreferenceManager
		// .getDefaultSharedPreferences(getActivity()
		// .getApplicationContext());
		// Boolean isFirstTime = prfs.getBoolean("isFirstTime", false);
		//
		// Button mButtonInscription = (Button) getView().findViewById(
		// R.id.layoutPageExplication_buttonInscription);
		// mButtonInscription.setOnClickListener(this);
		//
		// Button mButtonPartageApplication = (Button) getView().findViewById(
		// R.id.layoutPageExplication_buttonPartageApplication);
		// mButtonPartageApplication.setOnClickListener(this);
		//
		// if (isFirstTime) {
		//
		// } else {
		//
		// }

		// pop up definir un pseudo pour que vos amis vous reconnaissent
		// generation automatique

		// getSharedPreferences("AUTHENTICATION_FILE_NAME",
		// Context.MODE_PRIVATE);
		// String Astatus = prfs.getString("Authentication_Status", "");

		return v;
	}

	@Override
	public void onClick(View arg0) {
		//
		// switch (arg0.getId()) {
		// case R.id.layoutPageExplication_buttonInscription:
		//
		// ObjetInterfaceUtilisateur.getPseudonymeDialog(getActivity()
		// .getApplicationContext(), "",
		// Command.PSEUDONYME);
		// break;
		// case R.id.layoutPageExplication_buttonPartageApplication:
		// break;
		// }
	}

}
