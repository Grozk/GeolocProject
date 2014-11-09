package fr.gro.geolocproject.InterfaceUtilisateur;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class for mapping select multiple contacts from listContact
 * 
 * @author Goulven
 * 
 */
public class ListContactObject implements Parcelable {

	private String name = "";
	private String number = "";
	private boolean isChecked = false;

	public ListContactObject() {
		super();
	}

	public ListContactObject(String name, String number, boolean isChecked) {
		super();
		this.name = name;
		this.number = number;
		this.isChecked = isChecked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(this.name);
		arg0.writeString(this.number);
		arg0.writeString(this.isChecked ? "1" : "0");
	}

	public static final Parcelable.Creator<ListContactObject> CREATOR = new Parcelable.Creator<ListContactObject>() {
		@Override
		public ListContactObject createFromParcel(Parcel source) {
			return new ListContactObject(source);
		}

		@Override
		public ListContactObject[] newArray(int size) {
			return new ListContactObject[size];
		}
	};

	public ListContactObject(Parcel in) {
		this.name = in.readString();
		this.number = in.readString();
		this.isChecked = in.readString().equalsIgnoreCase("1") ? true : false;
	}

}
