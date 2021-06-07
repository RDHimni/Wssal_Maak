package ridaz.wssalmaak;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.zip.Inflater;

import dagger.hilt.EntryPoint;
import dagger.hilt.android.AndroidEntryPoint;
import ridaz.wssalmaak.models.User;
import ridaz.wssalmaak.viewmodel.MainViewModel;


@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    private MainViewModel viewModel;
    private NavigationView navigationView;
    private BottomNavigationView bottomBar;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        drawer = findViewById(R.id.drawer_layout);
        bottomBar =  findViewById(R.id.bottom_navigation);

        //setup NavDrawer
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        ImageView imageUser = header.findViewById(R.id.UserImageViewNavHead);
        TextView nameUser = header.findViewById(R.id.userNameInHeader);
        TextView emailUser = header.findViewById(R.id.EmailTvInHeader);
        RatingBar ratingBar = header.findViewById(R.id.ratingBarInHeader);
        retrieveData(imageUser, nameUser, emailUser, ratingBar);


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        //setup NavBottom
        NavigationUI.setupWithNavController(bottomBar,navController);


    }

    private void retrieveData(ImageView imageUser, TextView nameUser, TextView emailUser, RatingBar ratingBar) {
        viewModel.SelectUserFromRoom();
        viewModel.getUserRoom().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Glide.with(MainActivity.this).load(user.getProfilePhotoUrl() + "").into(imageUser);
                nameUser.setText(user.getLastName() + " " + user.getFirstName());
                emailUser.setText(user.getEmail());
                ratingBar.setRating((float) user.getRate());

            }
        });
    }


    public void openCloseNavigationDrawer(View view) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }





}