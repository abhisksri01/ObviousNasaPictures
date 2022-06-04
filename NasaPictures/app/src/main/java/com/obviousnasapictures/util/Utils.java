package com.obviousnasapictures.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static DecimalFormat format = new DecimalFormat("0");

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            View v = activity.getCurrentFocus();
            if (v != null) {
                IBinder binder = activity.getCurrentFocus()
                        .getWindowToken();
                if (binder != null) {
                    inputMethodManager.hideSoftInputFromWindow(binder, 0);
                }
            }
        }
    }

    public static String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static String getMonthDate(String dateTime) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            try {
                myDate = dateFormat.parse(dateTime);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMM,dd", Locale.getDefault());
        return timeFormat.format(myDate);
    }

    public static String changeDateFormatDDMMYYYY(int day, int month, int year, String format) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(c.getTime());
    }

    public static String getDay(String dateTime, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            try {
                myDate = dateFormat.parse(dateTime);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        return timeFormat.format(myDate);
    }

    public static boolean isEmailAddress(String text) {
        String pattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        return text.matches(pattern);
    }


    public static boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    public static String getTimeDifference(String dateStr1, String dateStr2, String format) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(format, Locale.getDefault());
        try {

            Date date1 = simpleDateFormat.parse(dateStr1);
            Date date2 = simpleDateFormat.parse(dateStr2);

            long different = date2.getTime() - date1.getTime();
            return GetFormattedInterval(different);

        } catch (ParseException e) {
            simpleDateFormat =
                    new SimpleDateFormat(format, Locale.getDefault());
            try {

                Date date1 = simpleDateFormat.parse(dateStr1);
                Date date2 = simpleDateFormat.parse(dateStr2);

                long different = date2.getTime() - date1.getTime();
                return GetFormattedInterval(different);

            } catch (ParseException e1) {
                e1.printStackTrace();
            }

        }
        return "";
    }

    public static String changeDateFormatTravel(int day, int month, int year, String format) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(c.getTime());
    }


    private static String GetFormattedInterval(final long ms) {
        long millis = ms % 1000;
        long x = ms / 1000;
        long seconds = x % 60;

        x /= 60;
        long minutes = x % 60;
        x /= 60;
        long hours = x % 24;

        return String.format(Locale.getDefault(), "%dh %dm", hours, minutes);
    }

    public static String getDefaultValue(String value, String defaultValue) {
        return (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null")) ?
                defaultValue : value;
    }

    public static String getRemoveT(String value) {
        if (value.contains("\t")) {
            return value.replaceAll("\t", "");
        } else {
            return value;
        }
    }

    public static String getDefaultValueNA(String value, String defaultValue) {
        return (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("Na")) ?
                defaultValue : value;
    }

    public static void dialPhoneNumber(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static long convertIntoMilliSeconds(String time) {
        String[] tokens = time.split(":");
        int secondsToMs = Integer.parseInt(tokens[2]) * 1000;
        int minutesToMs = Integer.parseInt(tokens[1]) * 60000;
        int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
        long total = secondsToMs + minutesToMs + hoursToMs;
        return total;
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static String changeDateFormat(String date, String fromFormat, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat, Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);
            SimpleDateFormat timeFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            return timeFormat.format(myDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTodayDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static int getCountOfDays(String createdDateString, String expireDateString, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        Date createdConvertedDate = null;
        Date expireCovertedDate = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar start = new GregorianCalendar();
        start.setTime(createdConvertedDate);

        Calendar end = new GregorianCalendar();
        end.setTime(expireCovertedDate);
        long diff = end.getTimeInMillis() - start.getTimeInMillis();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return (int) (dayCount);
    }

    public static Date getTodayDateTime(long miliSecond) {
        return new Date(miliSecond);
    }


    public static boolean validatePan(String panNo) {

        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]");
        Matcher matcher = pattern.matcher(panNo);

        return matcher.matches();
    }

    public static String replaceBackSlash(String message) {
        //Log.e("==========Final Result ", message.replaceAll("\\\\", ""));
        return message.replaceAll("\\\\", "");
    }


    public static String getReplacePhoneNumber(String number) {
        String newNumber = "";
        if (number.length() > 10) {
            if (number.startsWith("+91")) {
                newNumber = number.replaceFirst("\\+91", "");
                Log.e("-------->+91", newNumber);
            } else if (number.startsWith("91")) {
                newNumber = number.replaceFirst("91", "");
                Log.e("=======> 91", newNumber);
            } else if (number.startsWith("0")) {
                newNumber = number.replaceFirst("0", "");
                Log.e(">>>>>>>>>>0", newNumber);
            }
            return newNumber;
        }
        return number;
    }

    public static String getValue(double amount) {
        return format.format(amount);
    }

    public static String getColoredSpanned(String text, String color) {
        return "<font color=" + color + ">" + text + "</font>";
    }

    public static List<String> getDayList(String s) {
        if (s.equalsIgnoreCase("Monday")) {
            return Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        } else if (s.equalsIgnoreCase("Tuesday")) {
            return Arrays.asList("Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday");

        } else if (s.equalsIgnoreCase("Wednesday")) {
            return Arrays.asList("Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday");

        } else if (s.equalsIgnoreCase("Thursday")) {
            return Arrays.asList("Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday");

        } else if (s.equalsIgnoreCase("Friday")) {
            return Arrays.asList("Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday");

        } else if (s.equalsIgnoreCase("Saturday")) {
            return Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");

        } else if (s.equalsIgnoreCase("Sunday")) {
            return Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        }
        return null;
    }


    public static String AssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    public static String getDateTimeFromTimeStamp(Long time, String mDateFormat) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(mDateFormat, Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getDefault());
            Date dateTime = new Date(time);
            return dateFormat.format(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String[] getRemoveCommasfromstring(String string) {
        LoggerUtil.log("=====> " + string);
        return string.split(",");
    }

    public static void getImagesVisile(ImageView imgpic, TextView txtname, TextView txtvalue, boolean vis) {
        if (vis) {
            imgpic.setVisibility(View.VISIBLE);
            txtname.setVisibility(View.VISIBLE);/**/
            txtvalue.setVisibility(View.VISIBLE);
        } else {
            imgpic.setVisibility(View.GONE);
            txtname.setVisibility(View.GONE);
            txtvalue.setVisibility(View.GONE);
        }
    }


    public static boolean getVisibilityView(String value, View view) {
        if (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("0")) {
            view.setVisibility(View.VISIBLE);
//            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        return false;
    }

    public static boolean getVisibilityFac(String value, View view) {
        if (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("0")) {
//            view.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        return false;
    }

    public static void getVisibilityView_ViewGroup(String value, View view, ViewGroup viewGroup) {
        if (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("0")) {
            view.setVisibility(View.GONE);
            viewGroup.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
            viewGroup.setVisibility(View.VISIBLE);
        }
    }

    public static boolean getVisibilityView_boolean(String value, View view) {
        if (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("0")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean getVisibilityView_ViewGroup_boolean(String value) {
        if (value == null || value.equalsIgnoreCase("") || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("0")) {
            return true;
//            return false;
        } else {
            return true;
        }
    }

    /*public static BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public static BitmapDescriptor bitmapfromImage(Context context, Drawable drawable) {
        // below line is use to generate a drawable.

        // below line is use to set bounds to our vector drawable.
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        drawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }*/

    public static String getCompleteAddressString(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                LoggerUtil.log("Admin Area" + addresses.get(0).getAdminArea());
                LoggerUtil.log("Locality Area" + addresses.get(0).getLocality());
                LoggerUtil.log("India" + addresses.get(0).getCountryName());
                LoggerUtil.log("Pincode" + addresses.get(0).getPostalCode());
                LoggerUtil.log("ad2" + addresses.get(0).getSubLocality());


                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    LoggerUtil.log(i + " Address-" + returnedAddress.getAddressLine(i));
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",");
                }

                String[] arr = strReturnedAddress.toString().split(",");

                StringBuilder add = new StringBuilder("");
                for (int i = 0; i < arr.length; i++) {
                    if (i > 1) {
                        if (i == arr.length - 1) {
                            add.append(arr[i]).append(".");
                        } else {
                            add.append(arr[i]).append(",");
                        }

                    }
                }

                strAdd = add.toString();
                Log.e("My Current loction", add.toString());
            } else {
                Log.e("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("My Current loction", "Canont get Address!");
        }
        return strAdd;
    }


    public static String getCompleteAddress(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                LoggerUtil.log("Admin Area" + addresses.get(0).getAdminArea());
                LoggerUtil.log("Locality Area" + addresses.get(0).getLocality());
                LoggerUtil.log("India" + addresses.get(0).getCountryName());
                LoggerUtil.log("Pincode" + addresses.get(0).getPostalCode());
                LoggerUtil.log("ad2" + addresses.get(0).getSubLocality());


                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    LoggerUtil.log(i + " Address-" + returnedAddress.getAddressLine(i));
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",");
                }
                strAdd = String.valueOf(strReturnedAddress);
            } else {
                Log.e("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("My Current loction", "Canont get Address!");
        }
        return strAdd;
    }

    public static String getBuildingName(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "", str = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                LoggerUtil.log("Admin Area" + addresses.get(0).getAdminArea());
                LoggerUtil.log("Locality Area" + addresses.get(0).getLocality());
                LoggerUtil.log("India" + addresses.get(0).getCountryName());
                LoggerUtil.log("Pincode" + addresses.get(0).getPostalCode());
                LoggerUtil.log("ad2" + addresses.get(0).getSubLocality());

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    LoggerUtil.log(i + " Address-" + returnedAddress.getAddressLine(i));
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",");
                }
                String[] strArr = strReturnedAddress.toString().split(",");
                StringBuilder add = new StringBuilder("");
                for (int i = 0; i < strArr.length; i++) {
                    if (i < 2) {
                        add.append(strArr[i]).append(",");
                    }
                }
                Log.e("My Current loction", add.toString());
                str = add.toString();
            } else {
                Log.e("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("My Current loction", "Canont get Address!");
        }
        return str;
    }


    public static String getState(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                LoggerUtil.log("State==> " + addresses.get(0).getAdminArea());
                strAdd = addresses.get(0).getAdminArea();
            } else {
                Log.e("State==> ", "No State Returened");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("State==> ", "No State Returened");
        }
        return strAdd;
    }


    public static String getCity(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {
                LoggerUtil.log("City==> " + addresses.get(0).getLocality());

                strAdd = addresses.get(0).getLocality();
            } else {
                Log.e("City==> ", "No City Returened");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("City==> ", "No City Returened");
        }
        return strAdd;
    }


    public static String getAllDetailsCity(Context context, double LATITUDE, double LONGITUDE, TextInputEditText edtcity, TextInputEditText edtaddress,
                                           TextInputEditText edtpincode, TextInputEditText edtcentername) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                LoggerUtil.log("Admin Area" + addresses.get(0).getAdminArea());
                LoggerUtil.log("Locality Area" + addresses.get(0).getLocality());
                LoggerUtil.log("India" + addresses.get(0).getCountryName());
                LoggerUtil.log("Pincode" + addresses.get(0).getPostalCode());
                LoggerUtil.log("ad2" + addresses.get(0).getSubLocality());


                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",");
                }

                String[] strArr = strReturnedAddress.toString().split(",");
                StringBuilder add = new StringBuilder("");
                for (int i = 0; i < strArr.length; i++) {
                    if (i < 2) {
                        add.append(strArr[i]).append(",");
                    }
                }

                edtcentername.setText(add.toString());
                strAdd = strReturnedAddress.toString();
                edtcity.setText(addresses.get(0).getLocality());
                edtaddress.setText(strAdd);
                edtpincode.setText(addresses.get(0).getPostalCode());

                Log.e("My Current loction", strReturnedAddress.toString());
            } else {
                Log.e("My Current loction", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("My Current loction", "Canont get Address!");
        }
        return strAdd;
    }


    public static String getPincode(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                LoggerUtil.log("Pincode==> " + addresses.get(0).getPostalCode());
                LoggerUtil.log("Dist==> " + addresses.get(0).getSubAdminArea());
                strAdd = addresses.get(0).getPostalCode();
            } else {
                Log.e("Pincode==> ", "No Pincode Returened");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Pincode==> ", "No Pincode Returened");
        }
        return strAdd;
    }

    public static String formatNumber(String number) {
        if (number.contains(",")) {
            return number;
        } else {
            return NumberFormat.getNumberInstance(Locale.getDefault()).format(Integer.parseInt(number));

        }
    }


