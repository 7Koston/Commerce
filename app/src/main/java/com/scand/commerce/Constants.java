package com.scand.commerce;

import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class Constants {

    public final static String USER_AGENT = "Mozilla/5.0 (Android " + Build.VERSION.SDK_INT
            + "; Commerce " + BuildConfig.VERSION_NAME + "; " + Build.DEVICE + ")";
    public final static int REQUEST_WRITE_STORAGE = 112;
    public final static int REQUEST_READ_WRITE_STORAGE = 114;
    private static final Locale LOCALE = new Locale("ru", "RU", "Cyrl");
    public final static SimpleDateFormat ADAPTER_DATE_FORMAT = new
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", LOCALE);
    public final static SimpleDateFormat DAY_DATE_FORMAT = new
            SimpleDateFormat("dd MMMM yyyy", LOCALE);
    public final static SimpleDateFormat TIME_DATE_FORMAT = new
            SimpleDateFormat("HH:mm", LOCALE);
    public final static SimpleDateFormat DAY_TIME_DATE_FORMAT = new
            SimpleDateFormat("dd.MM.yyyy HH:mm", LOCALE);

    private Constants() {
    }
}
