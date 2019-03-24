package com.example.linkdevmvvm.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.linkdevmvvm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by antonio on 1/16/19.
 */

public class Utils {

    public static void loadImageWithGlide(final Context context, final ImageView imageView, final String URL) {

        if (context == null || imageView == null || URL == null)
            return;//early if found one of them with null value
        Glide.with(context)
                .load(URL)
                .apply(new RequestOptions().error(R.drawable.placeholder).placeholder(R.drawable.placeholder))
                .into(imageView);


    }

    //this method instead of writing to every time...
    public static void showMessage(Context context, String content) {
        if (context == null || content == null)
            return;
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    //this method valid string and value can never be null
    public static String validString(String string) {
        if (string == null)
            return "";

        return string;

    }


    public static void openWebsiteOnBrowser(Application activity, String url) {
        if (url == null || url.equals(""))
            return;//we don't need to open website if this check is true

        Uri webpage = Uri.parse(url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://" + url);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } else {
            showMessage(activity, activity.getString(R.string.no_apps_can_resolve_the_intent));
        }
    }

    public static String parseDate(String oldDateFormat) {
        if (oldDateFormat == null)
            return "";
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);
        try {
            Date date = inputFormat.parse(oldDateFormat);
            return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

}
