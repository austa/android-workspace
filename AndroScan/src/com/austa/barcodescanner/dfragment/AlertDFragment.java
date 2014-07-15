package com.austa.barcodescanner.dfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import com.austa.barcodescanner.R;

public class AlertDFragment extends DialogFragment {
    //private List<ProductPropertyClass> results;
    //ImageSearchResultAdapter imageView;

    //    @Override
    //    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //        View rootView = inflater.inflate(R.layout.dialogfragment, container, false);
    //        return rootView;
    //    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.androidhappy).setTitle("Ürün Özellikleri")

        // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "asdasd", Toast.LENGTH_LONG).show();
                    }
                })

                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something else
                    }
                }).create();
    }

}