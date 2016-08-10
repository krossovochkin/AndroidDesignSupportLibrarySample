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
package com.krossovochkin.adsl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.krossovochkin.adsl.appbarlayout.AppBarLayoutActivity;
import com.krossovochkin.adsl.bottomsheets.BottomSheetDialogFragmentActivity;
import com.krossovochkin.adsl.bottomsheets.BottomSheetsActivity;
import com.krossovochkin.adsl.collapsingtoolbarlayout.CollapsingToolbarLayoutScrollActivity;
import com.krossovochkin.adsl.collapsingtoolbarlayout.CollapsingToolbarLayoutScrollEnterAlwaysEnterAlwaysCollapsedActivity;
import com.krossovochkin.adsl.collapsingtoolbarlayout.CollapsingToolbarLayoutScrollExitUntilCollapsedActivity;
import com.krossovochkin.adsl.collapsingtoolbarlayout.CollapsingToolbarLayoutTextProtectionActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutAnchorActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutAppBarLayoutScrollActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutAppBarLayoutScrollEnterAlwaysActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutAppBarLayoutScrollEnterAlwaysSnapActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutCustomButtonAndSnackbarActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutFabAndSnackbarActivity;
import com.krossovochkin.adsl.coordinatorlayout.CoordinatorLayoutFabScrollBehaviorActivity;
import com.krossovochkin.adsl.fab.FloatingActionButtonActivity;
import com.krossovochkin.adsl.snackbar.SnackbarActivity;
import com.krossovochkin.adsl.tablayout.TabLayoutManualActivity;
import com.krossovochkin.adsl.tablayout.TabLayoutViewPagerFixedActivity;
import com.krossovochkin.adsl.tablayout.TabLayoutViewPagerScrollActivity;
import com.krossovochkin.adsl.textinputlayout.TextInputLayoutBasicsActivity;
import com.krossovochkin.adsl.textinputlayout.TextInputLayoutCountersActivity;
import com.krossovochkin.adsl.textinputlayout.TextInputLayoutErrorsActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        setupNavigationView(navigationView);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        final ListView listView = (ListView) findViewById(R.id.listView);
        initList(listView);
    }

    private void setupNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getGroupId() == R.id.checkableGroup) {
                    item.setChecked(true);
                }
                // TODO: provide some action depending on item selected - by item.getItemId()
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        Menu menu = navigationView.getMenu();

        SwitchCompat switchCompat = (SwitchCompat) MenuItemCompat.getActionView(menu.findItem(R.id.switchItem))
            .findViewById(R.id.switchCompat);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this,
                    "switch is " + (isChecked ? "checked" : "unchecked"),
                    Toast.LENGTH_SHORT).show();
            }
        });

        TextView textView = (TextView) MenuItemCompat.getActionView(menu.findItem(R.id.counterItem))
            .findViewById(R.id.textView);
        textView.setText("5");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initList(final ListView listView) {
        String[] items = new String[] {
            "TextInputLayout - Basics",
            "TextInputLayout - Errors",
            "TextInputLayout - Counters",
            "FloatingActionButton - Basics",
            "Snackbar - Basics",
            "TabLayout - ViewPager, Fixed",
            "TabLayout - ViewPager, Scroll",
            "TabLayout - Manual",
            "CoordinatorLayout - Fab & Snackbar",
            "AppBarLayout - Basics",
            "AppBarLayout - scroll",
            "AppBarLayout - scroll|enterAlways",
            "AppBarLayout - scroll|enterAlways|snap",
            "CoordinatorLayout - anchor",
            "CoordinatorLayout - Custom FAB-like behavior",
            "CoordinatorLayout - ScrollAwareFABBehavior by Ian Lake",
            "CollapsingToolbarLayout, NestedScrollView, FAB Behavior - scroll",
            "CollapsingToolbarLayout, ... - scroll|exitUntilCollapsed",
            "CollapsingToolbarLayout, ... - scroll|enterAlways|enterAlwaysCollapsed",
            "CollapsingToolbarLayout - Text Protection",
			"Bottom Sheets - Basics",
            "Bottom Sheets - Dialog Fragment"
        };
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, TextInputLayoutBasicsActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TextInputLayoutErrorsActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, TextInputLayoutCountersActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, FloatingActionButtonActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, SnackbarActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, TabLayoutViewPagerFixedActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, TabLayoutViewPagerScrollActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, TabLayoutManualActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutFabAndSnackbarActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, AppBarLayoutActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutAppBarLayoutScrollActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutAppBarLayoutScrollEnterAlwaysActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutAppBarLayoutScrollEnterAlwaysSnapActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutAnchorActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutCustomButtonAndSnackbarActivity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, CoordinatorLayoutFabScrollBehaviorActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, CollapsingToolbarLayoutScrollActivity.class));
                        break;
                    case 17:
                        startActivity(new Intent(MainActivity.this, CollapsingToolbarLayoutScrollExitUntilCollapsedActivity.class));
                        break;
                    case 18:
                        startActivity(new Intent(MainActivity.this, CollapsingToolbarLayoutScrollEnterAlwaysEnterAlwaysCollapsedActivity.class));
                        break;
                    case 19:
                        startActivity(new Intent(MainActivity.this, CollapsingToolbarLayoutTextProtectionActivity.class));
                        break;
					case 20:
						startActivity(new Intent(MainActivity.this, BottomSheetsActivity.class));
						break;
                    case 21:
                        startActivity(new Intent(MainActivity.this, BottomSheetDialogFragmentActivity.class));
                        break;
                }
            }
        });
    }
}
