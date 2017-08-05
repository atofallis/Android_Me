/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import static com.example.android.android_me.ui.MainActivity.BUNDLE_BODY_INDEX;
import static com.example.android.android_me.ui.MainActivity.BUNDLE_HEAD_INDEX;
import static com.example.android.android_me.ui.MainActivity.BUNDLE_LEG_INDEX;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if(savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            final int headIndex = extras.getInt(BUNDLE_HEAD_INDEX);
            final int bodyIndex = extras.getInt(BUNDLE_BODY_INDEX);
            final int legIndex = extras.getInt(BUNDLE_LEG_INDEX);

            BodyPartFragment headFragment = (BodyPartFragment) Fragment.instantiate(this, BodyPartFragment.class.getName());
//            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setBodyPartsList(AndroidImageAssets.getHeads());
            headFragment.setBodyPartIndex(headIndex);

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setBodyPartsList(AndroidImageAssets.getBodies());
            bodyFragment.setBodyPartIndex(bodyIndex);

            BodyPartFragment legsFragment = new BodyPartFragment();
            legsFragment.setBodyPartsList(AndroidImageAssets.getLegs());
            legsFragment.setBodyPartIndex(legIndex);

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }
    }
}
