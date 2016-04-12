package nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.fragments.FragmentAccounts;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.fragments.FragmentFeedback;
import nlusersi324201edwinvanrooij.fhict.httpathena.cloudpadadmin_app.Classes.fragments.FragmentTest;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Config;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.GeneralHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Handlers.LocalHandler;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Interfaces.AsyncResponse;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.Physical.Account;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.Classes.launcher.LoginContainerActivity;
import nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    //region Fields
    private Drawer drawer;
    private Account thisAccount;

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //region Initialize
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initializeFields();

        setupDrawer();
        //endregion
    }

    private void initializeFields() {
        setSupportActionBar(toolbar);
        thisAccount = new LocalHandler(this).getAccount();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentFeedback(), getResources().getString(R.string.tab_feedback));
        adapter.addFragment(new FragmentAccounts(), getResources().getString(R.string.tab_accounts));
        adapter.addFragment(new FragmentTest(), getResources().getString(R.string.tab_stats));
        adapter.addFragment(new FragmentTest(), getResources().getString(R.string.tab_etc));

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //region Actionbar
        int id = item.getItemId();

        switch (id) {
            case R.id.action_write_query:
                Intent i = new Intent(this, QueryActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
        //endregion
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //region Initialize actionbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        //endregion
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
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

    private void closeDrawer() {
        drawer.closeDrawer();
    }

    private void onLogout() {
        new LocalHandler(this).onLogout();
        GeneralHandler.ClearTopGo(this, LoginContainerActivity.class, R.anim.fade_in_slow, R.anim.fade_out_slow);
    }
}
