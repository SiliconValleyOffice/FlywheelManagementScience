CREATE TABLE `FunctionalTeamCommunityMember` (
  `FunctionalTeam__id` CHAR(40) NOT NULL ,
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `team_member_status` CHAR(8) NOT NULL ,
  PRIMARY KEY (`FunctionalTeam__id`, `CommunityMember__id`) ,
  CONSTRAINT `fk_FunctionalTeam_has_CommunityMember_FunctionalTeam`
    FOREIGN KEY (`FunctionalTeam__id` )
    REFERENCES `FunctionalTeam` (`_id` ) ,
  CONSTRAINT `fk_FunctionalTeam_has_CommunityMember_CommunityMember`
    FOREIGN KEY (`CommunityMember__id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FunctionalTeam_CommunityMember_TeamMemberStatus`
    FOREIGN KEY (`team_member_status` )
    REFERENCES `TeamMemberStatus` (`team_member_status` ) );