/*//TODO Example Url for wms
    final String WMS_FORMAT_STRING =
            "http://65.1.34.117:8080/geoserver/wms" +
                    "?service=WMS" +
                    "&version=1.1.0" +
                    "&request=GetMap" +
                    "&layers=quaere:districts" +
                    "&bbox=%f,%f,%f,%f" +
                    "&width=256" +
                    "&height=256" +
                    "&srs=EPSG:900913" +  // NB This is important, other SRSs wont work.
                    "&format=image/png" +
                    "&transparent=true";
*/

    public static final String get_WMS_Url(String wmsurl, String service, String version, String request, String layer, String width, String height,
                                           String srs, boolean transparent) {
        String url = wmsurl + "?service=" + service + "&version=" + version + "&request=" + request + "&layers=" + layer + "&bbox=%f,%f,%f,%f" + "&width=" + width + "&height=" + height + "&srs=" + srs + "&format=image/png" + "&transparent=" + transparent;
        LoggerUtil.log("Created wms url===================> " + url);
        return url;
    }

    public static final String get_WMS_Url_CQL_FILTER(String wmsurl, String service, String version, String request, String layer, String width, String height,
                                                      String srs, boolean transparent) {
        String url = wmsurl + "?service=" + service + "&version=" + version + "&request=" + request + "&layers=" + layer + "&bbox=%f,%f,%f,%f" + "&width=" + width + "&height=" + height + "&srs=" + srs + "&format=image/png" + "&transparent=" + transparent + "&CQL_FILTER=";
        LoggerUtil.log("Created wms url with cqlfilter==============> " + url);
        return url;
    }

   /* public static String getLatLongBBox(GoogleMap googleMap) {
        String s = (googleMap.getProjection().getVisibleRegion().latLngBounds.southwest.latitude) + ","
                + (googleMap.getProjection().getVisibleRegion().latLngBounds.southwest.longitude) + "," +
                (googleMap.getProjection().getVisibleRegion().latLngBounds.northeast.latitude) + "," +
                (googleMap.getProjection().getVisibleRegion().latLngBounds.northeast.longitude);
        LoggerUtil.log("Calculated BBox===> " + s);
        return s;
    }


    private static LatLng fromLatLngToPoint(LatLng latLng, GoogleMap map) {
        Point topRight = map.getProjection().toScreenLocation(map.getProjection().getVisibleRegion().latLngBounds.northeast);
        Point bottomLeft = map.getProjection().toScreenLocation(map.getProjection().getVisibleRegion().latLngBounds.southwest);
        double scale = Math.pow(2, map.getCameraPosition().zoom);
        Projection projection = map.getProjection();
        Point point = projection.toScreenLocation(latLng);
        Point worldPoint = map.getProjection().toScreenLocation(map.getProjection().fromScreenLocation(point));

        return new LatLng((worldPoint.x - bottomLeft.x) * scale, (worldPoint.y - topRight.y) * scale);
    }


    public static double getRadius(GoogleMap map) {
        double radius_px = 0.40 / 2;
        double constMetersDegress = 1000; // TODO Verifiy

        //zoom <-> m/px = http://timwhitlock.info/blog/2010/04/google-maps-zoom-scales/
        double[] scaled_zooms = {30000, 21282, 16355, 10064, 5540, 2909, 1485, 752, 378, 190, 95, 48, 24, 12, 6, 3, 1.48, 0.74, 0.37, 0.19, 0.09, 0.04, 0.02};
        double radius_meters = radius_px * scaled_zooms[Math.round(map.getCameraPosition().zoom)];
        double radius_degrees = radius_meters / constMetersDegress;
        return radius_degrees;
    }

    public static String getFeatureInfoURL(LatLng latLng, GoogleMap map) {
        double lat = latLng.latitude;
        double lng = latLng.longitude;

        //console.info(------------------------------)
        double radius_degrees = getRadius(map);
        double buffer_sw_y_dd = lat - radius_degrees;
        double buffer_sw_x_dd = lng - radius_degrees;
        double buffer_ne_y_dd = lat + radius_degrees;
        double buffer_ne_x_dd = lng + radius_degrees;
        //console.info(bbox dd,buffer_sw_x_dd+,+buffer_sw_y_dd+,+buffer_ne_x_dd+,+buffer_ne_y_dd)

        LatLng buffer_sw_dd = new LatLng(buffer_sw_y_dd, buffer_sw_x_dd);//decimal degrees
        LatLng buffer_ne_dd = new LatLng(buffer_ne_y_dd, buffer_ne_x_dd);//decimal degrees

        LatLng buffer_sw_px = fromLatLngToPoint(buffer_sw_dd, map);//pixels
        LatLng buffer_ne_px = fromLatLngToPoint(buffer_ne_dd, map);//pixels
        //console.info(buffer_sw_px,buffer_sw_px,buffer_ne_px,buffer_ne_px)

        int buffer_width_px = (int) (Math.abs(buffer_ne_px.latitude - buffer_sw_px.latitude));
        int buffer_height_px = (int) (Math.abs(buffer_ne_px.longitude - buffer_sw_px.longitude));
        //console.info(buffer_width_px,buffer_width_px, buffer_height_px,buffer_height_px)

        int center_x_px = (int) ((buffer_width_px / 2));
        int center_y_px = (int) ((buffer_height_px / 2));
        //console.info(center_x_px,center_x_px,center_y_px,center_y_px)
        //console.info(------------------------------)


//        String url = baseUrl + ?;
        String url = "http://65.1.34.117:8080/geoserver/wms?";
        url += "SERVICE=WMS";
        url += "&VERSION=1.1.1";
        url += "&REQUEST=GetFeatureInfo";
        url += "&TRANSPARENT=true";
//        url += "&QUERY_LAYERS= + layerName";
        url += "&QUERY_LAYERS=quaere:divisions";
        url += "&STYLES=";
        url += "&LAYERS=quaere:divisions";
        url += "&INFO_FORMAT=application/json";
        url += "&SRS=EPSG:4326";
        url += "&FEATURE_COUNT=10";
        url += "&WIDTH=" + (int) (buffer_width_px);
        url += "&HEIGHT=" + (int) (buffer_height_px);
        url += "&Y=" + Math.abs(Math.round(center_y_px));
        url += "&X=" + Math.round(center_x_px);
        url += "&BBOX=" + buffer_sw_x_dd + "," + buffer_sw_y_dd + "," + buffer_ne_x_dd + "," + buffer_ne_y_dd;

        return url;
    }*/


    public static void visibility(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }

    public static void setGravity(TextView text, boolean end) {
        if (end) {
            text.setGravity(Gravity.END);
        } else {
            text.setGravity(Gravity.START);
        }

    }


    public static String[] getRemoveSemiColo(String string) {
        LoggerUtil.log("=====> " + string);
        return string.split(",");
    }


    public static void disableenableclick(View editText, boolean va) {
        editText.setFocusable(va);
        editText.setFocusableInTouchMode(va);
        if (editText instanceof EditText) {
            ((EditText) editText).setCursorVisible(false);
        }
        editText.setClickable(va);
    }

    public static void textvisible(TextView txtname, View txtvalue, boolean vis) {
        if (vis) {
            txtname.setVisibility(View.VISIBLE);
            txtvalue.setVisibility(View.VISIBLE);
        } else {
            txtname.setVisibility(View.GONE);
            txtvalue.setVisibility(View.GONE);
        }
    }

    public static String filterText(String string) {
        try {
            String mapTxet = "";
            LoggerUtil.log("strr---->MainStr==> " + string);
            String[] strList = Utils.getRemoveCommasfromstring(string);

            for (int i = 0; i < strList.length; i++) {
                String[] splitedString = strList[i].split(":");
                if (!splitedString[1].equalsIgnoreCase("0")) {
                    mapTxet = mapTxet + " " + splitedString[0] + "-" + splitedString[1];
                }
            }
            LoggerUtil.logItem("strr---->Final String===> " + mapTxet);


            return mapTxet;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void makeCall(Context context, String mobNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mobNumber));
        context.startActivity(intent);
    }

    public static Bitmap getBitmapFromLink(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                connection.connect();
            } catch (Exception e) {
                Log.v("asfwqeds", e.getMessage());
            }
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            Log.v("asfwqeds", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String toCamelCase(String s) {
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + " " + toProperCase(part);
        }
        return camelCaseString;
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
