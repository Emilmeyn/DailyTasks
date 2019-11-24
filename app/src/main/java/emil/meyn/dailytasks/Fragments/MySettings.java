package emil.meyn.dailytasks.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import emil.meyn.dailytasks.R;

public class MySettings extends PreferenceFragmentCompat {
        @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
