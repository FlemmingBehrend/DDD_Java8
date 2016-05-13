# Domain

The domain layer is the **heart of the software**, and this is where the interesting stuff happens. :)

The ubiquitous language is used in classes, interfaces and method signatures, and every concept in here is familiar to a expert in the stakeholder (interessent) domain. There is one package per aggregate, and to each aggregate belongs entities, value objects, domain events, a repository interface and sometimes factories.

The structure and naming of aggregates, classes and methods in the domain layer should follow the ubiquitous language, and you should be able to explain to a domain expert how this part of the software works by drawing a few simple diagrams and using the actual class and method names of the source code.

## Domain elements overview

Domain Element | Life cycle control | Behavior | State
---------------|--------------------|----------|------
Aggregate |X|X|X
Entity |X|X|X
Value Object ||X|X
DTO ||X
Service ||X|
Repository ||X|
