package com.prashant.androidcodingexercise.data;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.prashant.androidcodingexercise.R;
import com.squareup.picasso.Picasso;

public class FactModel {
    private String title;
    private String description;
    private String imageHref;

    @BindingAdapter({"android:src"})
    public static void showImage(ImageView view, String url) {
        if (url == null || url.isEmpty()) {
            view.setImageResource(R.drawable.placeholder_image);
        } else {

            Picasso.get().load(url).error(R.drawable.error_placeholder).placeholder(R.drawable.placeholder_image).into(view);
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
