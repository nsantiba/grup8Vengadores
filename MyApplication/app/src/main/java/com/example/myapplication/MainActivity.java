package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2,et3,et4,et5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        et5=(EditText)findViewById(R.id.et5);

    }


    public void ingresar(View v) {
        //base de batos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracionPelis", null, 1);
        // se obtiene base de datos
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et4.getText().toString();
        String tit=et1.getText().toString();
        String anoo=et2.getText().toString();
        String urll= et3.getText().toString();
        String descri = et5.getText().toString();

        if(cod.isEmpty()||descri.isEmpty()||tit.isEmpty()|| anoo.isEmpty()|| urll.isEmpty()){
            Toast.makeText(this, "DATOS INCOMPLETOS", //tambien tuepe estar en el try catch con SQLexception, pero es muy general
                    //esta validacion es mas especific
                    Toast.LENGTH_SHORT).show();
            return;

        }
        try{
            bd.execSQL("insert into articulos (codigo,titulo,ano,url,descripcion) values ("+cod+",'"+tit+"',"+anoo+",'"+urll+"','"+descri+"')");
            bd.close();//cierra base de datos
            et1.setText("");//ebcero los valores de la parte visual
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
            Toast.makeText(this, "Se cargaron los datos de los pelicula",
                    Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteConstraintException e){
            Toast.makeText(this, "Codigo repetido, pelicula no ingresada",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void consultaportit(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracionPelis", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String titt=et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select * from articulos where titulo like '%" + titt +"%'", null);
        //like o igual pero con like puede decir las que epiezan con o temrminan con

        if (fila.moveToFirst()) {
            Intent i1 = new Intent(this, MainActivity2.class );
            i1.putExtra("Codigoi",fila.getString(0));
            i1.putExtra("Tituloi",fila.getString(1));
            i1.putExtra("anoi",fila.getString(2));
            i1.putExtra("urli",fila.getString(3));
            i1.putExtra("descrii",fila.getString(4));
            startActivity(i1);

        } else
            Toast.makeText(this, "No existe un pelicula con ese titulo",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }



}