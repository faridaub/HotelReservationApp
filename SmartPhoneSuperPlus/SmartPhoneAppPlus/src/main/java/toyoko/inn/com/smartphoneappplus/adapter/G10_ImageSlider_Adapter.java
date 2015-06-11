package toyoko.inn.com.smartphoneappplus.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import toyoko.inn.com.smartphoneappplus.R;

/**
 * Created by Farid on 2014/11/07.
 */
public class G10_ImageSlider_Adapter extends PagerAdapter {
    private Context context;
    private static int[] imageId = {R.drawable.image1, R.drawable.image2};

    public G10_ImageSlider_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.g10_image_slide_view_item, null, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.g10_image_slideview);
        imageView.setImageResource(imageId[position]);
        container.addView(viewItem);
        return viewItem;
    }

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //Log.e("Position", String.valueOf(position));
        ((ViewPager) container).removeView((View) object);
    }
}



