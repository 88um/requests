
# JRequest
 
 - JRequest is a java.net API Wrapper built in my spare time for fun and practice
 
 - This project will be maintained as long as I keep learning more Java




# How to use

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
// Make sure session is set to true

JRequest request = new JRequest(true); 

// Make your request

String response = request.get("https://instagram.com");

// Print Cookie List<String,String>

System.out.println(request.Cookies()); // ==> [mid=YoSKjgAEAAG6lpegfp4VzvWUqxC4, csrftoken=zLHbcevdAersj5DchUi3IVPI1peAOiKD]

// Session feature beta for now
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
# Logging
``` Java
JRequest request = new JRequest(false); 

request.get("https://api.ipify.org");

request.post("https://instagram.com",headers,data);

// Print responses for both requests

System.out.println(request.Logs()); ==>
          /**
           * GET Response <[200]> ("https://api.ipify.org); Status.OK
           * POST Response <[200]> ("https://instagram.com); Status.OK
           */
```

# Contributors

- * [Joshua Solo](https://github.com/88um)

# Extra

- I will continue updating this repo until I get bored

- Feel free to open any issues/pull requests
