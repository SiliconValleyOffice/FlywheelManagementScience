CREATE TABLE `HeadlineNodeTrash` (
    `_id` CHAR(40) NOT NULL,
    `row_timestamp` BIGINT(14) NULL,
    `parent_id` CHAR(40) NOT NULL,
    `parent_node_type_code` CHAR(3) NOT NULL,
    `searchable_headline` VARCHAR(256) NOT NULL,
    `deleted_by` CHAR(40) NOT NULL,
    `deleted_timestamp` BIGINT(14) NOT NULL,
    `parent_abbreviated_node_id` CHAR(9) NOT NULL,
    `serialized_headline_node` TEXT NOT NULL,
    `serialized_audit_block` TEXT NOT NULL,
    `serialized_completion` TEXT NULL,
    `serialized_fse_document` TEXT NOT NULL,
    `serialized_governance` TEXT NOT NULL,
    `serialized_tribkn_quality` TEXT NOT NULL,
    `serialized_work_task_budget` TEXT NULL,
    PRIMARY KEY (`_id`) );