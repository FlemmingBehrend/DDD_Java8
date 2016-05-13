# Application

The application layer is responsible for driving the workflow of the application, matching the use cases at hand. Here recide code that the application needs to perform its business tasks. These operations are interface-independent and can be both synchronous or message-driven. This layer is well suited for spanning transactions, high-level logging and security.

It coordinates the domain layer objects to perform the actual tasks.

In this layer you will implement code that coordinate business tasks spanning multiple aggregates.
