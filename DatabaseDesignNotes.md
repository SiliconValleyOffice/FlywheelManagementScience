# Introduction #

Various patterns and design decisions for the FMM database.

# Development #

## Model Driven Design ##

No DDL will be authored by hand.

All database design and DDL generation will be done using the MySQL Workbench.

Change process:
  * modify the latest data model (fmm.mwb) from the svn repository
  * review changes with a collaborator
  * generate/update ddl into the ddl directory


# Tables #

## Completion Node tables ##

These tables represent _completable_ nodes in the FMM.  This is how value is generated and delivered to the organization/customer.

  * WorkTask
  * WorkPlan
  * Sprint
  * FiscalYear

  * WorkPackage
  * Project Asset
  * Project
  * Portfolio

  * Milestone

  * Bookshelf
  * Notebook
  * DiscussionTopic

### Completion Commitment tables ###

There are 2 tables that represent nodes for which a Completion Commitment can be made:
  * MilestoneCommitment - for completion of a ProjectAsset
  * SprintCommitment - for completion of a WorkPackage or a ProjectAsset

## Organizational Node tables ##

These tables define the topology of the organization.

  * Organization
  * FlywheelWorkspace
  * CommunityMember

## Fragment tables ##

A subset of the tables store polymorphic _data fragments_ for the primary FMM nodes.  These tables are:
  * Governance
  * CompletionCommitment
  * Story
  * Notes
