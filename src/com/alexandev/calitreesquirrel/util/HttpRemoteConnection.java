package com.alexandev.calitreesquirrel.util;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.util.Log;

public class HttpRemoteConnection {

    protected static DefaultHttpClient httpClient = new DefaultHttpClient();

    private InputStream inputStream = null;

    public HttpRemoteConnection()
    {
    }
    
    public JSONObject getStreamFromUrl( String url, List<NameValuePair> params )
    {// Making HTTP request
        
    	JSONObject jObj = null;
        String json = "";
    	
    	try
        {
            HttpPost httpPost = new HttpPost( url );

            if( params != null )
                httpPost.setEntity( new UrlEncodedFormEntity( params ) );
            
            HttpResponse httpResponse = httpClient.execute( httpPost );
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
            
            
            
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                		inputStream, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                inputStream.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
     
            // try parse the string to a JSON object
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            
            
        }
        catch( Exception e )
        {
            Log.e( "log_tag", "Error in http connection " + e.toString() );
        }

        // return Input Stream
        return jObj;
    }

}
