/**
 *
 * @author joshua solo
 * 
 */
import java.io.*;
import java.util.*;
import java.net.*;
import org.json.*;


public class JRequest {
        private String Text;
        private int Code;
        private JSONObject Json;
        private String logs="";
        private String lastLog = "";
        private boolean session;
        private CookieManager cookieManager;
        private CookieStore cookieStore;
        private List<HttpCookie> cookies;
        private String cookieString;
        private Proxy proxy = null;
        public HashMap headers;
      
    
        public JRequest(boolean session){
            if (session){
            this.session = true;
            this.cookieManager = new CookieManager();
            CookieHandler.setDefault(this.cookieManager);

            }
        }
    
        private String log(String logg){
            if (logg.contains("20")){
                logg+="Status.OK\n";
            }else if (logg.contains("400")){
                logg+="Status.FAIL\n";
            }else if (logg.contains("403")|| logg.contains("401")){
                logg+="Status.UNAUTHORIZED\n";
            }else if (logg.contains("404")){
                logg+="Status.NOT_FOUND\n";
            }else if (logg.contains("405")){
                logg+="Status.NOT_ALLOWED\n";
            }else if (logg.contains("407")){
                logg+="Status.PROXY_AUTH_ERROR\n";
            }else if (logg.contains("500")){
                logg+="Status.SERVER_ERROR\n";
            }
            this.lastLog = logg;
            this.logs+=logg;
            return logg;
        }
    
        private String mapToJson(HashMap map){
            String convertedJson = null;
            try{
                JSONObject json = new JSONObject(map);
                convertedJson = json.toString();
            }catch (Exception e){}
            return convertedJson;
    
        }
        
        private void setCookies(){
            try{
                this.cookieString = "";
                for (HttpCookie cookie : this.cookies){
                    this.cookieString+=cookie.toString() + "; ";
                }
            }catch (Exception e){this.cookieString=null;}
        }



        public int Code(){
            return this.Code;
        }
    
        public String Logs(){
            return this.logs;
        }
    
        public String LastLog(){
            return this.lastLog;
        }
    
        public String Text(){
            return this.Text;
        }
    
        public JSONObject json(){
            try{
                this.Json = new JSONObject(this.Text);
            }catch(Exception e){e.printStackTrace();this.Json = null;}
            return this.Json;
        }
    
        public List<HttpCookie> Cookies(){
            if (this.cookieStore!=null){
            cookies = this.cookieStore.getCookies();}
            return cookies;
        }
    
