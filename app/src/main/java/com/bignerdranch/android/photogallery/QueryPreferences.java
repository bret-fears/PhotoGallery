package com.bignerdranch.android.photogallery;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by bretfears on 11/28/16.
 */

public class QueryPreferences {

    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String PREF_LAST_RESULT_ID = "lastResultId";
    private static final String PREF_LAST_PAGE_NUMBER = "lastPageNumber";
    private static final String PREF_IS_ALARM_ON = "isAlarmOn";

    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }

    public static String getLastResultId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_LAST_RESULT_ID, null);
    }

    public static void setLastResultId(Context context, String lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_LAST_RESULT_ID, lastResultId)
                .apply();
    }

    public static int getLastPageNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREF_LAST_PAGE_NUMBER, 1);
    }

    public static void setPrefLastPageNumber(Context context, int lastPageNumber) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_LAST_PAGE_NUMBER, lastPageNumber)
                .apply();
    }

    public static boolean isAlarmOn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_IS_ALARM_ON, false);
    }

    public static void setIsAlarmOn(Context context, boolean isAlarmOn) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_IS_ALARM_ON, isAlarmOn)
                .apply();
    }
}
