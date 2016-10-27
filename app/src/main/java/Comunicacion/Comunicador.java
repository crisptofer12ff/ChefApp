package Comunicacion;

import android.content.Context;
import android.net.*;
import android.net.http.AndroidHttpClient;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;

/**
 * Created by erick on 10/26/2016.
 */
public class Comunicador {

    static InputStream GET(String direccionURL) throws IOException {
        URL url = new URL(direccionURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());}
        finally {
            urlConnection.disconnect();
        }
        return in;
    }

    static void POST(String direccionURL, String JSON) throws IOException {
        String urlParameters  = "param1=" + URLEncoder.encode(JSON, "UTF-8");
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
        URL url = new URL(direccionURL);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
        conn.setUseCaches(false);

        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
            wr.write( postData );
        }

    }

    static boolean verificarConexion(Context contexto) {

        ConnectivityManager connMgr = (ConnectivityManager)
                contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }

        else{
            return false;
        }
    }

}
