package fr.gro.geolocproject.metier;

import java.util.List;
import java.util.Vector;

import com.example.geolocproject.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class FragmentSliderActivity extends FragmentActivity {

	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);

		// Cr�ation de la liste de Fragments que fera d�filer le PagerAdapter
		List<Fragment> fragments = new Vector();

		// Ajout des Fragments dans la liste
		fragments.add(Fragment.instantiate(this,
				PageGaucheFragment.class.getName()));
		fragments.add(Fragment.instantiate(this,
				PageCentreFragment.class.getName()));
		fragments.add(Fragment.instantiate(this,
				PageDroiteFragment.class.getName()));

		// Cr�ation de l'adapter qui s'occupera de l'affichage de la liste de
		// Fragments
		this.mPagerAdapter = new MyPageAdpater(
				super.getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
		// Affectation de l'adapter au ViewPager
		pager.setAdapter(this.mPagerAdapter);
		// Selection du fragment du milieu pour d�buter
		pager.setCurrentItem(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
