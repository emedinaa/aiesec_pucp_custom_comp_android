package com.gdglima.examples.customcomp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gdglima.examples.customcomp.view.component.HeaderViewGroup;
import com.gdglima.examples.customcomp.view.component.OnHeaderListener;
import com.gdglima.examples.customcomp.view.fragments.ExploreFragment;
import com.gdglima.examples.customcomp.view.fragments.HomeFragment;
import com.gdglima.examples.customcomp.view.fragments.MessageFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements OnHeaderListener{

    @InjectView(R.id.header)
    HeaderViewGroup header;

    private HomeFragment homeFragment= HomeFragment.newInstance(null,null);
    private ExploreFragment exploreFragment= ExploreFragment.newInstance(null,null);
    private MessageFragment messageFragment= MessageFragment.newInstance(null,null);
    //private HomeFragment homeFragment= HomeFragment.newInstance(null,null);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        header.app(this);
        selectedFragment(0);
    }

    private void selectedFragment(int i) {
        Fragment fragment=null;

        switch (i)
        {
            case 0:
                fragment= homeFragment;
                break;
            case 1:
                fragment= exploreFragment;
                break;
            case 2:
                fragment= messageFragment;
                break;
        }

        if(fragment!=null)
        {
            changeFragment(null,fragment,null);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return false;
    }

    @Override
    public void optionSelected(int position)
    {
        selectedFragment(position);
    }


    private void changeFragment(Bundle args, Fragment fragment, String tag)
    {
        if (args != null) fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment, tag).commit();
    }
}