        public void setProxy(String proxyurl,int port){
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyurl, port)); 
        }
    
        public void removeProxy(){
            this.proxy=null;
        }
    
        public String get(String myurl) {
            StringBuffer builder = new StringBuffer();
            BufferedReader reader;
            String lines;
            String logg=null;
            HttpURLConnection connection;
            if (!this.session){this.cookieManager = new CookieManager();CookieHandler.setDefault(this.cookieManager);}
            try {
                URL url = new URL(myurl);
                if (this.proxy!=null){connection = (HttpURLConnection) url.openConnection(proxy);}
                else{connection = (HttpURLConnection) url.openConnection();}
                if (this.session && this.cookieString!=null){connection.setRequestProperty("Cookie", this.cookieString);}
                if (this.headers != null){
                    Object[] headersKey = headers.keySet().toArray();
                    for(int i = 0; i<headersKey.length; i++){
                        Object key = headersKey[i];
                        Object value = headers.get(key);
                        connection.setRequestProperty(key.toString(),value.toString());}
                }
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setDoInput(true);
                connection.connect();
                this.cookieStore = this.cookieManager.getCookieStore();
                this.Code = connection.getResponseCode();
                setCookies();
                logg = log(String.format("GET Response <[%d]> (%s); ", this.Code,myurl));
                if (this.Code > 299) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((lines = reader.readLine()) != null) {
                       builder.append(lines.trim());
                    }
                    reader.close();
                }else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    
                    while ((lines = reader.readLine()) != null) {
                        builder.append(lines.trim());
                    }
                    reader.close();
                }
    
                this.Text = builder.toString();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            return logg;
        }
    
        public String get(String myurl,HashMap headers){
            StringBuffer builder = new StringBuffer();
            BufferedReader reader;
            String lines;
            String logg=null;
            HttpURLConnection connection;
            if (!this.session){this.cookieManager = new CookieManager();CookieHandler.setDefault(this.cookieManager);}
            if (this.headers!=null){headers=this.headers;}
            try {
                URL url = new URL(myurl);
                if (this.proxy!=null){connection = (HttpURLConnection) url.openConnection(proxy);}
                else{connection = (HttpURLConnection) url.openConnection();}
                if (this.session && this.cookieString!=null){connection.setRequestProperty("Cookie", this.cookieString);}

                Object[] headersKey = headers.keySet().toArray();
                for(int i = 0; i<headersKey.length; i++){
                    Object key = headersKey[i];
                    Object value = headers.get(key);
                    connection.setRequestProperty(key.toString(),value.toString());}

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setDoInput(true);
                connection.connect();

                this.cookieStore = this.cookieManager.getCookieStore();
                this.Code = connection.getResponseCode();
                setCookies();

                logg = log(String.format("GET Response <[%d]> (%s); ", this.Code,myurl));
                if (this.Code > 299) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((lines = reader.readLine()) != null) {
                        builder.append(lines.trim());
                    }
                    reader.close();
                }else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    
                    while ((lines = reader.readLine()) != null) {
                        builder.append(lines.trim());
                    }
                    reader.close();
                }
    
                this.Text = builder.toString();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            return logg;
        }
        
    
        public String post(String myurl,HashMap headers,HashMap data){
            StringBuffer builder = new StringBuffer();
            BufferedReader reader;
            String lines;
            String logg=null;
            HttpURLConnection connection;
            if (!this.session){this.cookieManager = new CookieManager();CookieHandler.setDefault(this.cookieManager);}
            if (this.headers!=null){headers=this.headers;}
            try {
                String post_data = mapToJson(data);
                URL url = new URL(myurl);
                if (this.proxy!=null){connection = (HttpURLConnection) url.openConnection(proxy);}
                else{connection = (HttpURLConnection) url.openConnection();}
                if (headers != null) {
                    Object[] headersKey = headers.keySet().toArray();
                        for(int i = 0; i<headersKey.length; i++){
                            Object key = headersKey[i];
                            Object value = headers.get(key);
                            connection.setRequestProperty(key.toString(),value.toString());}}
                if (this.session && this.cookieString!=null){connection.setRequestProperty("Cookie", this.cookieString);}
                
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(5000);
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.connect();
    
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(post_data.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                this.cookieStore = this.cookieManager.getCookieStore();
                this.Code = connection.getResponseCode();
                setCookies();
                logg = log(String.format("POST Response <[%d]> (%s); ", this.Code,myurl));
                if (this.Code > 299) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((lines = reader.readLine()) != null) {
                        builder.append(lines.trim());
                    }
                    reader.close();
                }else {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    
                    while ((lines = reader.readLine()) != null) {
                        builder.append(lines.trim());
                    }
                    reader.close();
                }
    
                this.Text = builder.toString();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        return logg;
        
    
            }

        public String post(String myurl,HashMap data){
                StringBuffer builder = new StringBuffer();
                BufferedReader reader;
                String lines;
                String logg=null;
                HttpURLConnection connection;
                if (!this.session){this.cookieManager = new CookieManager();CookieHandler.setDefault(this.cookieManager);}
                try {
                    String post_data = mapToJson(data);
                    URL url = new URL(myurl);
                    if (this.proxy!=null){connection = (HttpURLConnection) url.openConnection(proxy);}
                    else{connection = (HttpURLConnection) url.openConnection();}
                    if (this.headers != null) {
                        HashMap headers=this.headers;
                        Object[] headersKey = headers.keySet().toArray();
                            for(int i = 0; i<headersKey.length; i++){
                                Object key = headersKey[i];
                                Object value = headers.get(key);
                                connection.setRequestProperty(key.toString(),value.toString());}}
                    if (this.session && this.cookieString!=null){connection.setRequestProperty("Cookie", this.cookieString);}
                    
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setConnectTimeout(5000);
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.connect();
        
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(post_data.getBytes("UTF-8"));
                    outputStream.flush();
                    outputStream.close();
    
                    this.cookieStore = this.cookieManager.getCookieStore();
                    this.Code = connection.getResponseCode();
                    setCookies();
                    logg = log(String.format("POST Response <[%d]> (%s); ", this.Code,myurl));
                    if (this.Code > 299) {
                        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        while ((lines = reader.readLine()) != null) {
                            builder.append(lines.trim());
                        }
                        reader.close();
                    }else {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
                        while ((lines = reader.readLine()) != null) {
                            builder.append(lines.trim());
                        }
                        reader.close();
                    }
        
                    this.Text = builder.toString();
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            return logg;
            
        
                }
    
}



