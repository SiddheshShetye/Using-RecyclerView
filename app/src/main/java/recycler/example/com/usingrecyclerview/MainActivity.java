package recycler.example.com.usingrecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerViewFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragment = RecyclerViewFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frmContent,mFragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        getSupportActionBar().setTitle(item.getTitle());
        int id = item.getItemId();

        if (id == R.id.vertical_recyclerview) {
            mFragment.changeRecyclerViewTo(RecyclerViewFragment.VERTICAL_LIST);

        } else if (id == R.id.horizontal_recyclerview) {
            mFragment.changeRecyclerViewTo(RecyclerViewFragment.HORIZONTAL_LIST);
        } else if (id == R.id.vertical_grid_recyclerview) {
            mFragment.changeRecyclerViewTo(RecyclerViewFragment.VERTICAL_GRID);
        } else if (id == R.id.horizontal_grid_recyclerview) {
            mFragment.changeRecyclerViewTo(RecyclerViewFragment.HORIZONTAL_GRID);
        } else if (id == R.id.vertical_staggered_recyclerview) {
            mFragment.changeRecyclerViewTo(RecyclerViewFragment.VERTICAL_STAGGERED_GRID);
        } else if (id == R.id.horizontal_staggered_recyclerview) {
            mFragment.changeRecyclerViewTo(RecyclerViewFragment.HORIZONTAL_STAGGERED_GRID);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
