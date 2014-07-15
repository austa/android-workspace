package com.austa.barcodescanner.dfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.austa.barcodescanner.R;
import com.austa.barcodescanner.adapter.VolleyCaptechApplication;
import com.austa.barcodescanner.gson.parse.ProductPropertyClass;

public class DFragment extends DialogFragment {

    private ProductPropertyClass mProduct;
    private ImageLoader imageloader;
    private NetworkImageView iv;
    private TextView productInformation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialogfragment, container, false);
        imageloader = ((VolleyCaptechApplication) rootView.getContext().getApplicationContext()).getImageLoader();
        getDialog().setTitle(mProduct.getProductName());

        iv = (NetworkImageView) rootView.findViewById(R.id.resultImage);
        iv.setImageUrl(mProduct.getImageUrl(), imageloader);

        productInformation = (TextView) rootView.findViewById(R.id.tvProductInformation);
        productInformation.setText(mProduct.toString());

        return rootView;
    }

    //    @Override
    //    public Dialog onCreateDialog(Bundle savedInstanceState) {
    //        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setIcon(R.drawable.androidhappy)
    //
    //        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
    //            public void onClick(DialogInterface dialog, int which) {
    //                Toast.makeText(getActivity(), "asdasd", Toast.LENGTH_LONG).show();
    //            }
    //        })
    //
    //        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    //            public void onClick(DialogInterface dialog, int which) {
    //
    //            }
    //        });
    //
    //        return builder.create();
    //    }

    public void setData(ProductPropertyClass product) {
        mProduct = product;
    }
}