# Graph CMS

  ____                 _      ____ __  __ ____  
 / ___|_ __ __ _ _ __ | |__  / ___|  \/  / ___| 
| |  _| '__/ _` | '_ \| '_ \| |   | |\/| \___ \ 
| |_| | | | (_| | |_) | | | | |___| |  | |___) |
 \____|_|  \__,_| .__/|_| |_|\____|_|  |_|____/ 
                |_|                             

Java based content management system based on graph technologies

## Philosophy

We finally need semantics in web applications. Millions of content management systems are doing the same thing: provide pre defined list and single views in a more or less hierarchical way. GraphCMS just connects nodes with relationships. Simple. Better.

## Goals

* Graphify the content
* Graph model (node and relationship types) freely editable
* Lightweight
* Works out of the box
* Use proper standards if possible (JCR 2.0, BPMN 2.0, OAuth 2.0)

## Technology Stack

* Spring Boot
* Neo4j
  * Stores the graph model
  * Stores nodes and relationships
* Spring Security
  * Google and Github authentication for now
* Camunda
  * BPMN 2.0 workflow engine
* ModeShape
  * DMS storage (JCR 2.0)

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
