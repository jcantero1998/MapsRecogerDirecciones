package com.jon_cantero.mapsrecogerdirecciones.Fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jon_cantero.mapsrecogerdirecciones.Databases.Database;
import com.jon_cantero.mapsrecogerdirecciones.Models.Location;
import com.jon_cantero.mapsrecogerdirecciones.R;


public class FormFragment extends Fragment {

    private Button buttonCreate;

    private EditText etnombreLocal;
    private EditText etdireccionLocal;
    private EditText etciudadLocal;
    private EditText etcomunidadLocal;
    private EditText etpaisLocal;
    private EditText etcodigopostalLocal;

    private static Database datos;
    private static SQLiteDatabase db;

    public FormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_form, container, false);

        //Asignamos nuestra base de datos a la variable
        datos = new Database(getContext(),"Datos",null,1);

        //etnombreLocal = (EditText) view.findViewById(R.id.etnombre);
        etdireccionLocal = (EditText) view.findViewById(R.id.etdireccion);
        etciudadLocal = (EditText) view.findViewById(R.id.etciudad);
        etcomunidadLocal = (EditText) view.findViewById(R.id.etcomunidad);
        etpaisLocal = (EditText) view.findViewById(R.id.etpais);
        etcodigopostalLocal = (EditText) view.findViewById(R.id.etcodigopostal);

        buttonCreate = (Button) view.findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etnombreLocal.getText().toString().equals("") ||etdireccionLocal.getText().toString().equals("") || etciudadLocal.getText().toString().equals("")|| etcomunidadLocal.getText().toString().equals("") || etpaisLocal.getText().toString().equals("") || etcodigopostalLocal.getText().toString().equals("")) {
                    Toast.makeText(getContext(),"Completa todos los campos",Toast.LENGTH_LONG).show();
                }else {

                    Location locationToAdd = new Location(etnombreLocal.getText().toString(),etdireccionLocal.getText().toString(),etciudadLocal.getText().toString(),etcomunidadLocal.getText().toString(),etpaisLocal.getText().toString(),etcodigopostalLocal.getText().toString());

                    String respuesta = addLocation(locationToAdd);
                    if (respuesta!=""){
                        Toast.makeText(getContext(),respuesta,Toast.LENGTH_LONG).show();
                    }else {
                        //Borramos los campos de los edit text
                        etnombreLocal.setText("");
                        etdireccionLocal.setText("");
                        etciudadLocal.setText("");
                        etcomunidadLocal.setText("");
                        etpaisLocal.setText("");
                        etcodigopostalLocal.setText("");
                    }
                }
            }
        });
        return view;
    }

    public void renderLocation(Location location) {
        //java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.EditText.setText(java.lang.CharSequence)' on a null object reference
        //Me dice que los editText son null y no se porque, ya que los editText estan bien declarados y referenciados.
        etdireccionLocal.setText(location.getDireccion());
        etciudadLocal.setText(location.getCiudad());
        etcomunidadLocal.setText(location.getComunidad());
        etpaisLocal.setText(location.getPais());
        etcodigopostalLocal.setText(location.getCodigopostal());
    }

    public static String addLocation(Location location) {
        //Abrimos la base de datos en modo lectura y escritura
        db = datos.getWritableDatabase();

        if(db != null)
        {
            ContentValues registro = new ContentValues();

            registro.put("Nombre",location.getNombre());
            registro.put("Direccion",location.getDireccion());
            registro.put("Ciudad",location.getCiudad());
            registro.put("Comunidad",location.getComunidad());
            registro.put("Pais",location.getPais());
            registro.put("CodigoPostal",location.getCodigopostal());
            registro.put("Latitud","");
            registro.put("Longitud","");

            if(db.insert("Mapas", null, registro) == -1)
            {
                db.close();
                return "Error al añadir la localizacion";
            }else{
                db.close();
                return "";
            }
        } else {
            db.close();
            return "Error al acceder a la base de datos";
        }
    }

}