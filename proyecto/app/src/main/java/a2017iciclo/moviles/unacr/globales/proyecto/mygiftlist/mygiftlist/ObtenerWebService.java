package a2017iciclo.moviles.unacr.globales.proyecto.mygiftlist.mygiftlist;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jdani on 14/5/2017.
 */

public class ObtenerWebService extends AsyncTask<String, Integer, String> {
    String resultado="";
    @Override
    protected String doInBackground(String... params) {

        String cadena = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

        //http://maps.googleapis.com/maps/api/geocode/json?latlng=38.404593,%20-0.529534&sensor=false
        cadena = cadena + params[0];
        cadena = cadena + ",%20";
        cadena = cadena + params[1];
        cadena = cadena + "&sensor=false";


        String devuelve = "";

        URL url = null; // Url de donde queremos obtener información
        try {
            url = new URL(cadena);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
            connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
            //connection.setHeader("content-type", "application/json");

            int respuesta = connection.getResponseCode();
            StringBuilder result = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK){


                InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                // StringBuilder.

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);        // Paso toda la entrada al StringBuilder
                }

                //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                //Accedemos al vector de resultados
                JSONArray resultJSON = respuestaJSON.getJSONArray("results");   // results es el nombre del campo en el JSON

                //Vamos obteniendo todos los campos que nos interesen.
                //En este caso obtenemos la primera dirección de los resultados.
                String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
                if (resultJSON.length()>0){
                    direccion = resultJSON.getJSONObject(0).getString("formatted_address");
                    // dentro del results pasamos a Objeto la seccion formated_address
                }
                devuelve = "Dirección: " + direccion;   // variable de salida que mandaré al onPostExecute para que actualice la UI

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return devuelve;
    }

    @Override
    protected void onCancelled(String aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        resultado = aVoid;
        //super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
        resultado="";
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}

