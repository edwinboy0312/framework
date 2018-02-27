package utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;


public class ApiUtilities {   


  public String postEntity(String url,String sXML,HashMap<String,String> mapSetReqProp) throws Exception {

    try {       
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      
      con.setDoOutput(true);
      con.setDoInput(true);

      if(!mapSetReqProp.isEmpty()){
        for (Entry<String, String> entry : mapSetReqProp.entrySet()) {
          con.setRequestProperty(entry.getKey(),entry.getValue());                   
        }   
      }                           

      con.setRequestMethod("POST");           
      OutputStream out = con.getOutputStream();
      if(!sXML.isEmpty())
        out.write(sXML.getBytes());
      out.flush();
      out.close();

      int responseCode = con.getResponseCode();
      BufferedReader in;
      if (responseCode != 200) {
        in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        return "";
      } else {
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      String strResponse = response.toString();
      return strResponse;

    } catch (Exception e) {

      System.out.println("Error in POST method" + e.getMessage());
      return "";
    }
  }

  public String putEntity(String url,String sXML,HashMap<String,String> mapSetReqProp) throws Exception {

    try {       
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      
      con.setDoOutput(true);
      con.setDoInput(true);

      if(!mapSetReqProp.isEmpty()){
        for (Entry<String, String> entry : mapSetReqProp.entrySet()) {
          con.setRequestProperty(entry.getKey(),entry.getValue());                   
        }   
      }                           

      con.setRequestMethod("PUT");           
      OutputStream out = con.getOutputStream();
      if(!sXML.isEmpty())
        out.write(sXML.getBytes());
      out.flush();
      out.close();

      int responseCode = con.getResponseCode();
      BufferedReader in;
      if (responseCode != 200) {
        in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        return "";
      } else {
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      String strResponse = response.toString();
      return strResponse;

    } catch (Exception e) {

      System.out.println("Error in Put method" + e.getMessage());
      return "";
    }
  }
  

  public String getEntity(String url,HashMap<String,String> mapSetReqProp) {

    try {   

      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      if(!mapSetReqProp.isEmpty()){
        for (Entry<String, String> entry : mapSetReqProp.entrySet()) {
          con.setRequestProperty(entry.getKey(),entry.getValue());                   
        }   
      }                           

      con.setRequestMethod("GET");        
      int responseCode = con.getResponseCode();
      BufferedReader in;

      if (responseCode != 200) {
        in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        return "";
      } else {

        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      String sResponse = response.toString();
      return sResponse;

    } catch (Exception e) {
      System.out.println("Error in GET method" + e.getMessage());
      return "";
    }
    
    
  }




}
