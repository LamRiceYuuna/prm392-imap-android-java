package com.thltd.androidapp.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.thltd.androidapp.R;
import com.thltd.androidapp.databinding.ActivityMainBinding;

import java.util.HashSet;
import java.util.Set;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;
    private NavController navController;

    private void  bindingView(){
        toggle = new ActionBarDrawerToggle(this, binding.mainDrawerLayout, R.string.open, R.string.close);

        navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onSetupActionBar();
        bindingView();
        bindingAction();
    }
    private void onSetupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#CFEAF0")));
        actionBar.setTitle("IMap");
    }
    private void bindingAction() {

        binding.bottomNav.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.home){
                navController.navigate(R.id.home);
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                return true;
            }else  if(item.getItemId()==R.id.noti){
                navController.navigate(R.id.noti);
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                return true;
            }else  if(item.getItemId()==R.id.profile){
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                return true;
            }else {
                return false;
            }
        });

        binding.mainDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.item1){
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START, false);
                return true;
            }else if(item.getItemId() == R.id.item2){
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }else if(item.getItemId()==R.id.logout){
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            return false;
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_header_search, menu);
        onSearch(menu);
        return true;
    }

    private void onSearch(Menu menu) {
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                searchView.setIconified(true);
                searchView.clearFocus();
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
    }
}