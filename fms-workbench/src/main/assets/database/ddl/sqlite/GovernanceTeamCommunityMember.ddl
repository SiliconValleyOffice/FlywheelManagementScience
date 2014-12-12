CREATE TABLE `GovernanceTeamCommunityMember` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL,
  `GovernanceTeam__id` CHAR(40) NOT NULL,
  `CommunityMember__id` CHAR(40) NOT NULL,
  `TeamMemberStatus_team_member_status` CHAR(8) NOT NULL,
  `proposed_by` CHAR(40) NOT NULL,
  `proposed_datetime` BIGINT(14) NOT NULL,
  `confirmed_by` CHAR(40) NULL,
  `confirmed_datetime` BIGINT(14) NULL,
  `super_user` TINYINT NOT NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_GovernanceTeamCommunityMember_GovernanceTeam1`
    FOREIGN KEY (`GovernanceTeam__id`)
    REFERENCES `GovernanceTeam` (`_id`),
  CONSTRAINT `fk_GovernanceTeamCommunityMember_CommunityMember1`
    FOREIGN KEY (`CommunityMember__id`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_GovernanceTeamCommunityMember_TeamMemberStatus1`
    FOREIGN KEY (`TeamMemberStatus_team_member_status`)
    REFERENCES `TeamMemberStatus` (`team_member_status`),
  CONSTRAINT `fk_GovernanceTeamCommunityMember_CommunityMember2`
    FOREIGN KEY (`proposed_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_GovernanceTeamCommunityMember_CommunityMember3`
    FOREIGN KEY (`confirmed_by`)
    REFERENCES `CommunityMember` (`_id`) );