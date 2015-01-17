package fr.gro.geolocproject.InterfaceUtilisateur;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.geolocproject.R;

public class ListContactActivity extends ListActivity implements OnClickListener, OnItemClickListener {

	// This is the Adapter being used to display the list's data
	// SimpleCursorAdapter mAdapter;
	ListContactAdapter mAdapter;
	ListView mList = null;

	// These are the Contacts rows that we will retrieve
	static final String[] PROJECTION = new String[] {
			ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME };

	// This is the select criteria
	static final String SELECTION = "((" + ContactsContract.Data.DISPLAY_NAME
			+ " NOTNULL) AND (" + ContactsContract.Data.DISPLAY_NAME
			+ " != '' ))";

	private static final int RESULT_OK = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_contact);
		
		mList = (ListView)findViewById(android.R.id.list);
		mAdapter = new ListContactAdapter(this.getApplicationContext(),
				R.layout.list_contact_item, fetchContacts());
		Button buttonValid = (Button)findViewById(R.id.activity_list_contact_buttonOK);
		buttonValid.setOnClickListener(this);
		
		setListAdapter(mAdapter);
		getListView().setOnItemClickListener(this);
	}

	public ArrayList<ListContactObject> fetchContacts() {

		ArrayList<ListContactObject> listContact = new ArrayList<ListContactObject>();

		String phoneNumber = null;

		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		String _ID = ContactsContract.Contacts._ID;
		String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
		String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

		Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

		// StringBuffer output = new StringBuffer();

		ContentResolver contentResolver = getContentResolver();

		Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null,
				null);

		// Loop for every contact in the phone
		if (cursor.getCount() > 0) {

			while (cursor.moveToNext()) {
				// init du contact
				ListContactObject objContact = new ListContactObject();

				String contact_id = cursor
						.getString(cursor.getColumnIndex(_ID));
				String name = cursor.getString(cursor
						.getColumnIndex(DISPLAY_NAME));

				int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(HAS_PHONE_NUMBER)));

				if (hasPhoneNumber > 0) {

					objContact.setName(name);

					// Query and loop for every phone number of the contact
					Cursor phoneCursor = contentResolver.query(
							PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?",
							new String[] { contact_id }, null);

					while (phoneCursor.moveToNext()) {
						phoneNumber = phoneCursor.getString(phoneCursor
								.getColumnIndex(NUMBER));
						objContact.setNumber(phoneNumber);

					}

					phoneCursor.close();
				}

				// ajout du contact a la liste retourn�e
				if (!objContact.getNumber().isEmpty()
						&& !objContact.getName().isEmpty()) {
					System.out.println(objContact.getName());
					listContact.add(objContact);
				}
			}
		}
		return listContact;
	}

	@Override
	public void onClick(View v) {
		
		
		switch(v.getId()){
		case R.id.activity_list_contact_buttonOK : 
			ArrayList<ListContactObject> resultList = new ArrayList<ListContactObject>();
			for (int i = 0; i < mList.getAdapter().getCount(); i++){
				ListContactObject objContact = (ListContactObject)mList.getAdapter().getItem(i);
				mList.getItemAtPosition(i);
				if (objContact.isChecked()){
					resultList.add(objContact);
				}
			}
			
			Intent intent= getIntent();
			Bundle bundleReturn = new Bundle();
			bundleReturn.putParcelableArrayList("listContact", resultList);
			intent.putExtras(bundleReturn);
			setResult(RESULT_OK,intent);
			finish();
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ListContactObject obj =((ListContactObject)mList.getItemAtPosition(arg2));
		if (!obj.isChecked()){
			obj.setChecked(true);
		} else {
			obj.setChecked(false);
		}
		
		mAdapter.notifyDataSetChanged();
		}

}
