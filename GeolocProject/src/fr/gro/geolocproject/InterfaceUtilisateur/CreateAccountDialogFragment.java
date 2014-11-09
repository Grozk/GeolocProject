package fr.gro.geolocproject.InterfaceUtilisateur;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class CreateAccountDialogFragment extends DialogFragment {

	private Context ctx;

	public CreateAccountDialogFragment(Context ctx) {
		super();
		this.ctx = ctx;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(this.ctx);

		builder.setMessage("Create Account")
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Toast.makeText(getActivity(), "Creation du profil",
								Toast.LENGTH_LONG).show();
					}
				})
				.setNegativeButton("Non ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}

}
