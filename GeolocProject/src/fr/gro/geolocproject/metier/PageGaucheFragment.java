package fr.gro.geolocproject.metier;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.geolocproject.R;

import fr.gro.geolocproject.InterfaceUtilisateur.ListContactActivity;
import fr.gro.geolocproject.InterfaceUtilisateur.ListContactObject;
import fr.gro.geolocproject.InterfaceUtilisateur.ListSelectedContactAdapter;
import fr.gro.geolocproject.requetes.requeteBase.HttpClient;

public class PageGaucheFragment extends Fragment implements OnClickListener, OnItemLongClickListener{

	static final int PICK_CONTACT_REQUEST = 1; // The request code
	private static final int RESULT_OK = 0;
	ListView listContactSelectionnes = null;
	EditText numAChercher = null;
	
	ListSelectedContactAdapter mAdapterListSelectedContact;
	ArrayList<ListContactObject> listSelectedContacts = new ArrayList<ListContactObject>();
	

	HttpClient rqtAjax = new HttpClient();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.page_gauche_layout, container, false);

		numAChercher = (EditText) v
				.findViewById(R.id.layoutGauche_EditText_numAChercher);
		Button buttonRepertoire = (Button) v
				.findViewById(R.id.layoutGauche_buttonRepertoire);
		Button buttonValidationContact = (Button) v
				.findViewById(R.id.layoutGauche_button_envoyerAjouterContact);
		numAChercher.setEnabled(true);
		
		listContactSelectionnes = (ListView) v
				.findViewById(R.id.layoutGauche_listViewContactSelectionnes);

		mAdapterListSelectedContact = new ListSelectedContactAdapter(
				this.getActivity(), R.layout.list_contact_selected_item,
				listSelectedContacts);
		listContactSelectionnes.setAdapter(mAdapterListSelectedContact);
		listContactSelectionnes.setOnItemLongClickListener(this);
		
		buttonRepertoire.setOnClickListener(this);
		buttonValidationContact.setOnClickListener(this);
		
		return v;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()){
		case R.id.layoutGauche_buttonRepertoire:
			if ("".equals(numAChercher.getText().toString())){
				lauchListContactActivity();
			} else {
				// un numero a chercher dans le repertoire du telephone
				ListContactObject contact = new ListContactObject();
				contact.setNumber(numAChercher.getText().toString());
				String name = null;
				// recuperation du nom du contact associé au numero dans l'editText
				name = getContactName(this.getActivity(),contact.getNumber());
				
				if (name == null){
					Toast.makeText(this.getActivity(), "Aucun contact ne correspond à ce numéro", Toast.LENGTH_LONG).show();
				} else {
					//ajout du numero a la liste des contact à ajouter
					contact.setName(name);
					contact.setChecked(true);
					
					listSelectedContacts.add(contact);
					mAdapterListSelectedContact.notifyDataSetChanged();
				}
			}
				break;
		case R.id.layoutGauche_button_envoyerAjouterContact:
			//message box de confirmation
			alertDialogConfirmation();
			break;
		}
		
	}
	
	/**
	 * Lance l'activité d'affichage des contacts du telephone
	 */
	private void lauchListContactActivity(){
		// preparation de l'activité d'affichage des contact du telephone
		Intent intent = new Intent(this.getActivity(),
				ListContactActivity.class);

		// On demarre l'activity
		startActivityForResult(intent, PICK_CONTACT_REQUEST);
	}
	
	/**
	 * Gestion de la liste des contacts selectionnés
	 * - s'il sont inscrits : addContactToFollowers
	 * - s'ils ne sont pas inscrits : sendInvitContact
	 * 
	 *  TODO optimisation coté serveur pour tester une liste de contact entiere afin d'eviter les nombreuses requetes
	 */
	private void gestionListContacts(){
		//TODO msg d'attente ON
		ArrayList<ListContactObject> listeInscrits = new ArrayList<ListContactObject>();
		ArrayList<ListContactObject> listeNonInscrits = new ArrayList<ListContactObject>();

		
		// on test chaque contact sélectionné s'il est inscrit
		for (ListContactObject currentContact : listSelectedContacts){
			
			// test si le contact est déjà inscrit via le num de telephone
			AsyncTask<String, Void, String> response = rqtAjax
					.execute("Individus.json");
			String rep = null;
			try {
				rep = response.get();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
					
			if ("1".equals(rep)){
				listeInscrits.add(currentContact);
			} else {
				listeNonInscrits.add(currentContact);
			}
			
		}
		
		//traitement des différentes listes
		sendInvitContact(listeNonInscrits);
		addContactToFollowers(listeInscrits);
		//TODO msg d'attente OFF
	}
	/**
	 * Envoi une invitation aux contacts non inscrits sur l'application 
	 */
	private void sendInvitContact(ArrayList<ListContactObject> listeNonInscrits){
		
		// envoi de l'invit a la liste des contacts non inscrits sur l'application
		for (ListContactObject currentContact : listeNonInscrits){
			// inscription temporaire du contact
			AsyncTask<String, Void, String> response = rqtAjax
						.execute("ADDInscriptionTemporaire.json");
//			String rep = null;
//			try {
//				rep = response.get();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 			
		}
		//envoi d'un SMS à tous les numéros ensemble pour proposer le téléchargement de l'application

	}
	
	/**
	 * Ajoute le contact à la liste des amis à suivre
	 */
	private void addContactToFollowers(ArrayList<ListContactObject> listeInscrits ){
		// ajout la liste des contacts selectionnés à la liste des amis à suivre
		for (ListContactObject currentContact : listeInscrits){
			AsyncTask<String, Void, String> response = rqtAjax
					.execute("ajoutLienEntreDeuxPersonnes.json");
		}
	}
	
	/**
	 * Get contact from number
	 * 
	 * @param context
	 * @param phoneNumber
	 * @return
	 */
	public static String getContactName(Context context, String phoneNumber) {
	    ContentResolver cr = context.getContentResolver();
	    Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
	    Cursor cursor = cr.query(uri, new String[]{PhoneLookup.DISPLAY_NAME}, null, null, null);
	    if (cursor == null) {
	        return null;
	    }
	    String contactName = null;
	    if(cursor.moveToFirst()) {
	        contactName = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));
	    }

	    if(cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    }

	    return contactName;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == PICK_CONTACT_REQUEST) {

			// // Make sure the request was successful
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				ArrayList<ListContactObject> listContactSelect = (ArrayList<ListContactObject>) extras
						.get("listContact");
				for (ListContactObject item : listContactSelect) {
					Toast.makeText(this.getActivity(),
							"Numero." + item.getNumber(), Toast.LENGTH_LONG)
							.show();
					if (!listSelectedContacts.contains(item)) {
						listSelectedContacts.add(item);
					}
				}
			}
			mAdapterListSelectedContact.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		listSelectedContacts.remove(listSelectedContacts.indexOf(listContactSelectionnes.getItemAtPosition(arg2)));
		
		mAdapterListSelectedContact.notifyDataSetChanged();
		return false;
	}

	
	private void alertDialogConfirmation() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setCancelable(true);
		builder.setTitle("Etes-vous sur ?");
		builder.setInverseBackgroundForced(true);

		// Set an EditText view to get user input
		final EditText input = new EditText(getActivity());
		input.setText("Les contacts qui ne sont pas inscrits sur l'application recevront un message d'invitation. Les autres seront ajoutés à votre liste d'amis.");
		builder.setView(input);

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				//fonction de gestion des contacts
				gestionListContacts();
				
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
