package com.stockbook.ims.presentation.sellsman.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stockbook.ims.R;

/**
 * Created by Faizul Haque Nayan on 18/11/14.
 */

public class SellFragment extends Fragment {

    private View viewMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMain = inflater.inflate(R.layout.fragment_sell, container, false);
        return viewMain;
    }
}
