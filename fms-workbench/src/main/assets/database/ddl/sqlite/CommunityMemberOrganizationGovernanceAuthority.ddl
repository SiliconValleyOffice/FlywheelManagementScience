CREATE TABLE `CommunityMemberOrganizationGovernanceAuthority` (
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `FmsOrganization__id` CHAR(40) NOT NULL ,
  `governance_target` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `can_be_sponsor` TINYINT NULL ,
  `can_be_facilitator` TINYINT NULL ,
  `can_be_customer` TINYINT NULL ,
  `can_be_administrator` TINYINT NULL ,
  `can_sponsor_sponsor` TINYINT NULL ,
  `can_sponsor_facilitator` TINYINT NULL ,
  `can_sponsor_customer` TINYINT NULL ,
  `can_sponsor_administrator` TINYINT NULL ,
  PRIMARY KEY (`CommunityMember__id`, `FmsOrganization__id`, `governance_target`) ,
  CONSTRAINT `fk_OrganizationCommunityMemberLinkToOrganizationFmsGovernance`
    FOREIGN KEY (`FmsOrganization__id` , `CommunityMember__id` )
    REFERENCES `OrganizationCommunityMember` (`FmsOrganization__id` , `CommunityMember__id` ) ,
  CONSTRAINT `fk_OrganizationCommunityMemberLinkToOrganizationGovernanceTar`
    FOREIGN KEY (`governance_target` )
    REFERENCES `OrganizationGovernanceTarget` (`governance_target` ) );
