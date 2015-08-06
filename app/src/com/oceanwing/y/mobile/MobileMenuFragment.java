package com.oceanwing.y.mobile;

// import com.oceanwing.y.mobile.account.EditPaymentActivity;
import com.oceanwing.y.plugin.account.RegisterIdentifierActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.util.Log;

public class MobileMenuFragment extends Fragment 
    implements View.OnClickListener {

    private static final String TAG = "##skywang-Menu";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mobile_menu_fragment, null);

        rootView.findViewById(R.id.person_container).setOnClickListener(this);
        rootView.findViewById(R.id.history_container).setOnClickListener(this);
        rootView.findViewById(R.id.payment_container).setOnClickListener(this);
        rootView.findViewById(R.id.share_container).setOnClickListener(this);
        rootView.findViewById(R.id.promo_container).setOnClickListener(this);
        rootView.findViewById(R.id.help_container).setOnClickListener(this);
        rootView.findViewById(R.id.about_container).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_container: {
                // startActivity(new Intent(this.getActivity(), EditProfileActivity.class));
                startActivity(new Intent(this.getActivity(), RegisterIdentifierActivity.class));
                break;
            }
            case R.id.history_container: {
                break;
            }
            case R.id.payment_container: {
                break;
            }
            case R.id.share_container: {
                break;
            }
            case R.id.promo_container: {
                break;
            }
            case R.id.help_container: {
                break;
            }
            case R.id.about_container: {
                break;
            }
        }
    }

    // the meat of switching the above fragment
    private void switchFragment(Fragment fragment) {
        Log.d(TAG, "switchFragment");
        if (getActivity() == null)
            return;
        
        if (getActivity() instanceof MobileActivity) {
            MobileActivity ma = (MobileActivity) getActivity();
            ma.switchContent(fragment);
        }
    }
}
