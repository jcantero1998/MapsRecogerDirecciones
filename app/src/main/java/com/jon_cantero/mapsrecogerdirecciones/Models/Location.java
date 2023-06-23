package com.jon_cantero.mapsrecogerdirecciones.Models;

public class Location {
    private String nombre;
    private String direccion;
    private String ciudad;
    private String comunidad;
    private String pais;
    private String codigopostal;
    private String latitud;
    private String longitud;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getComunidad() {
        return comunidad;
    }
    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getCodigopostal() {
        return codigopostal;
    }
    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }
    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Location() {
    }

    public Location(String direccion, String ciudad, String comunidad, String pais, String codigopostal) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.comunidad = comunidad;
        this.pais = pais;
        this.codigopostal = codigopostal;
    }
    public Location(String nombre, String direccion, String ciudad, String comunidad, String pais,
                    String codigopostal) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.comunidad = comunidad;
        this.pais = pais;
        this.codigopostal = codigopostal;
    }

    public Location(String direccion, String ciudad, String comunidad, String pais, String codigopostal, String latitud,
                    String longitud) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.comunidad = comunidad;
        this.pais = pais;
        this.codigopostal = codigopostal;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Location(String nombre, String direccion, String ciudad, String comunidad, String pais, String codigopostal,
                    String latitud, String longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.comunidad = comunidad;
        this.pais = pais;
        this.codigopostal = codigopostal;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
        result = prime * result + ((codigopostal == null) ? 0 : codigopostal.hashCode());
        result = prime * result + ((comunidad == null) ? 0 : comunidad.hashCode());
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + ((pais == null) ? 0 : pais.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (ciudad == null) {
            if (other.ciudad != null)
                return false;
        } else if (!ciudad.equals(other.ciudad))
            return false;
        if (codigopostal == null) {
            if (other.codigopostal != null)
                return false;
        } else if (!codigopostal.equals(other.codigopostal))
            return false;
        if (comunidad == null) {
            if (other.comunidad != null)
                return false;
        } else if (!comunidad.equals(other.comunidad))
            return false;
        if (direccion == null) {
            if (other.direccion != null)
                return false;
        } else if (!direccion.equals(other.direccion))
            return false;
        if (pais == null) {
            if (other.pais != null)
                return false;
        } else if (!pais.equals(other.pais))
            return false;
        return true;
    }
}
