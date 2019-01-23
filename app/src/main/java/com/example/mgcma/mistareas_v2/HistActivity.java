package com.example.mgcma.mistareas_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mgcma.mistareas_v2.bbdd.ControladorBBDD;

public class HistActivity extends AppCompatActivity {

    //Instanciamos la clase de la BBDD, variable global
    ControladorBBDD controladorDB;
    //Creamos un array que almencenará las cadenas de texto que se vayan creando y/o eliminado
    private ArrayAdapter<String> miAdapter;
    //Referenciamos al ListView de tareas que hemos creado
    ListView listViewTareas;
    //String tarea_old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);
        //Creamos el objeto controladorDB justo onCreate el MainActivitity que será el controlador
        controladorDB = new ControladorBBDD(this);
        listViewTareas = (ListView) findViewById(R.id.listTareas_old);
        controladorDB.obtenerTareas_old();
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        //Lo primero que tenemos que hacer es comprobar los registros que tiene la tabla
        if(controladorDB.numeroRegistros_old()==0){
            listViewTareas.setAdapter(null);
        } else {
            miAdapter = new ArrayAdapter<>(this,R.layout.item_tarea_old,R.id.tareaView_old,controladorDB.obtenerTareas_old());
            listViewTareas.setAdapter(miAdapter);
        }
    }
}
