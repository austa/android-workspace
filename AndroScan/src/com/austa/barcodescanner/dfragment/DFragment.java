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
import android.widget.Toast;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.austa.barcodescanner.R;
import com.austa.barcodescanner.adapter.VolleyCaptechApplication;
import com.austa.barcodescanner.db.DatabaseApp;
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
    private DatabaseApp database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dialogfragment, container, false);
        imageloader = ((VolleyCaptechApplication) rootView.getContext().getApplicationContext()).getImageLoader();
        getDialog().setTitle(mProduct.getProductName());

        //progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        database = new DatabaseApp(mProduct.getProductName(), "bbb", "ccc");
        btnSaveProduct = (Button) rootView.findViewById(R.id.saveBtn);
        btnUpProduct = (Button) rootView.findViewById(R.id.upBtn);
        iv = (NetworkImageView) rootView.findViewById(R.id.resultImage);
        tvProductInformation = (TextView) rootView.findViewById(R.id.tvProductInformation);

        btnSaveProduct.setOnClickListener(this);
        btnUpProduct.setOnClickListener(this);

        iv.setImageUrl(mProduct.getImageUrl(), imageloader);
        tvProductInformation.setText(mProduct.getProductName());

        return rootView;
    }

    public void setData(ProductPropertyClass product) {
        mProduct = product;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.saveBtn:
                btnSaveProduct.setEnabled(false);
                //progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Kaydet tuşuna basıldı", Toast.LENGTH_LONG).show();
                database.executeInsert(getActivity());
                database.executeGetList(getActivity());
                database.executeProductList();

                //progressBar.setVisibility(View.GONE);
                btnSaveProduct.setEnabled(true);
                break;

            case R.id.upBtn:
                btnUpProduct.setEnabled(false);
                //progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "İptal tuşuna basıldı", Toast.LENGTH_LONG).show();
                //progressBar.setVisibility(View.GONE);
                btnUpProduct.setEnabled(true);
                break;
        }

    }
}