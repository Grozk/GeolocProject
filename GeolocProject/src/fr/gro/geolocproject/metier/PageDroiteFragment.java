package fr.gro.geolocproject.metier;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geolocproject.R;

public class PageDroiteFragment extends Fragment implements OnClickListener {

	public String PREFS_NAME = "GEOLOC_PREF";
	private SharedPreferences prfs;
	private TextView tvPseudo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_droite_inscription_layout,
				container, false);

		prfs = this.getActivity().getSharedPreferences(
				PREFS_NAME, 0);
		Boolean isFirstTime = prfs.getBoolean("isFirstTime", true);

		// init des boutons
		Button mButtonInscription = (Button) v
				.findViewById(R.id.layoutPageInscription_buttonInscription);
		mButtonInscription.setOnClickListener(this);

		Button mButtonPartageApplication = (Button) v
				.findViewById(R.id.layoutPageExplication_buttonPartageApplication);
		mButtonPartageApplication.setOnClickListener(this);
		mButtonPartageApplication.setEnabled(true);

		tvPseudo = (TextView) v
				.findViewById(R.id.layoutPageInscription_TextviewPseudo);

		// parametrage des titres
		if (isFirstTime) {
			// Page d'acceuil
			SharedPreferences.Editor editor = prfs.edit();
			editor.putBoolean("isFirstTime", false);
			editor.commit();
		} else {
			miseAjourPseudo();

		}



		return v;
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.layoutPageInscription_buttonInscription:
			alertDialog();
			break;
		case R.id.layoutPageExplication_buttonPartageApplication:
			// show QRCode
			Toast.makeText(getActivity(), "Show QRCode à coder.",
					Toast.LENGTH_LONG).show();
			break;
		}
	}

	private void miseAjourPseudo() {
		//
		String pseudo = prfs.getString("pseudo", "");
		tvPseudo.setText("Vous êtes inscrit avec le pseudo : " + pseudo);
	}

	public void createProfil(String pseudo) {
		if (pseudo != null) {

			SharedPreferences.Editor editor = prfs.edit();
			editor.putString("pseudo", pseudo);
			editor.commit();

			Toast.makeText(getActivity(), "Creation du profil => " + pseudo,
					Toast.LENGTH_LONG).show();

			miseAjourPseudo();
		} else {
			Toast.makeText(getActivity(), "Votre pseudo n'est pas correct.",
					Toast.LENGTH_LONG).show();
		}
	}

	public void alertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setCancelable(true);
		builder.setTitle("Choix du pseudonyme : ");
		builder.setInverseBackgroundForced(true);

		// Set an EditText view to get user input
		final EditText input = new EditText(getActivity());
		input.setHint("Entrez votre pseudo");
		builder.setView(input);

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				createProfil(input.getText().toString());
				dialog.cancel();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				dialog.cancel();
			}
		});
		builder.show();
	}
}
