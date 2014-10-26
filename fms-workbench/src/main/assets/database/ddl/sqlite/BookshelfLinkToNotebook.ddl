CREATE TABLE `BookshelfLinkToNotebook` (
  `id` CHAR(40) NOT NULL,
  `Bookshelf__id` CHAR(40) NOT NULL,
  `Notebook__id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Bookshelf_has_Notebook_Bookshelf`
    FOREIGN KEY (`Bookshelf__id`)
    REFERENCES `Bookshelf` (`_id`)
  CONSTRAINT `fk_Bookshelf_has_Notebook_Notebook`
    FOREIGN KEY (`Notebook__id`)
    REFERENCES `Notebook` (`_id`) );