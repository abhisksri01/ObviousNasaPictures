package com.obviousnasapictures.constant;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = "com.vhnd";

    private static final String ANDROID_ID = "android_id";

    private static final String DISPLAY_NAME = "display_name";
    private static final String USER_EMAIL = "user_email";
    private static final String LOGIN_ID = "login_id";
    private static final String USER_ID = "user_id";
    private static final String USER_MOBILE = "user_mobile";
    private static final String USER_TYPE = "user_type";
    private static final String IS_ACTIVE = "is_active";
    private static final String IS_DATA_FETCHED = "is_data_fetched";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_DISTRICT = "user_district";
    private static final String USER_DISTRICTId = "user_district_Id";
    private static final String USER_TEHSIL = "user_tehsil";
    private static final String USER_BLOCK = "user_block";
    private static final String USER_BLOCKID = "user_blockId";
    private static final String USER_DESIGNATION = "user_designation";
    private static final String USER_ORGANIZATION = "user_organization";
    private static final String USER_EHRMS_CODE = "user_ehrms_code";
    private static final String USER_FACILITY_ID = "user_facility_id";
    private static final String USER_FACILITY = "user_facility";
    private static final String USER_FACILITY_SESSION = "user_facility_session";
    private static final String USER_VILLAGE_ID = "user_village_id";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getUSER_DISTRICT() {
        return mPref.getString(USER_DISTRICT, "");
    }

    public void setUSER_DISTRICT(String user_password) {
        mPref.edit().putString(USER_DISTRICT, user_password).apply();
    }

    public String getUSER_DISTRICTId() {
        return mPref.getString(USER_DISTRICTId, "");
    }

    public void setUSER_DISTRICTId(String user_password) {
        mPref.edit().putString(USER_DISTRICTId, user_password).apply();
    }

    public int getUSER_FACILITY_ID() {
        return mPref.getInt(USER_FACILITY_ID, 0);
    }

    public void setUSER_FACILITY_ID(int user_password) {
        mPref.edit().putInt(USER_FACILITY_ID, user_password).apply();
    }

    public int getUSER_FACILITY_SESSION() {
        return mPref.getInt(USER_FACILITY_SESSION, 0);
    }

    public void setUSER_FACILITY_SESSION(int user_facility_session) {
        mPref.edit().putInt(USER_FACILITY_SESSION, user_facility_session).apply();
    }

    public String getUSER_FACILITY() {
        return mPref.getString(USER_FACILITY, "");
    }

    public void setUSER_VILLAGE_ID(int user_password) {
        mPref.edit().putInt(USER_VILLAGE_ID, user_password).apply();
    }

    public int getUSER_VILLAGE_ID() {
        return mPref.getInt(USER_VILLAGE_ID, 0);
    }

    public void setUSER_FACILITY(String user_password) {
        mPref.edit().putString(USER_FACILITY, user_password).apply();
    }

    public String getUSER_TEHSIL() {
        return mPref.getString(USER_TEHSIL, "");
    }

    public void setUSER_TEHSIL(String user_password) {
        mPref.edit().putString(USER_TEHSIL, user_password).apply();
    }

    public String getUSER_BLOCK() {
        return mPref.getString(USER_BLOCK, "");
    }

    public void setUSER_BLOCK(String user_password) {
        mPref.edit().putString(USER_BLOCK, user_password).apply();
    }

    public String getUserBlockid() {
        return mPref.getString(USER_BLOCKID, "");
    }

    public void setUserBlockid(String user_password) {
        mPref.edit().putString(USER_BLOCKID, user_password).apply();
    }

    public String getUSER_DESIGNATION() {
        return mPref.getString(USER_DESIGNATION, "");
    }

    public void setUSER_DESIGNATION(String user_password) {
        mPref.edit().putString(USER_DESIGNATION, user_password).apply();
    }

    public String getUSER_ORGANIZATION() {
        return mPref.getString(USER_ORGANIZATION, "");
    }

    public void setUSER_ORGANIZATION(String user_password) {
        mPref.edit().putString(USER_ORGANIZATION, user_password).apply();
    }

    public String getUSER_EHRMS_CODE() {
        return mPref.getString(USER_EHRMS_CODE, "");
    }

    public void setUSER_EHRMS_CODE(String user_password) {
        mPref.edit().putString(USER_EHRMS_CODE, user_password).apply();
    }


    //for fragment
    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    //for getting instance
    public static synchronized PreferencesManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }

    public boolean clear() {
        return mPref.edit().clear().commit();
    }

    public String getUSER_PASSWORD() {
        return mPref.getString(USER_PASSWORD, "");
    }

    public void setUSER_PASSWORD(String user_password) {
        mPref.edit().putString(USER_PASSWORD, user_password).apply();
    }

    public String getANDROID_ID() {
        return mPref.getString(ANDROID_ID, "");
    }

    public void setANDROID_ID(String value) {
        mPref.edit().putString(ANDROID_ID, value).apply();
    }

    public String getDISPLAY_NAME() {
        return mPref.getString(DISPLAY_NAME, "");
    }

    public void setDISPLAY_NAME(String display_name) {
        mPref.edit().putString(DISPLAY_NAME, display_name).apply();
    }

    public String getUSER_EMAIL() {
        return mPref.getString(USER_EMAIL, "");
    }

    public void setUSER_EMAIL(String email) {
        mPref.edit().putString(USER_EMAIL, email).apply();
    }

    public String getLOGIN_ID() {
        return mPref.getString(LOGIN_ID, "");
    }

    public void setLOGIN_ID(String login_id) {
        mPref.edit().putString(LOGIN_ID, login_id).apply();
    }

    public int getUSER_ID() {
        return mPref.getInt(USER_ID, 0);
    }

    public void setUSER_ID(int user_id) {
        mPref.edit().putInt(USER_ID, user_id).apply();
    }

    public String getUSER_MOBILE() {
        return mPref.getString(USER_MOBILE, "");
    }

    public void setUSER_MOBILE(String user_mobile) {
        mPref.edit().putString(USER_MOBILE, user_mobile).apply();
    }

    public String getUSER_TYPE() {
        return mPref.getString(USER_TYPE, "");
    }

    public void setUSER_TYPE(String user_type) {
        mPref.edit().putString(USER_TYPE, user_type).apply();
    }


    public boolean getIS_ACTIVE() {
        return mPref.getBoolean(IS_ACTIVE, false);
    }

    public void setIS_ACTIVE(boolean is_active) {
        mPref.edit().putBoolean(IS_ACTIVE, is_active).apply();
    }

    public boolean getIS_DATA_FETCHED() {
        return mPref.getBoolean(IS_DATA_FETCHED, false);
    }

    public void setIS_DATA_FETCHED(boolean is_active) {
        mPref.edit().putBoolean(IS_DATA_FETCHED, is_active).apply();
    }

}
