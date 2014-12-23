package rosemak.casestudy3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Steven ROseman
 */
public class SettingsPreferenceFragment extends PreferenceFragment {
    public static final String TAG = "SettingsPreferenceFragment";


    SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Preference wifiPreference = findPreference("pref_wifi");
        wifiPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {


            @Override
            public boolean onPreferenceClick(Preference preference) {


                SharedPreferences.Editor editor = settings.edit();



                boolean boolData = settings.getBoolean("pref_wifi", true);
                if (boolData) {


                }


                Log.i(TAG, "Bool= " + boolData);

                editor.putBoolean("pref_wifi", !(boolData));
                editor.apply();



                return false;
            }
        });




    }

}
