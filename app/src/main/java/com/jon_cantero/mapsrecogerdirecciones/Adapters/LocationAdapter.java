package com.jon_cantero.mapsrecogerdirecciones.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jon_cantero.mapsrecogerdirecciones.Models.Location;
import com.jon_cantero.mapsrecogerdirecciones.R;

import java.util.List;

public class LocationAdapter extends BaseAdapter {

    //implementamos los métodos abstractos
    //Necesitamos un contexto, un layout que lo pasamos como referencia y una lista de string
    private Context context;
    private int layout;
    private List<Location> locations;

    // Nos creamos el constructor
    public LocationAdapter(Context context, int layout, List<Location> locations) {
        this.context = context;
        this.layout = layout;
        this.locations = locations;
    }

    // Le dice al activity cuántas veces hay que iterar sobre un listview
    @Override
    public int getCount() {
        return this.locations.size();
    }

    // Para obtener un item, me devuelve el item de la posicion
    @Override
    public Object getItem(int position) {
        return this.locations.get(position);
    }

    //Para obtener el id de un item
    @Override
    public long getItemId(int id) {
        return id;
    }

    // Donde se dibuja lo que queremos hacer, el método clave
    // es donde está el tema
    // convertView es la lista de vistas que se va a dibujar
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // Copiamos la vista que vamos a inflar
        View v = convertView;

        // Usamos la clase LayoutInflater que se obtiene de un método de la misma clase pasándole u contexto
        // Inflamos la vista que nos hallegado con el layout personalizado
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        // Le indicamos el Layout que hemos creado antes
        v = layoutInflater.inflate(R.layout.list_view_locations, null);

        // Nos traemos el valor de la posición
        String currentName = locations.get(position).getNombre();
        String currentCity = locations.get(position).getCiudad();
        String currentCommunity = locations.get(position).getComunidad();
        String currentPostalCode = locations.get(position).getCodigopostal();

        // Nos falta rellenar los textView del Layout
        // aquí no tenemos el FindViewById, para referenciarlo tenemos que usar la vista que hemos creado

        TextView txtName = (TextView) v.findViewById(R.id.txtName);
        txtName.setText(currentName);

        TextView txtCiudad = (TextView) v.findViewById(R.id.txtCiudad);
        txtCiudad.setText(currentCity);

        TextView txtComunidad = (TextView) v.findViewById(R.id.txtComunidad);
        txtComunidad.setText(currentCommunity);

        TextView txtCodigoPostal = (TextView) v.findViewById(R.id.txtCodigoPostal);
        txtCodigoPostal.setText(currentPostalCode);

        // Devolvemos la vista inflada y modificada  para terminar
        return v;
    }
}