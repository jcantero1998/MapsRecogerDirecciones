package com.jon_cantero.mapsrecogerdirecciones.Fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jon_cantero.mapsrecogerdirecciones.Models.Location;
import com.jon_cantero.mapsrecogerdirecciones.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap gMap;
    private MapView mapView;

    private Button buttonCreate;
    private DataListener callback;
    private Location location;

    private Geocoder geocoder;
    private List<Address> addresses;

    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        buttonCreate = (Button) view.findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (location == null){
                    Toast.makeText(getContext(),"Seleccione una localización", Toast.LENGTH_LONG).show();
                }else{
                    callback.sendLocation(location);
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // Creamos una posición y su marcador
        LatLng cuatrovientos = new LatLng(42.824534415282834,-1.6601076722145083);

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        gMap.addMarker(new MarkerOptions().position(cuatrovientos).title("Hola desde 4Vientos").draggable(true));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(cuatrovientos));
        gMap.animateCamera(zoom);

        gMap.setOnMarkerDragListener(this);

        geocoder = new Geocoder(getContext(), Locale.getDefault());
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        // Declaramos las variables de posicionamiento
        // en ellas recogeremos las coordenadas de la posición
        double latitud = marker.getPosition().latitude;
        double longitud = marker.getPosition().longitude;

        try {
            // Declaramos donde vamos a recoger la dirección.
            // este método lanza una IOException, por lo que nos recomienda  controlarla
            // Nos devuelve una lista de hasta el maxResult de elementos(tercer parámetro)
            addresses = geocoder.getFromLocation(latitud, longitud, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Recojo en cada variable lo que me interesa mostrar
        String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();

        //Creo la localizacion que pasaré por el metodo sendLocation(Location location)
        location = new Location(address,city,state,country,postalCode);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            // Convertimos el contexto en DataListener y lo guardamos en el callback
            callback = (DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " DataListener mal implementado");
        }

    }

    public interface DataListener {
        void sendLocation(Location location);
    }
}
