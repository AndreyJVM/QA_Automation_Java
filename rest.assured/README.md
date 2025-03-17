###  [RestAssured ](https://rest-assured.io/)

Testing and validating REST services in Java is harder than in dynamic languages such as Ruby and Groovy.
REST Assured brings the simplicity of using these languages into the Java domain.

### API
Application Programming Interface, or API, is a way for different software components to communicate and exchange data. 
APIs are used to create powerful, reliable, secure, and capable applications that meet the needs of users. 
They are everywhere, constantly running in the background, providing digital capabilities that are essential to our modern lives.

APIs operate on the principle of information exchange between programs, systems, and devices.
This process takes place in the form of a loop consisting of a request and a response. The user launches a request to receive data by interacting with the application.
The request is passed to the API, which extracts the data and sends it back to the user.

The key point to start with is understanding the concept of **client-server architecture**. In this architecture, 
the client plays the role of data recipient, and the server plays the role of information provider.

#### Client
A client is a system or application that accesses a remote system for information. 
The client sends a request to the server and receives a response.

#### Server
A server is a computer system or application that serves as a data source. It is capable of transmitting 
information over a network, including the Internet. The functionality provided by the server is called services.
These services are provided to customers in response to their requests.

---

### HTTP Response Codes

* **1xx - Informational responses.**
* **2xx - Successfully processed responses.**
  * **200 OK** - the request was completed successfully.
  * **201 Created** - the request was successful and the resource was created.
  * **202 Accepted** - the server accepted the client's request, but did not complete its processing.
* **3xx - Redirection.**
  * **301 Moved Permanently** - The requested resource has been permanently moved to another URL, and the client must access the new URL to access the resource.
  * **302 Found** - the resource has been temporarily moved to another URL, and the client must temporarily use the new URL.
* **4xx - Client errors.**
  * **400 Bad Request** - the request was not executed due to an error in the request, for example, a typo or lack of data.
  * **401 Unauthorized** - the request was not executed because the client did not authenticate or does not have access rights to the requested resource.
  * **403 Forbidden** - the request was not executed because the client is authenticated but not authorized to access the requested resource.
  * **404 Not Found** - the request was not executed because the server could not find the requested resource.
* **5xx - Server errors.**
  * **500 Internal Server Error** - the request was not executed due to an unexpected problem with the server.
  * **502 Bad Gateway** - the request was not executed due to an incorrect response from the upstream server.
  * **503 Service Unavailable** - The server was unable to process the request due to maintenance, overload, or other temporary interference.

---

### SOAP  Simple Object Access Protocol

**Advantages**
* Language and platform independence.
* Connectivity with various transport protocols.
* Built-in error handling.
* A number of security extensions.

**Disadvantages**
* The "_envelope_" tag at the beginning and at the end.
* The text containing the actual request.
* A header for specific information and additional requirements.
* Any errors that occur during processing.

---

### Local book service 

**Start project**
```shell
 sudo docker-compose up -d
```

**Swagger-ui**

```html
http://localhost:8080/rest-api/swagger-ui/#/
```