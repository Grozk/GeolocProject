package fr.gro.geolocproject.InterfaceUtilisateur;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import fr.gro.geolocproject.Command.Command;
import fr.gro.geolocproject.Command.CommandWrapper;

public class ObjetInterfaceUtilisateur {

	private static final CommandWrapper DISMISS = new CommandWrapper(
			Command.NO_OP);

	public static AlertDialog getPseudonymeDialog(final Context context,
			final String name, final Command getPseudonyme) {

		// CreateAccountDialogFragment dialog = new CreateAccountDialogFragment(
		// context);
		// dialog.show((FragmentActivity)
		// context.getgetSupportFragmentManager(),
		// "MyDF");

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		// builder.setIcon(R.drawable.dialog_question);
		builder.setTitle("Choix du pseudonyme : ");
		builder.setInverseBackgroundForced(true);

		// Set an EditText view to get user input
		EditText input = new EditText(context);
		builder.setView(input);

		builder.setPositiveButton("Yes", new CommandWrapper(getPseudonyme));
		builder.setNegativeButton("No", DISMISS);
		return builder.create();
	}

}
