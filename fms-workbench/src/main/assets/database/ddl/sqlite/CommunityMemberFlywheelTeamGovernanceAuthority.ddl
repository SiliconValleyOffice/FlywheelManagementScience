CREATE TABLE `CommunityMemberFlywheelTeamGovernanceAuthority` (
  `FlywheelTeam__id` CHAR(40) NOT NULL ,
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `governance_target` CHAR(18) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `sponsor_` TINYINT NULL ,
  `facilitator` TINYINT NULL ,
  `customer` TINYINT NULL ,
  `administrator` TINYINT NULL ,
  PRIMARY KEY (`FlywheelTeam__id`, `CommunityMember__id`, `governance_target`) ,
  CONSTRAINT `fk_FlywheelTeam_GovernanceAuthority_FlywheelTeam_CommunityMem1`
    FOREIGN KEY (`CommunityMember__id` , `FlywheelTeam__id` )
    REFERENCES `FlywheelTeamCommunityMember` (`CommunityMember__id` , `FlywheelTeam__id` ) ,
  CONSTRAINT `fk_CommunityMemberFlywheelTeamGovernanceAuthority_GovernanceT1`
    FOREIGN KEY (`governance_target` )
    REFERENCES `GovernanceTarget` (`governance_target` ) );
