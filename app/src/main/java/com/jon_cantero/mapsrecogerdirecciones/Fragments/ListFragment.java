package com.jon_cantero.mapsrecogerdirecciones.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jon_cantero.mapsrecogerdirecciones.Adapters.LocationAdapter;
import com.jon_cantero.mapsrecogerdirecciones.Databases.Database;
import com.jon_cantero.mapsrecogerdirecciones.Models.Location;
import com.jon_cantero.mapsrecogerdirecciones.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    //region propiedades

    //Lista estatica que contenrá los Mapas
    public static List<Location> locations = new ArrayList<Location>();

    //Adaptador que contendrá los Mails
    public static LocationAdapter adapter;

    //Propiedades para la base de datos
    private static Database datos;
    private static SQLiteDatabase db;

    //Propiedades para la ListView
    private ListView listView;

    //endregion

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Asignamos nuestra base de datos a la variable
        datos = new Database(getContext(),"Datos",null,1);

        //Cargamos la lista de locations de la base de datos
        try {
            String respuesta = cargarMapas();
            if (respuesta != ""){
                Toast.makeText(getContext(),respuesta,Toast.LENGTH_LONG).show();
            }
        }catch(Exception e) {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        //Volcamos sobre un view lo mismo que el return
        View view =  inflater.inflate(R.layout.fragment_list, container, false);

        //Asociamos nuestro ListView
        listView = (ListView) view.findViewById(R.id.lvUbicaciones);

        //le pasamos al adaptador 1a lista de locations
        adapter = new LocationAdapter(getContext(),R.layout.list_view_locations, locations);

        // Asociamos el ListView con el Adaptador
        listView.setAdapter(adapter);

        // Cambio el return para que me devuelva el view
        return view;
    }

    public static String cargarMapas() {
        //Abrimos la base de datos en modo lectura y escritura
        db = datos.getWritableDatabase();

        if(db != null)
        {
            //Vaciamos la lista
            locations.clear();

            //Volvemos a cargar la lista
            Cursor fila = db.rawQuery("select * from Mapas", null);
            if(fila.moveToFirst()){
                do{
                    locations.add(new Location(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5)));
                }while(fila.moveToNext());
            }
            db.close();
            return "";
        } else {
            db.close();
            return "Error al acceder a la base de datos";
        }
    }

}
