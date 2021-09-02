package src.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class RecentFilesUtil {
    public static Preferences recentPreferences = Preferences.userRoot().node("/RecentFiles");

    public static List<String> recentFilesList;

    static {
        recentFilesList = new ArrayList<String>();
        try {
            updateList();
        } catch (BackingStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void updateList() throws BackingStoreException {
        String[] keys = recentPreferences.keys();

        Arrays.sort(keys);

        for (int i = 0; i < keys.length; i++) {
            recentFilesList.add(recentPreferences.get(keys[i], "file not found"));
        }
    }

    public static void putRecentFile(String filePath) {
        if (recentFilesList.size() > 0) {
            if (filePath != recentFilesList.get(0)) {
                if (recentFilesList.size() == 1) {
                    recentPreferences.put("1", recentPreferences.get("0", "not found"));
                } else {
                    int c = 0;
                    while (c < recentFilesList.size() && c < 4) {
                        recentPreferences.put(Integer.toString(c + 1), recentFilesList.get(c));
                        c++;
                    }
                }

                recentPreferences.put("0", filePath);

                try {
                    updateList();
                } catch (BackingStoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            recentPreferences.put("0", filePath);

            try {
                updateList();
            } catch (BackingStoreException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}