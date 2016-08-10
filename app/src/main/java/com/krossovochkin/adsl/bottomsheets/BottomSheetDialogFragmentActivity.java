package com.krossovochkin.adsl.bottomsheets;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.krossovochkin.adsl.R;

/*
 * Copyright 2016 Vasya Drobushkov
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
public class BottomSheetDialogFragmentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bottom_sheet_dialog_fragment);

		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				BottomSheetDialogFragment dialog = new MyBottomSheetDialogFragment();
				dialog.show(getSupportFragmentManager(), dialog.getTag());
			}
		});
	}

	public static class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
		@Override
		public void setupDialog(Dialog dialog, int style) {
			super.setupDialog(dialog, style);
			dialog.setContentView(R.layout.dialog_fragment_bottom_sheet);
		}
	}
}
