# Live Coding Demos

Live Coding Demos for OAuth 2.0 & OpenID Connect with Spring Security 5.

This includes the following demos:

* [auth-code-demo](https://github.com/andifalk/owasp-chapter-munich-04-2019/tree/master/live-demos/auth-code-demo): Demo client to demonstrate all steps of an authorization code flow
* [client](https://github.com/andifalk/owasp-chapter-munich-04-2019/tree/master/live-demos/client): A client application build with spring security 5 that calls the resource server (see next line)
* [resource-server](https://github.com/andifalk/owasp-chapter-munich-04-2019/tree/master/live-demos/resource-server): A resource server that can be called by the client application

## Setting up JBoss Keycloak

To successfully run the demos an identity provider is required.
Here we use JBoss Keycloak as identity provider.

To install this please follow these steps:

1. Download keycloak from [here (zip archive)](https://downloads.jboss.org/keycloak/6.0.1/keycloak-6.0.1.zip).
2. Extract the downloaded zip file  in a directory of your choice.
3. Download the preconfigured [keycloak configuration](https://github.com/andifalk/owasp-chapter-munich-04-2019/raw/master/live-demos/keycloak/data.zip)
4. Extract the contents of the _data.zip_ file into the subdirectory __standalone__ of the directory
you have chosen in step 2
5. Now keycloak is ready to be started. To start it run _standalone.sh_ or _standalone.bat_ scripts in _bin_ sub directory).

After start of keycloak has finished the admin console is reachable at [http://localhost:8080/auth](http://localhost:8080/auth).

To login into keycloak just use admin/admin as credentials.

