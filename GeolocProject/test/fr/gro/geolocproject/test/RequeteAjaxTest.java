package fr.gro.geolocproject.test;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import android.os.AsyncTask;
import fr.gro.geolocproject.requetes.requeteBase.HttpClient;

public class RequeteAjaxTest {

	HttpClient rqtAjax;

	@Before
	public void setUp() throws Exception {
		rqtAjax = new HttpClient();
	}

	@Test
	public void testRequeteAjax() {
		AsyncTask<String, Void, String> result = rqtAjax.execute();// getListeIndiv();

		try {
			assertNotNull(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(result.toString());
	}


}
