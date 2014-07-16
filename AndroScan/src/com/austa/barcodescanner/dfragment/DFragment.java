package com.austa.barcodescanner.dfragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.austa.barcodescanner.R;
import com.austa.barcodescanner.adapter.VolleyCaptechApplication;
import com.austa.barcodescanner.gson.parse.ProductPropertyClass;

public class DFragment extends DialogFragment implements
        OnClickListener {

    private ProductPropertyClass mProduct;
    private ProgressBar progressBar;
    private ImageLoader imageloader;
    private NetworkImageView iv;
    private TextView tvProductInformation;
    private Button btnSaveProduct;
    private Button btnUpProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialogfragment, container, false);
        imageloader = ((VolleyCaptechApplication) rootView.getContext().getApplicationContext()).getImageLoader();
        getDialog().setTitle(mProduct.getProductName());

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        btnSaveProduct = (Button) rootView.findViewById(R.id.saveBtn);
        btnUpProduct = (Button) rootView.findViewById(R.id.upBtn);
        iv = (NetworkImageView) rootView.findViewById(R.id.resultImage);
        tvProductInformation = (TextView) rootView.findViewById(R.id.tvProductInformation);

        btnSaveProduct.setOnClickListener(this);
        btnUpProduct.setOnClickListener(this);

        iv.setImageUrl(mProduct.getImageUrl(), imageloader);
        tvProductInformation.setText(mProduct.toString());

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.saveBtn:
                btnSaveProduct.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                /*
                 * Veritabanı ekleme işlemeleri buraya gelecek
                 */
                progressBar.setVisibility(View.GONE);
                btnSaveProduct.setEnabled(true);
                break;

            case R.id.upBtn:
                btnUpProduct.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                /*
                 * Buraya ise daha sonra düşünülecek bir buton işlevi gelecek
                 */
                progressBar.setVisibility(View.GONE);
                btnUpProduct.setEnabled(true);
                break;
        }

    }
}