## Resource Server

The resource server provides two REST API endpoints:

* [localhost:9091/hello-server/hello-user](http://localhost:9091/hello-server/hello-user)
* [localhost:9091/hello-server/hello-admin](http://localhost:9091/hello-server/hello-admin)

Both endpoints are secured and require a JWT access token being transmitted as bearer token header.
The first endpoint requires the 'user' or 'admin' scope, the second endpoint is only accessible for 'admin' scope.  
