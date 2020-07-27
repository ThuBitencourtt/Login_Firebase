package com.example.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.menus.interfaces.Comunicador;
import com.example.menus.interfaces.Comunicador2;
import com.example.menus.interfaces.Comunicador3;
import com.example.menus.ui.home.HomeFragment;
import com.example.menus.ui.professor.Professor;
import com.example.menus.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements Comunicador,Comunicador2, Comunicador3 {

    private DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;
    public static final String BANDA_KEY = "banda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_professor, R.id.nav_aluno)
                .setDrawerLayout(drawer)
                .build();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.nav_home) {

                    replaceFragment(new HomeFragment());

                } else if (id == R.id.nav_professor) {

                    replaceFragment(new Professor());

                }else if (id == R.id.nav_aluno){

                    replaceFragment(new SlideshowFragment());
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, HomeFragment.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void recebeNomeDaBanda(String nome) {
        Bundle bundle = new Bundle();
        bundle.putString(BANDA_KEY, nome);

        Fragment fragmentConteudo = new Conteudo();
        fragmentConteudo.setArguments(bundle);

        replaceFragment(fragmentConteudo);

    }

    @Override
    public void atividade_professor(String nome) {
        Bundle bundle = new Bundle();
        bundle.putString(BANDA_KEY, nome);

        Fragment fragmentAtividade = new Ativiidade_professor();
        fragmentAtividade.setArguments(bundle);

        replaceFragment(fragmentAtividade);

    }
    @Override
    public void entrega(String nome) {
        Bundle bundle = new Bundle();
        bundle.putString(BANDA_KEY, nome);

        Fragment fragmentEntrega = new Entrega();
        fragmentEntrega.setArguments(bundle);

        replaceFragment(fragmentEntrega);

    }

}
