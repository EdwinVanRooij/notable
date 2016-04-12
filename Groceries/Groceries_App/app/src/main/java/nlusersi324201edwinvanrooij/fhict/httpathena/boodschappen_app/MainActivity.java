package nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Adapters.ViewPagerAdapter;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Classes.Physical.GroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.boodschappen_app.Fragments.FragmentGroceryList;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncDataRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.AsyncTasks.AsyncURLRequest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.launcher.LoginContainerActivity;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;

public class MainActivity extends AppCompatActivity {
    //region Fields
    private Account thisAccount;
    private Drawer drawer;

    private static final String TAG = "MainActivity";

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    //endregion

    //region Properties

    //endregion

    //region Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        thisAccount = new LocalHandler(this).getAccount();

        setupDrawer();

        getAmountOfLists();
    }
    //endregion

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
                        new SecondaryDrawerItem().withName(getResources().getString(R.string.navigation_myLists)).withIdentifier(2).withIcon(MaterialDesignIconic.Icon.gmi_assignment).withSelectable(false),
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
                                                                   case 2://My lists
                                                                       onMyLists();
                                                                       break;
                                                                   case 4://Feedback
//                                                                       Intent feedback = new Intent(MainActivity.this, FeedbackActivity.class).putExtra(Config.KEY_ACCOUNT, thisAccount);
//                                                                       startActivity(feedback);
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

    @Override
    public void onResume() {
        try {
            super.onResume();
            String tabname = new LocalHandler(this).getTabName(tabLayout.getTabAt(0).getText().toString());
            if (tabname.equals(tabLayout.getTabAt(0).getText().toString())) {
                viewPager.setCurrentItem(0);
            } else if (tabname.equals(tabLayout.getTabAt(1).getText().toString())) {
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

    private void onMyLists() {
        Intent i = new Intent(MainActivity.this, MyLists.class).putExtra(Config.KEY_ACCOUNT, thisAccount);
        startActivity(i);
    }

    private void getAmountOfLists() {
        AsyncResponse a = new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                List<GroceryList> lists = GeneralHandler.getGroceryListsFromJSON(output);
                setupViewPager(viewPager, lists);
            }
        };
        String query = "select l.id, title from groceries_list l, groceries_list_account la, account a where a.id = la.account_id and l.id = la.list_id and account_id = " + thisAccount.getId();
        new AsyncDataRequest(a, this, AsyncURLRequest.queryType.Select, query, true).execute();
    }

    private void setupViewPager(final ViewPager viewPager, List<GroceryList> lists) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for (GroceryList l :
                lists) {
            Bundle b = new Bundle();
            b.putParcelable(Config.KEY_ACCOUNT, thisAccount);
            FragmentGroceryList f = new FragmentGroceryList();
            b.putParcelable(Config.KEY_LIST, l);
            f.setArguments(b);
            adapter.addFragment(f, l.getTitle());
        }

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}

