
# JRequest
 
 - JRequest is a for-fun project designed to be a capable java.net API wrapper
 
 - This repo will continually be updated with any additions that come to mind

 - Feel free to open any issues/pull requests



# Get method

``` Java
// Choose wether to keep session cookies or not

JRequest request = new JRequest(true); 

// Set your request url

String url = "https://api.ipify.org";

// If there are no headers

String response = request.get(url);

// else

HashMap<String,String> headers = new HashMap<String,String>();

headers.put("content-type","application/json");

String response = request.get(url,headers);

System.out.println(response); // ==> GET Response <[200]> ("https://api.ipify.org); Status.OK

// Print Response Body

System.out.println(request.Text()); // ==> 178.22.34.59

```

# Post method

``` Java
// Choose wether to keep session cookies or not

JRequest request = new JRequest(true); 

// Set your request url

String url = "https://jsonplaceholder.typicode.com/posts";

// Set your data

HashMap data = new HashMap();
data.put("id",1);
data.put("userId",101);
data.put("body","word");
data.put("title","iheartjoshua<3");

// If there are no headers

String response = request.post(url,null,data);

// else

String response = request.post(url,headers,data);

System.out.println(response);  // ==> POST Response <[201]> ("https://jsonplaceholder.typicode.com/posts"); Status.OK

// Print response JSON

System.out.println(request.json()); // ==> {"id":101,"body":"word","title":"iheartjoshua<3","userId":101}
```

# Cookies
``` Java
// Session can be set to true or false

JRequest request = new JRequest(true); /*Setting this to true allows you to use the same cookies per request
                                        * Setting this to false allows you to make fresh requests with different cookies*/

// Make your request

String response = request.get("https://instagram.com");

// Print Cookie List<HttpCookie>

System.out.println(request.Cookies()); // ==> [mid=YoSKjgAEAAG6lpegfp4VzvWUqxC4, csrftoken=zLHbcevdAersj5DchUi3IVPI1peAOiKD]

```

# Proxies
``` Java
JRequest request = new JRequest(false); 

//Before you make your request, set a proxy.

request.setProxy("66.35.28.230",8080);

request.get("https://api.ipify.org");

// Print response body

System.out.println(request.Text()); // ==> "66.35.28.230"

// Note: Only non-authenticated http proxies are supported.
```

# Persistent Headers
``` Java
JRequest request = new JRequest(false);

// Create a new headers map

HashMap headers = new HashMap();
headers.put("User-Agent","Mozilla/6.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");

// Set the request class header to our hasmap

request.headers = data;

// Make a request

request.get("https://www.whatsmyua.info/api/v1/ua?");
System.out.println(request.Text()); // ==> Mozilla/6.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36

// Remove the class headers and make a request

request.headers=null;
request.get("https://www.whatsmyua.info/api/v1/ua?");
System.out.println(request.Text()); // ==> Java/17.0.2
```


# Logging
``` Java
JRequest request = new JRequest(false); 

request.get("https://api.ipify.org");

request.post("https://instagram.com",headers,data);

// Print response for last request

System.out.println(request.LastLog()); // ==> POST Response <[200]> ("https://instagram.com); Status.OK

// Print responses for both requests

System.out.println(request.Logs()); 
          /**
           * GET Response <[200]> ("https://api.ipify.org); Status.OK
           * POST Response <[200]> ("https://instagram.com); Status.OK
           */
```

# Contributors

- * [Joshua Solo](https://github.com/88um)

