# Infrastructure

This layer acts as a supporting library for all the other layers.

It provides communication between layers, implements persistence for business objects, contains supporting libraries for the user interface layer, etc.

Also, we consider code and configuration files that glues the other layers to the infrastructure as part of the infrastructure layer. Looking for example at the persistence aspect, the database schema definition, Hibernate specific files and implementations of the repository interfaces are part of the infrastructure layer.

JPA mapping annotations can be placed on the model in the domain layer.

While it can be tricky to give a solid definition of what kind of code belongs to the infrastructure layer for any given situation, it should be possible to completely stub out the infrastructure in pure Java unit/scenario tests and still be able to use the domain layer and possibly the application layer to work out the core business problems.
