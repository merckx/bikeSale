package com.bikesale.dialogs;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddBikePartDialog extends DialogFragment
{


    @Override
    public Dialog getDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Hello");
        return builder.create();
    }
}
