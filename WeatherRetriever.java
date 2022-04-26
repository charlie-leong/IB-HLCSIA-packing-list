import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRetriever {

    public JSONObject getWeather(String city){
        try{
            //hardcoded key password to access the weather API
            String app_key= "0750ce16a3ac4ddc7a6222ba45ad9bcf";
            //set up new URL link to connect to the API using the password and destination as inputs
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?appid="+ app_key +"&q="+ city);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            //setting up http url connection
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);


            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                //"response" used to store the contents from the api response
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                //reading the response line by line and appending what is necessary to "response"
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());

                }
                JSONObject obj = new JSONObject(response.toString());

                return obj;
            }

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public double getTemperature(String city){
        JSONObject obj = getWeather(city);
        //converting the temperature accessed from kelvin to celsius
        return (double) obj.getJSONObject("main").get("temp")-273.18;

    }


    public String getTempCategory(String destination){
         int celsius = (int)(getTemperature(destination));
        String temperature = "";

        //assigns and returns a temperature category based on the celsius temperature
        if(celsius >= 28){
            temperature = "hot";
        }else if(celsius >= 23){
            temperature = "lukewarm";
        }else if(celsius >= 15){
            temperature = "hk_cold";
        }else if(celsius >= 0){
            temperature = "cold";
        }
        return temperature;
    }

    public String getWeatherCondition(String city) {

        JSONObject obj = getWeather(city);
        JSONArray arr = obj.getJSONArray("weather");
        for (int i = 0; i < arr.length(); i++) {
            return "\nweather description: " + arr.getJSONObject(i).getString("description");
        }
        return null;

    }
}
