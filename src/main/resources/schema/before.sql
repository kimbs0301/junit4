CREATE TABLE `player` (
  `player_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `age` INT(11) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `player`(`name`, `age`) VALUES ("홍길동",41);
INSERT INTO `player`(`name`, `age`) VALUES ("일지매",44);
INSERT INTO `player`(`name`, `age`) VALUES ("이순신",34);
INSERT INTO `player`(`name`, `age`) VALUES ("세종대왕",35);
INSERT INTO `player`(`name`, `age`) VALUES ("광개토",45);
INSERT INTO `player`(`name`, `age`) VALUES ("왕건",34);
INSERT INTO `player`(`name`, `age`) VALUES ("임꺽정",37);
