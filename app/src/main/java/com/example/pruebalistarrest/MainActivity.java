package com.example.pruebalistarrest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView lstCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstCategoria = (ListView)findViewById(R.id.lstCategoria);
        listar();
    }

String getVariableBolean = "8";
    public void listar() {
        TareaWSListar tarea = new TareaWSListar();
        tarea.execute();
    }
    //Tarea Asíncrona para llamar al WS de listado en segundo plano
    public class TareaWSListar extends AsyncTask<String,Integer,Boolean> {

        public List<nthcPoligono> listaCategoria;

        protected Boolean doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            boolean resul = true;
            String urlApiREST = "https://www.amazoniaresiliente.com/admin/prueba.php?nthc_cliente_nthc_usuario_idnthc_usuario="+getVariableBolean;
            try {
                URL url = new URL(urlApiREST);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("lista");
                listaCategoria = new ArrayList<nthcPoligono>();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    String  idnthc_poligono = finalObject.getString("idnthc_poligono");
                    String poligono = finalObject.getString("poligono");
                    String area = finalObject.getString("area");
                    String imagen1 = finalObject.getString("imagen1");
                    String  lat1 = finalObject.getString("lat1");
                    String lng1 = finalObject.getString("lng1");
                    String imagen2 = finalObject.getString("imagen2");
                    String lat2 = finalObject.getString("lat2");
                    String  lng2 = finalObject.getString("lng2");
                    String imagen3 = finalObject.getString("imagen3");
                    String lat3 = finalObject.getString("lat3");
                    String lng3 = finalObject.getString("lng3");
                    String imagen4 = finalObject.getString("imagen4");
                    String lat4 = finalObject.getString("lat4");
                    String lng4 = finalObject.getString("lng4");
                    String nthc_cliente_idnthc_cliente = finalObject.getString("nthc_cliente_idnthc_cliente");
                    String nthc_cliente_nthc_usuario_idnthc_usuario = finalObject.getString("nthc_cliente_nthc_usuario_idnthc_usuario");

                    listaCategoria.add(new nthcPoligono(idnthc_poligono,poligono,area,imagen1,
                            lat1,lng1,imagen2,lat2,lng2,imagen3,lat3,lng3,imagen4,lat4,lng4,nthc_cliente_idnthc_cliente,nthc_cliente_nthc_usuario_idnthc_usuario));
                }
            } catch(Exception ex) {
                Log.e("ServicioRest","Error!", ex);
                resul = false;
            }
            return resul;


        }


        protected void onPostExecute(Boolean result) {


            if (result) {

                //Rellenamos la lista con los nombres de los productos
                //Rellenamos la lista con los resultados
                CategoriaListViewAdapter adaptador = new CategoriaListViewAdapter(MainActivity.this, listaCategoria);
                lstCategoria.setAdapter(adaptador);
                lstCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        nthcPoligono elegido = (nthcPoligono) parent.getItemAtPosition(position);
//
//                        CharSequence texto = "Seleccionado: " + elegido.getNombre();
//
//                        Toast toast = Toast.makeText(MenuPrincipal.this, texto, Toast.LENGTH_LONG);
//                        toast.show();
//                        Intent i = new Intent(getApplicationContext(),Manteni_SubCategoria.class);
//                        i.putExtra("estadocate",elegido.getEstado());
//                        i.putExtra("imagen",elegido.getImagen());
//                        i.putExtra("nombreCate",elegido.getNombre());
//                        i.putExtra("idcategoria",elegido.getIdcategoria());
//                        i.putExtra("idcliente",idcliente);
//                        i.putExtra("cod",recuperado);
//                        startActivity(i);
                    }
                });

            }
        }

    }

}
