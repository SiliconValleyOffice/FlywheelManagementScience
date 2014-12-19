CREATE TABLE `StrategyTeamCommunityMember` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `StrategyTeam__id` CHAR(40) NOT NULL,
  `CommunityMember__id` CHAR(40) NOT NULL,
  `team_member_status` CHAR(8) NOT NULL,
  `proposed_by` CHAR(40) NOT NULL,
  `proposed_datetime` BIGINT(14) NOT NULL,
  `confirmed_by` CHAR(40) NULL,
  `confirmed_datetime` BIGINT(14) NULL,
  `super_user` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_GovernanceTeam_has_CommunityMember_GovernanceTeam1`
    FOREIGN KEY (`StrategyTeam__id`)
    REFERENCES `StrategyTeam` (`_id`),
  CONSTRAINT `fk_GovernanceTeam_has_CommunityMember_CommunityMember1`
    FOREIGN KEY (`CommunityMember__id`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_StrategyTeam_CommunityMember_TeamMemberStatus1`
    FOREIGN KEY (`team_member_status`)
    REFERENCES `TeamMemberStatus` (`team_member_status`),
  CONSTRAINT `fk_StrategyTeamCommunityMember_CommunityMember1`
    FOREIGN KEY (`proposed_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_StrategyTeamCommunityMember_CommunityMember2`
    FOREIGN KEY (`confirmed_by`)
    REFERENCES `CommunityMember` (`_id`) );