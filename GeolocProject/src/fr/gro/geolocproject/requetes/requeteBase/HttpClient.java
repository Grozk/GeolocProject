package fr.gro.geolocproject.requetes.requeteBase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class HttpClient extends AsyncTask<String, Void, String> {

	private static String BASE_URL = "http://geolocproject.alwaysdata.net/geolocTest/";

	// http://geolocproject.alwaysdata.net/geolocTest/Individus/view/1.json
	// http://geolocproject.alwaysdata.net/geolocTest/Individus/add.json

	public String getListeIndiv(String... arg0) {
		HttpURLConnection con = null;
		InputStream is = null;
		StringBuffer buffer = null;
		try {
			System.out.println("Debut de la demande");
			con = (HttpURLConnection) (new URL(BASE_URL + arg0[0])
					.openConnection());
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();

			// Let's read the response
			buffer = new StringBuffer();
			con.getResponseCode();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null)
				buffer.append(line + "\r\n");

			is.close();
			con.disconnect();
			System.out.println(buffer.toString());
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
			try {
				con.disconnect();
			} catch (Throwable t) {
			}
		}

		return buffer.toString();

	}

	@Override
	protected String doInBackground(String... arg0) {
		return getListeIndiv(arg0);
	}



}
