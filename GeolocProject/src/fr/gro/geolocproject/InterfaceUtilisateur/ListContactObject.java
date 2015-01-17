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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListContactObject other = (ListContactObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}
