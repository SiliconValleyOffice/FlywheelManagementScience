CREATE TABLE `FlywheelTeamCommunityMember` (
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `FlywheelTeam__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `team_member_status` CHAR(8) NOT NULL ,
  `allocation_percentage` FLOAT NOT NULL  ,
  `proposed_by` CHAR(40) NOT NULL ,
  `proposed_datetime` BIGINT(14) NOT NULL ,
  `confirmed_by` CHAR(40) NULL ,
  `confirmed_datetime` BIGINT(14) NULL ,
  `super_user` TINYINT NULL ,
  PRIMARY KEY (`CommunityMember__id`, `FlywheelTeam__id`) ,
  CONSTRAINT `fk_FlywheelTeam_has_CommunityMember_CommunityMember`
    FOREIGN KEY (`CommunityMember__id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FlywheelTeam_has_CommunityMember_FlywheelWorkspace`
    FOREIGN KEY (`FlywheelTeam__id` )
    REFERENCES `FlywheelTeam` (`_id` ) ,
  CONSTRAINT `fk_FlywheelWorkspace_has_CommunityMember_TeamMemberStatus_enum`
    FOREIGN KEY (`team_member_status` )
    REFERENCES `TeamMemberStatus` (`team_member_status` ),
  CONSTRAINT `fk_FlywheelTeamLinkToCommunityMember_CommunityMember`
    FOREIGN KEY (`proposed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FlywheelTeamLinkToCommunityMember_CommunityMember2`
    FOREIGN KEY (`confirmed_by` )
    REFERENCES `CommunityMember` (`_id` ) );
