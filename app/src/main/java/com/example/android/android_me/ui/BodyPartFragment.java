package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;


public class BodyPartFragment extends Fragment {

    @NonNull
    private List<Integer> mBodyPartsList = null;
    private int mBodyPartIndex;

    private static final String TAG = BodyPartFragment.class.getName();
    private static final String BUNDLE_BODY_PART_INDEX = "body_part_index";
    private static final String BUNDLE_BODY_PART_LIST = "body_part_list";

//    public BodyPartFragment()  {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        if(savedInstanceState != null) {
            mBodyPartIndex = savedInstanceState.getInt(BUNDLE_BODY_PART_INDEX);
            mBodyPartsList = savedInstanceState.getIntegerArrayList(BUNDLE_BODY_PART_LIST);
        }
        final ImageView img = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if(mBodyPartsList == null || mBodyPartsList.size() == 0 || mBodyPartIndex >= mBodyPartsList.size()) {
            Log.e(TAG, "mBodyPartsList not correctly initialised");
        } else {
            img.setImageResource(mBodyPartsList.get(mBodyPartIndex));

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBodyPartIndex < mBodyPartsList.size()-1) {
                        mBodyPartIndex++;
                    } else {
                        mBodyPartIndex =0;
                    }
                    img.setImageResource(mBodyPartsList.get(mBodyPartIndex));
                }
            });
        }
        return rootView;
    }

    public void setBodyPartsList(List<Integer> bodyPartsList) {
        mBodyPartsList = bodyPartsList;
    }

    public void setBodyPartIndex(int bodyPartIndex) {
        mBodyPartIndex = bodyPartIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_BODY_PART_INDEX, mBodyPartIndex);
        outState.putIntegerArrayList(BUNDLE_BODY_PART_LIST, (ArrayList<Integer>) mBodyPartsList);
    }
}
