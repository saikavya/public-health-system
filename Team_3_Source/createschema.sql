CREATE SCHEMA project2;

USE project2;

CREATE TABLE `disease` (
  `disease_id` int(11) NOT NULL DEFAULT '0',
  `disease_description` varchar(45) NOT NULL,
  PRIMARY KEY (`disease_id`),
  UNIQUE KEY `disease_description_UNIQUE` (`disease_description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `location` (
  `location_id` int(11) NOT NULL DEFAULT '0',
  `location_name` varchar(45) NOT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `location_name_UNIQUE` (`location_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `occupation` (
  `occ_id` int(11) NOT NULL DEFAULT '0',
  `occup_description` varchar(45) NOT NULL,
  PRIMARY KEY (`occ_id`),
  UNIQUE KEY `occup_description_UNIQUE` (`occup_description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `survey` (
  `sur_id` int(11) NOT NULL DEFAULT '0',
  `loc_id` int(11) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  `population(million)` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sur_id`),
  KEY `loc_id2_idx` (`loc_id`),
  CONSTRAINT `loc_id2` FOREIGN KEY (`loc_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `citizen` (
  `cit_id` int(11) NOT NULL DEFAULT '0',
  `sur_id` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `ethnicity` varchar(45) DEFAULT NULL,
  `marital_stat` varchar(45) DEFAULT NULL,
  `fast_food_weekly` int(11) DEFAULT NULL,
  `fruits_weekly` int(11) DEFAULT NULL,
  `alcohol` tinyint(4) DEFAULT NULL,
  `smoke` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`cit_id`),
  KEY `sur_id_idx` (`sur_id`),
  CONSTRAINT `sur_id` FOREIGN KEY (`sur_id`) REFERENCES `survey` (`sur_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dis_cit` (
  `dis_id` int(11) NOT NULL,
  `cit_id` int(11) NOT NULL,
  PRIMARY KEY (`dis_id`,`cit_id`),
  KEY `cit_id_idx` (`cit_id`),
  CONSTRAINT `cit_id1` FOREIGN KEY (`cit_id`) REFERENCES `citizen` (`cit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dis_id` FOREIGN KEY (`dis_id`) REFERENCES `disease` (`disease_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `healthcare` (
  `hc_id` int(11) NOT NULL DEFAULT '0',
  `hc_type` varchar(45) DEFAULT NULL,
  `loc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`hc_id`),
  KEY `loc_id_idx` (`loc_id`),
  CONSTRAINT `loc_id1` FOREIGN KEY (`loc_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `loc_accident` (
  `loc_id` int(11) DEFAULT NULL,
  `acc_id` int(11) NOT NULL DEFAULT '0',
  `acc_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `loc_id_idx` (`loc_id`),
  CONSTRAINT `loc_id` FOREIGN KEY (`loc_id`) REFERENCES `location` (`location_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `loc_env` (
  `id` int(11) NOT NULL,
  `loc_id` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `pollen_conc%` varchar(45) DEFAULT NULL,
  `co_conc%` varchar(45) DEFAULT NULL,
  PRIMARY KEY disease (`id`),
  KEY `loc_id4_idx` (`loc_id`),
  CONSTRAINT `loc_id4` FOREIGN KEY (`loc_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `occ_cit` (
  `occ_id` int(11) NOT NULL,
  `cit_id` int(11) NOT NULL,
  PRIMARY KEY (`occ_id`,`cit_id`),
  KEY `cit_id_idx` (`cit_id`),
  CONSTRAINT `cit_id` FOREIGN KEY (`cit_id`) REFERENCES `citizen` (`cit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `occ_id` FOREIGN KEY (`occ_id`) REFERENCES `occupation` (`occ_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;