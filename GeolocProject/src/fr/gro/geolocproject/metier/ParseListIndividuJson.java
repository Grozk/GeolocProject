package fr.gro.geolocproject.metier;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gro.geolocproject.mappingjson.ListIndividus;

public class ParseListIndividuJson {

	public ListIndividus getListeIndividus(String jsonToParse) {

		ObjectMapper mapper = new ObjectMapper();
		ListIndividus listIndividu = null;
		try {
			listIndividu = mapper.readValue(jsonToParse, ListIndividus.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listIndividu;
	}
}
