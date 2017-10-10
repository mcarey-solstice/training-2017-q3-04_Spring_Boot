DROP TABLE IF EXISTS `snippet`;

CREATE TABLE `snippet` (
  `id`       VARCHAR(36)  NOT NULL,
  `title`    VARCHAR(200) NOT NULL,
  `code`     VARCHAR(500) DEFAULT NULL,
  `created`  DATE         NOT NULL,
  `modified` DATE         NOT NULL,
  PRIMARY KEY (`id`)
);