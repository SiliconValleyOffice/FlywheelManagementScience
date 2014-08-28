CREATE TABLE `OrganizationCommunityMember` (
  `Organization__id` CHAR(40) NOT NULL ,
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `OrganizationParticipation_organization_participation` TEXT NOT NULL ,
  `TeamMemberStatus_team_member_status` CHAR(8) NOT NULL ,
  `proposed_by` CHAR(40) NOT NULL ,
  `proposed_timestamp` BIGINT(14) NOT NULL ,
  `confirmed_by` CHAR(40) NULL ,
  `confirmed_timestamp` BIGINT(14) NULL ,
  `authentication_uid` VARCHAR(45) NULL ,
  `is_super_user` TINYINT NULL ,
  PRIMARY KEY (`Organization__id`, `CommunityMember__id`) ,
  CONSTRAINT `fk_Organization_CommunityMember_Organization1`
    FOREIGN KEY (`Organization__id` )
    REFERENCES `FmsOrganization` (`_id` ) ,
  CONSTRAINT `fk_Organization_CommunityMember_CommunityMember1`
    FOREIGN KEY (`CommunityMember__id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_OrganizationLinkToCommunityMember_CommunityMember1`
    FOREIGN KEY (`proposed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_OrganizationLinkToCommunityMember_TeamMemberStatus1`
    FOREIGN KEY (`TeamMemberStatus_team_member_status` )
    REFERENCES `TeamMemberStatus` (`team_member_status` ) ,
  CONSTRAINT `fk_OrganizationLinkToCommunityMember_CommunityMember2`
    FOREIGN KEY (`confirmed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_OrganizationCommunityMember_OrganizationParticipation1`
    FOREIGN KEY (`OrganizationParticipation_organization_participation` )
    REFERENCES `OrganizationParticipation` (`organization_participation` ) );
    