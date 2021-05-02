package com.example.projectd;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.projectd.ui.movie_detail.MovieDetailFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements FragmentCallback{
    Toolbar toolbar;

    Fragment fragment_movieDetailInfo;
    FragmentManager manager;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        manager = getFragmentManager();
        fragment_movieDetailInfo = new MovieDetailFragment();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar); //Toolbar toolbar
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //fragment간 전환을 위해서 함수 선언
    public void onFragmentChange(){
        toolbar.setTitle("영화상세");

        //원래코드
        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment_movieDetailInfo).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment_movieDetailInfo).addToBackStack(null).commit();
        //fragment에 있는 list view가 이상하게 됨!
    }

    public void onCommand_CommentWriteActivity(){
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        startActivityForResult(intent, 101);
    }

    @Override
    public void showCommentWriteActivity() {
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        startActivityForResult(intent, 101);
    }

    /**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(intent != null){
                Toast.makeText(getApplicationContext(),"!!!!!", Toast.LENGTH_LONG).show();

                /* 원본
                String comment = intent.getStringExtra("comment");
                float rating = intent.getFloatExtra("rating", 0.0f);
                adapter.addItem(new CommentItem("qwerty0579", "시간", rating, comment, 0));
                commentListView.setAdapter(adapter);
                */

                String comment = intent.getStringExtra("comment");
                float rating = intent.getFloatExtra("rating", 0.0f);

                //bundle을 통해 movie detail fragment로 데이터를 전달하고 싶다~~
                Bundle bundle = new Bundle();
                bundle.putString("comment", comment);
                bundle.putFloat("rating", rating);
                fragment_movieDetailInfo.setArguments(bundle);




            }
        }
    }
}