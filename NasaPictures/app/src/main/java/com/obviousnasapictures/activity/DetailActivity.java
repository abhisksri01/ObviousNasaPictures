package com.obviousnasapictures.activity;

import static com.obviousnasapictures.BuildConfig.BUNDLE;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.obviousnasapictures.R;
import com.obviousnasapictures.adapter.DetailsAdapter;
import com.obviousnasapictures.constant.BaseActivity;
import com.obviousnasapictures.constant.Const;
import com.obviousnasapictures.databinding.DetailsActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {

    DetailsActivityBinding binding;
    int dotCount = 0;
    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getBundleExtra(BUNDLE);

        binding.toolbar.imgBackkkkk.setOnClickListener(view -> onBackPressed());

        DetailsAdapter detailsAdapter = new DetailsAdapter(Const.galleryData.getData(), context);
        binding.viewPager.setAdapter(detailsAdapter);

        dotCount = detailsAdapter.getCount();
        List<ImageView> imageViews = new ArrayList<>();

        for (int i = 0; i < dotCount; i++) {
            ImageView imageView = new ImageView(context);
            imageViews.add(i, imageView);
            imageViews.get(i).setImageResource(R.drawable.inactive_dot);
            imageViews.get(i).setPadding(2, 2, 2, 2);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 0, 0); // setting the margin in the layout
            imageView.setLayoutParams(params);
            binding.sliderDots.addView(imageViews.get(i));
        }

        imageViews.get(bundle.getInt("position")).setImageResource(R.drawable.active_dot);
        binding.viewPager.setCurrentItem(bundle.getInt("position"));

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotCount; i++) {
                    imageViews.get(i).setImageResource(R.drawable.inactive_dot);
                }
                imageViews.get(position).setImageResource(R.drawable.active_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
