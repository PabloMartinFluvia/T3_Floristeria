SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `floristeria` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `floristeria`.`floristerias` (
  `floristeria_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_floristeria` VARCHAR(65) NOT NULL,
  `valorStocksTotal` DECIMAL(19,2) NOT NULL DEFAULT 0,
  `valorTicketsTotal` DECIMAL(19,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`floristeria_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `floristeria`.`conjuntos` (
  `conjunto_id` INT(11) NOT NULL AUTO_INCREMENT,
  `valor_Productos` DECIMAL(19,2) NOT NULL DEFAULT 0,
  `floristeria_id` INT(11) NOT NULL,
  PRIMARY KEY (`conjunto_id`, `floristeria_id`),
  INDEX `fk_conjuntos_floristerias_idx` (`floristeria_id` ASC) VISIBLE,
  CONSTRAINT `fk_conjuntos_floristerias`
    FOREIGN KEY (`floristeria_id`)
    REFERENCES `floristeria`.`floristerias` (`floristeria_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `floristeria`.`productos` (
  `producto_id` INT(11) NOT NULL AUTO_INCREMENT,
  `categoria` ENUM('ARBOL', 'FLOR', 'DECORACION') NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `precio` DECIMAL(13,2) NOT NULL,
  `altura` ENUM('MUY_ALTO', 'ALTO', 'MEDIANO', 'BAJO', 'MUY_BAJO') NULL DEFAULT NULL,
  `color` VARCHAR(20) NULL DEFAULT NULL,
  `material` ENUM('MADERA', 'PLASTICO') NULL DEFAULT NULL,
  PRIMARY KEY (`producto_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `floristeria`.`conjuntos_has_products` (
  `conjunto_id` INT(11) NOT NULL,
  `producto_id` INT(11) NOT NULL,
  `cantidad` INT(11) NOT NULL,
  INDEX `fk_conjuntos_has_products_conjuntos1_idx` (`conjunto_id` ASC) VISIBLE,
  INDEX `fk_conjuntos_has_products_productos1_idx` (`producto_id` ASC) VISIBLE,
  PRIMARY KEY (`conjunto_id`, `producto_id`),
  CONSTRAINT `fk_conjuntos_has_products_conjuntos1`
    FOREIGN KEY (`conjunto_id`)
    REFERENCES `floristeria`.`conjuntos` (`conjunto_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_conjuntos_has_products_productos1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `floristeria`.`productos` (`producto_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
