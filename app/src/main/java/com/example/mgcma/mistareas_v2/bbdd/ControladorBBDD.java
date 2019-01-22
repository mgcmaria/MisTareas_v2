package com.example.mgcma.mistareas_v2.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControladorBBDD extends SQLiteOpenHelper {

    //Constructor
    public ControladorBBDD(Context context) {
        super(context, "com.example.mgcma.mistareas_v2.bbdd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la tabla tareas con un id autoincrementable y un nombre que no está vacío
        db.execSQL("CREATE TABLE TAREAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL)");
    }

    //Este método lo utilizariamos en caso de cambiar la versión de la bbdd, haciendo una migración de la antigua a la nueva
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void anadirTarea (String tarea){

        //Indicamos que registro es la variable que contendrá la información al insertar
        ContentValues registro = new ContentValues();
        registro.put("NOMBRE", tarea);//Indicamos el nombre de la columna que corresponda de la tabla

        //Paso 1: Abrimos la bbdd para lectura/escritura
        SQLiteDatabase db = this.getWritableDatabase();
        //Paso 2: Ejecutamos la sentencia
        db.insert("TAREAS",null, registro);
        //Paso 3: Cerramos la BBDD
        db.close();
    }

    //Creamos el método que devolverá un Array de cadenas
    public String[] obtenerTareas (){

        //Paso 1: Abrimos la bbdd para lectura
        SQLiteDatabase db = this.getReadableDatabase();
        //Paso 2: Ejecutamos la sentencia y guardamos los resultados en una variable de tipo cursor
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS",null);

        //Declaramos una variable que nos va a contar el núemro de registros de la tabla
        int contador = cursor.getCount();
        // Ahora vamos a comprobar si contador tiene o no registros
        if(contador == 0){
            db.close();
            return null;
        } else {
            String[] tareas = new String[contador];
            cursor.moveToFirst();
            for (int i=0; i<contador;i++){
                tareas[i]=cursor.getString(1);
                cursor.moveToNext();
            }
            //Paso 3. Cerramos la BBDD
            db.close();
            return tareas;
        }
    }

    public int numeroRegistros (){
        //Paso 1: Abrimos la bbdd para lectura
        SQLiteDatabase db = this.getReadableDatabase();
        //Paso 2: Ejecutamos la sentencia y guardamos los resultados en una variable de tipo cursor
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS",null);
        //Paso 3: Retornamos el número de registros
        return cursor.getCount();
    }

    public void borrarTarea (String tarea){
        //Paso 1: Abrimos la bbdd para lectura/escritura
        SQLiteDatabase db = this.getWritableDatabase();
        //Paso 2: Ejecutamos la acción de borrado
        //Tenemos que pasar un Array como valor, por que vamos a crear un Array de String de una sola tarea.
        db.delete("TAREAS","NOMBRE=?",new String[]{tarea});
        //Paso 3: Cerramos la BBDD
        db.close();
    }
}
