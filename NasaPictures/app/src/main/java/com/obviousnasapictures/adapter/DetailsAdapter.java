package com.obviousnasapictures.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.obviousnasapictures.R;
import com.obviousnasapictures.model.DataItem;
import com.obviousnasapictures.util.Utils;

import java.util.List;

public class DetailsAdapter extends PagerAdapter {

    private List<DataItem> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public DetailsAdapter(List<DataItem> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_details, container, false);

        ImageView imageView;
        TextView title, desc, date, copyright;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.txt_des);
        date = view.findViewById(R.id.tv_date);
        copyright = view.findViewById(R.id.tv_copyright);

        Glide.with(context).load(models.get(position).getHdurl())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
                        .placeholder(R.drawable.image_not_available).error(R.drawable.image_not_available)).into(imageView);

        title.setText(Utils.getDefaultValue(models.get(position).getTitle(), ""));
        desc.setText(Utils.getDefaultValue(models.get(position).getExplanation(), ""));
        copyright.setText(Utils.getDefaultValue(models.get(position).getCopyright(), ""));
        date.setText(Utils.getDefaultValue(models.get(position).getDate(), ""));

        if (!(TextUtils.isEmpty(models.get(position).getCopyright())) && models.get(position).getCopyright() != null && models.get(position).getCopyright().length() > 0)
            copyright.setVisibility(View.VISIBLE);
        else copyright.setVisibility(View.GONE);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}