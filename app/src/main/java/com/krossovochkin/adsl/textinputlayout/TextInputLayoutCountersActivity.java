/*
 * Copyright 2015 Vasya Drobushkov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.krossovochkin.adsl.textinputlayout;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.View;

import com.krossovochkin.adsl.R;

public class TextInputLayoutCountersActivity extends BaseTextInputLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextInputLayout textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        textInputLayoutUsername.setCounterEnabled(true);
        textInputLayoutUsername.setCounterMaxLength(10);
        textInputLayoutUsername.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

        final TextInputLayout textInputLayoutText = (TextInputLayout) findViewById(R.id.textInputLayoutText);
        textInputLayoutText.setVisibility(View.VISIBLE);

        final TextInputLayout textInputLayoutStrange = (TextInputLayout) findViewById(R.id.textInputLayoutStrange);
        textInputLayoutStrange.setVisibility(View.VISIBLE);
    }
}
