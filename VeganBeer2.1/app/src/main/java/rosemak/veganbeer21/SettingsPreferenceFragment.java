package rosemak.veganbeer21;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by stevierose on 11/8/14.
 */
public class SettingsPreferenceFragment extends PreferenceFragment {

    public static final String TAG = "SETTINGSPREFERENCEFRAG.TAG";
    public static final String USERWIFI = "pref_wifi";


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

            Main main = new Main();

            @Override
            public boolean onPreferenceClick(Preference preference) {


                SharedPreferences.Editor editor = settings.edit();



                    boolean boolData = settings.getBoolean("pref_wifi", true);
                        if (boolData) {

                            main.beerExecution();
                        }


                    Log.i(TAG, "Bool= " + boolData);

                    editor.putBoolean("pref_wifi", !(boolData));
                    editor.apply();



                return false;
            }
        });



        final Preference cachePreference = findPreference("pref_cache");
        cachePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences.Editor editor = settings.edit();

                boolean boolData = settings.getBoolean("pref_cache", true);

                Log.i(TAG, "cache= " +boolData);

                editor.putBoolean("pref_cache", !(boolData));
                if (!(boolData)) {
                    Log.i(TAG, "CLEAR DATA");
                    editor.clear();
                    editor.apply();
                }
                editor.apply();
                return false;
            }
        });
    }



}
