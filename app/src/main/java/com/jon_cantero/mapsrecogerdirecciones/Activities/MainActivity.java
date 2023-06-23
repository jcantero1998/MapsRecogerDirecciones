package com.jon_cantero.mapsrecogerdirecciones.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jon_cantero.mapsrecogerdirecciones.Fragments.FormFragment;
import com.jon_cantero.mapsrecogerdirecciones.Fragments.ListFragment;
import com.jon_cantero.mapsrecogerdirecciones.Fragments.MapFragment;
import com.jon_cantero.mapsrecogerdirecciones.Models.Location;
import com.jon_cantero.mapsrecogerdirecciones.R;

public class MainActivity extends AppCompatActivity implements  MapFragment.DataListener{

    // Creamos un fragment para controlar en qué fragment estamos en cada momento
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        // Condicionamos la carga del fragmento
        // si el savedInstanceState es nulo, quiere decir que es la primera
        // vez que se carga el activity
        if (savedInstanceState== null) {
            // Configuramos el fragmento que queremos que se ejecute de inicio
            currentFragment = new MapFragment();
            changeFragment(currentFragment);
        }
    }

    private void setupToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Manejamos el evento click en el menú de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Hacemos un switch porque comtemplamos que pueda haber más de una opción en el menú
        switch (item.getItemId()) {
            // En el caso de nuestra opción
            case R.id.mapa:
                currentFragment = new MapFragment();
                break;
            case R.id.misUbicacionea:
                currentFragment = new ListFragment();
                //currentFragment = new WellcomeFragment();
                break;
        }
        // Aplicamos el fragment elegido
        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }
    
    // Creamos un método para hacer la transición del fragment.
    // Hacemos la transición del contenedor de fragmentos declarados en el layout
    // al fragment que pasamos como parámetro.
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void sendLocation(Location location) {

        // instancia del fragmento al que queremos comunicarnos
        // Le tenemos que hacer un casting por que si no trae un fragmento generico
        FormFragment formFragment = (FormFragment) new FormFragment();
        formFragment.renderLocation(location);
        currentFragment = formFragment;
        changeFragment(currentFragment);
    }

}