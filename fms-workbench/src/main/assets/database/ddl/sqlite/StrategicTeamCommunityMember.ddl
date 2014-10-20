CREATE TABLE `StrategyTeamCommunityMember` (
  `StrategyTeam__id` CHAR(40) NOT NULL ,
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `team_member_status` CHAR(8) NOT NULL ,
  PRIMARY KEY (`StrategyTeam__id`, `CommunityMember__id`) ,
  CONSTRAINT `fk_GovernanceTeam_has_CommunityMember_GovernanceTeam`
    FOREIGN KEY (`StrategyTeam__id` )
    REFERENCES `StrategyTeam` (`_id` ) ,
  CONSTRAINT `fk_GovernanceTeam_has_CommunityMember_CommunityMember`
    FOREIGN KEY (`CommunityMember__id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_StrategyTeam_CommunityMember_TeamMemberStatus`
    FOREIGN KEY (`team_member_status` )
    REFERENCES `TeamMemberStatus` (`team_member_status` ) );
