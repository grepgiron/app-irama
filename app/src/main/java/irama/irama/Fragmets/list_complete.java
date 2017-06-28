package irama.irama.Fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import irama.irama.R;
import irama.irama.Sqlite.DBHelper;

/**
 * Created by enagi on 26/6/2017.
 */

public class list_complete extends Fragment {

    private DBHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_listcomplete, container, false);
        dbHelper = new DBHelper(vw.getContext());


        return vw;

    }
}
