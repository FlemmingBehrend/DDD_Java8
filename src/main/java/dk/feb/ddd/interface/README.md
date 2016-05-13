# Interfaces

This package should contain interfaces to the outside world, meaning everything that interacts with other systems, such as web services, RMI interfaces or web applications, and batch processing frontends.

In this layer we expose our REST services to the frontend angular application.

In this layer we handle
* Interpretation of incomming data
* Validation of incomming data
* Translation of incomming data
* Serialization of outgoing data (JSON/XML)
* Usage of DTO's for exposing a subset of the internal model

One *very* important thing is that the domain internals are never exposed by this layer. To be more exact lets look at a REST example. Error handling have been omitted in the following examples.
Lets say we have a domain object called person.

### Bad way of doing it
```java
@GET
@Path("/person/{id}")
@Produces("application/json")
public Response getPerson(@PathParam("id") String id) {
  Person p = // ... some code to fetch a person, not relevent to this example
  return Response.ok(p).build();
}
```
**never return a representation of the internal domain model in the Response object. Changes to your internal model view bleed through to the json object returned by the getPerson method**

### God way of doing it
```java
@GET
@Path("/person/{id}")
@Produces("application/json")
public Response getPerson(@PathParam("id") String id) {
  Person p = // ... some code to fetch a person, not relevent to this example
  JsonObjectBuilder personBuilder = Json.createObjectBuilder()
    .add("firstname", p.getFirstname())
    .add("lastname", p.getLastname())
    ... more attributes mapped

  return Response.ok(personBuilder.build()).build();
}
```
**this will shield your internal model, remember that your internal model is exactly that, YOUR internal model, keep it that way and avoid your model to bleed.**

> There is no official naming standard for json property names, but we follow the google standard descriped here : https://google.github.io/styleguide/jsoncstyleguide.xml
