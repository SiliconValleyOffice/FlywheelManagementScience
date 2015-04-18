# Introduction #

A Node Frag(ment) is a polymorphic collection of data members and functionality that implements an FMM horizontal functionality across many of the FMM Nodes.  A good example is Governance, which will be the example for this document.

Each Node Frag is implemented by a class.  The Node Frag also has an interface that is implemented by each FMM Node that leverages that horizontal functionality.


# Collaboration #

  * interface - FmmNodeGovernance
  * implementation - NodeFragGovernance
  * wrapper class - FmmGovernableNodeImpl
  * client classes (not an FMM Node) that do not extend the super class - FseDocument, FseDocumentView
  * underlying database table (when using a database) - NodeGragGovernance
  * DAO class - NodeFragGovernanceDaoSqLite, NodeFragGovernanceDaoMySql

Using Governance as an example, these are the collaborations:
  * FmmGovernableNode
  * NodeFragGovernance

# Interface #

In the case of our Governance example, this is FmmNodeGovernance.

# Implementation #

In the case of our Governance example, this is NodeFragGovernance.

This class implements the data members and functionality needed to support Governance in the Flywheel Management Model.

# Wrapper Class #

In the case of our Governance example, this is FmmGovernableNodeImpl.


This class appears in the class hierarchy for FMM implementation.  Super class is FmmHeadlineNodeImpl.