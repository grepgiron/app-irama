package irama.irama.Controllers.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import irama.irama.Activity.Fragmets.List_Complete.list_complete;
import irama.irama.Activity.Fragmets.List_Pending.list_pending;

/**
 * Created by enagi on 26/6/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                list_pending tap_pending = new list_pending();
                return tap_pending;
            case 1:
                list_complete tap_complete = new list_complete();
                return tap_complete;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
