CREATE TABLE `CommunityMember` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `community_member_status` CHAR(8) NOT NULL ,
  `aggregate_team_allocation` FLOAT NOT NULL ,
  `authentication_uid` VARCHAR(45) NULL ,
  `given_name` VARCHAR(24) NULL ,
  `family_name` VARCHAR(24) NULL ,
  `nick_name` VARCHAR(16) NULL ,
  `email_address` VARCHAR(45) NULL ,
  `cell_phone` VARCHAR(45) NULL ,
  `work_phone` VARCHAR(45) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_CommunityMember_CommunityMemberStatus_enum1`
    FOREIGN KEY (`community_member_status` )
    REFERENCES `CommunityMemberStatus` (`community_member_status` ) );
