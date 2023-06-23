package com.jon_cantero.mapsrecogerdirecciones.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper
{
    String crearMapa = "CREATE TABLE Mapas(Nombre text,Direccion text, Ciudad text, Comunidad text,Pais text, CodigoPostal text, Latitud text, Longitud text)";

    public Database(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(contexto,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(crearMapa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int versionAnt,int versionNue)
    {
        db.execSQL("DROP TABLE IF EXISTS Mapas");
        db.execSQL(crearMapa);
    }
}