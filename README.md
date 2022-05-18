
# Sphinx++
- Sphinx++ is an enhancement of https://github.com/iiKillerxSG/Sphinx

- It is a java.net API Wrapper built in my spare time for fun and practice




# How to use

# Get method

``` Java
// Choose wether to keep session cookies or not

Sphinx request = new Sphinx(true); 

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

Sphinx request = new Sphinx(true); 

// Set your request url

String url = "https://api.ipify.org";

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

System.out.println(response);  // ==> POST Response <[201]> ("https://api.ipify.org); Status.OK

// Print response JSON

System.out.println(request.json()); // ==> {"id":101,"body":"word","title":"iheartjoshua<3","userId":101}
```

# Cookies
``` Java
// Make sure session is set to true

Sphinx request = new Sphinx(true); 

// Make your request

String response = request.get("https://instagram.com");

// Print Cookie List<String,String>

System.out.println(request.Cookies()); // ==> [mid=YoSKjgAEAAG6lpegfp4VzvWUqxC4, csrftoken=zLHbcevdAersj5DchUi3IVPI1peAOiKD]

// Session feature beta for now
```

# Proxies
``` Java
Sphinx request = new Sphinx(false); 

//Before you make your request, set a proxy.

request.setProxy("66.35.28.230",8080);

request.get("https://api.ipify.org");

// Print response body

System.out.println(request.Text()); // ==> "66.35.28.230"

// Note: Only non-authenticated http proxies are supported.
```


# Contributors

- * [Joshua Solo](https://github.com/88um)

# Extra

- I will continue updating this repo until I get bored

- Feel free to open any issues/pull requests
