package com.austa.barcodescanner.adapter;

import java.util.List;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.austa.barcodescanner.R;
import com.austa.barcodescanner.gson.parse.ProductPropertyClass;

public class ImageSearchResultAdapter extends BaseAdapter {
    private Context context;
    private List<ProductPropertyClass> results;
    private ImageLoader loader;

    public ImageSearchResultAdapter(Context context, List<ProductPropertyClass> results) {
        this.context = context;
        this.results = results;
        loader = ((VolleyCaptechApplication) context.getApplicationContext()).getImageLoader();
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.image_search_result_element, null, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.resultTitle);
            holder.content = (TextView) view.findViewById(R.id.resultContent);
            holder.resultImage = (NetworkImageView) view.findViewById(R.id.resultImage);
            view.setTag(holder);
        }
        ProductPropertyClass selectedResult = (ProductPropertyClass) getItem(position);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title.setText(selectedResult.getProductName());
        holder.content.setText(Html.fromHtml(selectedResult.getProductPrice()));
        holder.resultImage.setImageUrl(selectedResult.getImageUrl(), loader);

        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView content;
        NetworkImageView resultImage;
    }

}
