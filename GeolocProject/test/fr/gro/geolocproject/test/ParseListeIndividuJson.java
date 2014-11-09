package fr.gro.geolocproject.test;

import org.junit.Test;

import fr.gro.geolocproject.mappingjson.Individu;
import fr.gro.geolocproject.mappingjson.ListIndividus;
import fr.gro.geolocproject.metier.ParseListIndividuJson;

public class ParseListeIndividuJson {

	@Test
	public void testParseListeIndividuJson() {
		String rep = "{"
				+ "   \"individus\": ["
				+ "        {"
				+ "    \"Individu\": {"
				+ "         \"id\": \"1\","
				+ "          \"telephone\": \"0298524125\","
				+ "           \"idIndividuGenere\": \"654654\","
				+ "            \"dateInscription\": \"2014-07-20 18:09:52\","
				+ "             \"isDesactive\": false"
				+ "          }"
				+ "       },"
				+ "        {"
				+ "    \"Individu\": {"
				+ "         \"id\": \"2\","
				+ "          \"telephone\": \"0001122554\","
				+ "           \"idIndividuGenere\": \"654qsd\","
				+ "            \"dateInscription\": \"2014-07-20 18:09:52\","
				+ "             \"isDesactive\": false"
				+ "          }"
				+ "       },"
				+ "        {"
				+ "    \"Individu\": {"
				+ "         \"id\": \"3\","
				+ "          \"telephone\": \"3333333333\","
				+ "           \"idIndividuGenere\": \"654aze\","
				+ "            \"dateInscription\": \"2014-07-20 18:09:52\","
				+ "             \"isDesactive\": false"
				+ "          }"
				+ "       },"
				+ "        {"
				+ "    \"Individu\": {"
				+ "         \"id\": \"4\","
				+ "          \"telephone\": \"2586954581\","
				+ "           \"idIndividuGenere\": \"20bad0b295d4538f9d0130e6f63304c8\","
				+ "            \"dateInscription\": \"2014-07-20 18:40:32\","
				+ "             \"isDesactive\": false"
				+ "          }"
				+ "       },"
				+ "        {"
				+ "    \"Individu\": {"
				+ "         \"id\": \"5\","
				+ "          \"telephone\": \"2586954581\","
				+ "           \"idIndividuGenere\": \"b39effb7619a7f797a72cd86b9ab6f9e\","
				+ "            \"dateInscription\": \"2014-07-20 18:40:39\","
				+ "             \"isDesactive\": false"
				+ "          }"
				+ "       },"
				+ "        {"
				+ "   \"Individu\": {"
				+ "        \"id\": \"6\","
				+ "         \"telephone\": \"2586954581\","
				+ "          \"idIndividuGenere\": \"f9dbdd24c2b56ad2256434650911f8bc\","
				+ "           \"dateInscription\": \"2014-07-21 18:26:32\","
				+ "            \"isDesactive\": false" + "         }"
				+ "      }" + "   ]" + "}";
		System.out.println(rep);
		ParseListIndividuJson parserListIndividujson = new ParseListIndividuJson();

		ListIndividus li = parserListIndividujson.getListeIndividus(rep);

		for (Individu ind : li.getIndividus()) {
			System.out.println(ind.getIndividu().getIdIndividuGenere());
		}

	}
}
