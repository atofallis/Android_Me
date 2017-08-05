package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    public static final String BUNDLE_HEAD_INDEX = "head_index";
    public static final String BUNDLE_BODY_INDEX = "body_index";
    public static final String BUNDLE_LEG_INDEX = "leg_index";

    private int mHeadIndex, mBodyIndex, mLegIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {

        String clickDetails = "";
        int bodyPartType = (position / 12);
        switch(bodyPartType) {
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
