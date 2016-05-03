package app.com.example.android.cloudpad_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Objects;

import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.adapters.ViewPagerAdapter;
import app.com.example.android.cloudpad_app.utils.GeneralHandler;
import app.com.example.android.cloudpad_app.classes.handlers.PushHandler;
import app.com.example.android.cloudpad_app.fragments.FragmentPrivate;
import app.com.example.android.cloudpad_app.fragments.FragmentShared;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.launcher.LoginContainerActivity;


public class MainActivity extends AppCompatActivity {

    //region Fields
    private Drawer drawer;
    //test
    private Account thisAccount;
    private static final String TAG = "MainActivity";
    //test edit

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            //region Initialize activity
            //test
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

            initializeFields();

            setupDrawer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeFields() {
        setSupportActionBar(toolbar);
        thisAccount = new LocalHandler(this).getAccount();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        //endregion

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                new LocalHandler(MainActivity.this).setTab(tab.getText().toString());
                viewPager.setCurrentItem(tab.getPosition());
            }

            //region Unused methods
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
            //endregion
        });

        new PushHandler(this, thisAccount).connectToAllChannels();
    }

    private void setupDrawer() {
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.primary)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(thisAccount.getUsername())
                                .withEmail(thisAccount.getEmail())
                                .withIcon(R.color.primary_dark)
                )
                .build();

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(getResources().getString(R.string.navigation_home)).withIdentifier(1).withIcon(MaterialDesignIconic.Icon.gmi_home).withSelectable(false),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(getResources().getString(R.string.navigation_feedback)).withIdentifier(4).withIcon(MaterialDesignIconic.Icon.gmi_plus).withSelectable(false),
                        new SecondaryDrawerItem().withName(getResources().getString(R.string.navigation_help)).withIdentifier(5).withIcon(MaterialDesignIconic.Icon.gmi_help).withSelectable(false),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(getResources().getString(R.string.navigation_logout)).withIdentifier(100).withIcon(MaterialDesignIconic.Icon.gmi_square_right).withSelectable(false)
                )
                .withAccountHeader(headerResult)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                                   @Override
                                                   public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                                       try {
                                                           if (drawerItem != null) {
                                                               switch (drawerItem.getIdentifier()) {
                                                                   case 1://Home
                                                                       closeDrawer();
                                                                       break;
                                                                   case 4://Feedback
                                                                       Intent feedback = new Intent(MainActivity.this, FeedbackActivity.class).putExtra(Config.KEY_ACCOUNT, thisAccount);
                                                                       startActivity(feedback);
                                                                       break;
                                                                   case 5://Help
                                                                       startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://athena.fhict.nl/users/i324201/edwinvanrooij/")));
                                                                       break;
                                                                   case 100://Logout
                                                                       onLogout();
                                                                       break;
                                                                   default:
                                                                       Toast.makeText(MainActivity.this, "Kon niet bepalen waar je op drukte.", Toast.LENGTH_SHORT).show();
                                                                       break;
                                                               }
                                                           }
                                                       } catch (Exception e) {
                                                           e.printStackTrace();
                                                       }
                                                       return true;
                                                   }
                                               }

                )
                .build();
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void closeDrawer() {
        drawer.closeDrawer();
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        startActivity(new Intent(MainActivity.this, NewNoteActivity.class).putExtra(Config.KEY_ACCOUNT, thisAccount));
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            String tabname = new LocalHandler(this).getTabName(tabLayout.getTabAt(0).getText().toString());
            if (Objects.equals(tabname, tabLayout.getTabAt(0).getText().toString())) {
                viewPager.setCurrentItem(0);
            } else if (Objects.equals(tabname, tabLayout.getTabAt(1).getText().toString())) {
                viewPager.setCurrentItem(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onLogout() {
        new LocalHandler(this).onLogout();
        GeneralHandler.ClearTopGo(this, LoginContainerActivity.class, R.anim.fade_in_slow, R.anim.fade_out_slow);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FragmentPrivate fragmentPrivate = new FragmentPrivate();
        FragmentShared fragmentShared = new FragmentShared();

        Bundle b = new Bundle();
        b.putParcelable(Config.KEY_ACCOUNT, thisAccount);

        fragmentPrivate.setArguments(b);
        fragmentShared.setArguments(b);

        adapter.addFragment(fragmentPrivate, getResources().getString(R.string.tab_main_private_title));
        adapter.addFragment(fragmentShared, getResources().getString(R.string.tab_main_shared_title));

        viewPager.setAdapter(adapter);
    }
}

