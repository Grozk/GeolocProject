package fr.gro.geolocproject.metier;

import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.geolocproject.R;

import fr.gro.geolocproject.mappingjson.ListIndividus;
import fr.gro.geolocproject.objets.ListIndividuAdapter;
import fr.gro.geolocproject.requetes.requeteBase.HttpClient;

public class PageCentreFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.page_centre_layout, container, false);

		ListView list = (ListView) v.findViewById(R.id.listViewContact);

		HttpClient rqtAjax = new HttpClient();
		AsyncTask<String, Void, String> response = rqtAjax
				.execute("Individus.json");
		String rep = null;
		try {
			rep = response.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(rep);
		ParseListIndividuJson parserListIndividujson = new ParseListIndividuJson();

		// parse de la reponse
		ListIndividus li = parserListIndividujson.getListeIndividus(rep);

		// initilisation du custom adapter pour les individus
		ListIndividuAdapter listIndividuAdapter = new ListIndividuAdapter(this
				.getActivity().getBaseContext(), li.getIndividus());
		list.setAdapter(listIndividuAdapter);

		TextView tv = (TextView) v.findViewById(R.id.pageCentreTextEmpty);
		if (li.getIndividus() != null && !li.getIndividus().isEmpty()) {
			tv.setVisibility(View.INVISIBLE);
		} else {
			tv.setVisibility(View.VISIBLE);
		}

		// for (Individu ind : li.getIndividus()) {
		// System.out.println(ind.getIndividu().getIdIndividuGenere());
		// }
		//
		//
		// Toast.makeText(getActivity(), rep, Toast.LENGTH_LONG)
		// .show();

		return v;
	}


}
