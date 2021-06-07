package ridaz.wssalmaak.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ridaz.wssalmaak.ui.updateprofile.cars.CarsFragment;
import ridaz.wssalmaak.ui.updateprofile.personne.PersonneFragment;

public class PageViewAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;


    public PageViewAdapter(Context context, int totalTabs, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Fragment personneFragment = new PersonneFragment();
                return personneFragment;
            case 1:
                Fragment carsFragment = new CarsFragment();
                return carsFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
