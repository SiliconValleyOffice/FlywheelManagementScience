CREATE TABLE `FunctionalTeamCommunityMember` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` DATETIME NOT NULL DEFAULT 0,
  `FunctionalTeam__id` CHAR(40) NOT NULL,
  `CommunityMember__id` CHAR(40) NOT NULL,
  `team_member_status` CHAR(8) NOT NULL,
  `proposed_by` CHAR(40) NOT NULL,
  `proposed_datetime` BIGINT(14) NOT NULL,
  `confirmed_by` CHAR(40) NULL,
  `confirmed_datetime` BIGINT(14) NULL,
  `super_user` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_FunctionalTeam_has_CommunityMember_FunctionalTeam1`
    FOREIGN KEY (`FunctionalTeam__id`)
    REFERENCES `FunctionalTeam` (`_id`),
  CONSTRAINT `fk_FunctionalTeam_has_CommunityMember_CommunityMember1`
    FOREIGN KEY (`CommunityMember__id`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_FunctionalTeam_CommunityMember_TeamMemberStatus1`
    FOREIGN KEY (`team_member_status`)
    REFERENCES `TeamMemberStatus` (`team_member_status`),
  CONSTRAINT `fk_FunctionalTeamCommunityMember_CommunityMember1`
    FOREIGN KEY (`proposed_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_FunctionalTeamCommunityMember_CommunityMember2`
    FOREIGN KEY (`confirmed_by`)
    REFERENCES `CommunityMember` (`_id`) );