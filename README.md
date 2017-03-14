# Graph CMS

Java based content management system based on graph technologies

## Philosophy

We finally need semantics in web applications. Millions of content management systems are doing the same thing: provide pre defined list and single views in a more or less hierarchical way. GraphCMS just connects nodes with relationships. Simple. Better.

## Goals

* Graphify the content
* Graph model (node and relationship types) freely editable
* Lightweight
* Works out of the box

## Technology Stack

* Spring Boot
* Neo4j
  * stores nodes and relationships
  * stores the graph model
* Spring Security
  * Google and Github authentication for now


## Runtime Environment

* Docker

## Components

* Node Type Management
* Node Type Renderer
* Relationship Type Management
* Relationship Type Renderer
* Modeler for node and relationship types

## Roles

* The user can view content nodes and their relationships
* The editor can create, modify and delete content nodes of a specific node type and their relationships
* The admin can create, modify and delete node and relationship types
