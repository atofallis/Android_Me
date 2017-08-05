package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    public static final String BUNDLE_HEAD_INDEX = "head_index";
    public static final String BUNDLE_BODY_INDEX = "body_index";
    public static final String BUNDLE_LEG_INDEX = "leg_index";

    private int mHeadIndex, mBodyIndex, mLegIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTwoPane = (findViewById(R.id.android_me_linear_layout) != null);
        if(mTwoPane) {
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.master_list_grid_view);
            gridView.setNumColumns(2);

            AndroidMeActivity.configureBodyParts(savedInstanceState, this);
        } else {
            // initialise next button click listener with defaults
            configureNextButton();
        }
    }

    @Override
    public void onImageSelected(int position) {

        int bodyPartType = (position / 12);

        if(mTwoPane) {
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartType) {
                case 0:
                    newFragment.setBodyPartsList(AndroidImageAssets.getHeads());
                    newFragment.setBodyPartIndex(position);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setBodyPartsList(AndroidImageAssets.getBodies());
                    newFragment.setBodyPartIndex(position - 12);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setBodyPartsList(AndroidImageAssets.getLegs());
                    newFragment.setBodyPartIndex(position - 24);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legs_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            String clickDetails = "";
            switch (bodyPartType) {
                case 0:
                    mHeadIndex = position;
                    clickDetails = " HEAD @ " + mHeadIndex;
                    break;
                case 1:
                    mBodyIndex = position - 12;
                    clickDetails = " BODY @ " + mBodyIndex;
                    break;
                case 2:
                    mLegIndex = position - 24;
                    clickDetails = " LEGS @ " + mLegIndex;
                    break;
                default:
                    clickDetails = " unexpected case: " + bodyPartType;
            }
            Toast.makeText(this, "Pos: " + position + " = " + clickDetails, Toast.LENGTH_SHORT).show();
            configureNextButton();
        }
    }

    private void configureNextButton() {
        Bundle b = new Bundle();
        b.putInt(BUNDLE_HEAD_INDEX, mHeadIndex);
        b.putInt(BUNDLE_BODY_INDEX, mBodyIndex);
        b.putInt(BUNDLE_LEG_INDEX, mLegIndex);
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
