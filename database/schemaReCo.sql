SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `ReCoBD` ;
CREATE SCHEMA IF NOT EXISTS `ReCoBD` DEFAULT CHARACTER SET utf8 ;
USE `ReCoBD` ;

-- -----------------------------------------------------
-- Table `ReCoBD`.`MTipoUsuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MTipoUsuario` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MTipoUsuario` (
  `id_tus` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de cada tipo de usuario' ,
  `des_tus` VARCHAR(45) NULL COMMENT '***Descripción del tipo de usuario\n' ,
  `tie_sem` INT NOT NULL COMMENT '***Tiempo de sesión de cada usuario expresado en minutos\n' ,
  PRIMARY KEY (`id_tus`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MUsuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MUsuario` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MUsuario` (
  `id_usu` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de usuario' ,
  `nic_usu` VARCHAR(20) NOT NULL COMMENT '*Nic de usuario\n' ,
  `con_usu` VARCHAR(20) NOT NULL ,
  `id_tus` INT NULL COMMENT '**Identificador de cada tipo de usuario' ,
  PRIMARY KEY (`id_usu`, `nic_usu`) ,
  INDEX `fk_MDatosDeAutentificacion_CTipoUsuario1_idx` (`id_tus` ASC) ,
  CONSTRAINT `fk_id_tus`
    FOREIGN KEY (`id_tus` )
    REFERENCES `ReCoBD`.`MTipoUsuario` (`id_tus` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MAlumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MAlumno` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MAlumno` (
  `id_alu` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de alumno' ,
  `id_usu` INT NULL COMMENT '**Identificador de usuario' ,
  `apa_alu` VARCHAR(60) NOT NULL COMMENT '***Apellido paterno de alumno' ,
  `ama_alu` VARCHAR(60) NOT NULL COMMENT '***Apellido materno de alumno' ,
  `nom_alu` VARCHAR(60) NOT NULL COMMENT '***Nombre(s) de alumno' ,
  `bol_alu` INT NOT NULL COMMENT '***Numero de boleta del alumno' ,
  `gpo_alu` VARCHAR(45) NOT NULL COMMENT '***Grupo del alumno' ,
  `cor_usu` VARCHAR(60) NOT NULL COMMENT '***Correo de alumno' ,
  PRIMARY KEY (`id_alu`) ,
  INDEX `fk_MAlumno_MUsuario1_idx` (`id_usu` ASC) ,
  CONSTRAINT `fk_MAlumno_MUsuario1`
    FOREIGN KEY (`id_usu` )
    REFERENCES `ReCoBD`.`MUsuario` (`id_usu` )
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MProfesor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MProfesor` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MProfesor` (
  `id_pro` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de profesor' ,
  `id_usu` INT NULL COMMENT '**Identificador de usuario' ,
  `apa_pro` VARCHAR(60) NOT NULL COMMENT '***Apellido paterno de profesor' ,
  `ama_pro` VARCHAR(60) NOT NULL COMMENT '***Apellido materno de profesor' ,
  `nom_pro` VARCHAR(60) NOT NULL COMMENT '***Nombre(s) de profesor' ,
  `cor_pro` VARCHAR(60) NOT NULL COMMENT '***Correo de profesor' ,
  PRIMARY KEY (`id_pro`) ,
  INDEX `fk_MProfesor_MUsuario1_idx` (`id_usu` ASC) ,
  CONSTRAINT `fk_MProfesor_MUsuario1`
    FOREIGN KEY (`id_usu` )
    REFERENCES `ReCoBD`.`MUsuario` (`id_usu` )
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`EReporte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`EReporte` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`EReporte` (
  `id_ece` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador del encabezado del reporte' ,
  `id_alu` INT NULL COMMENT '**Identificador del alumno' ,
  `id_pro` INT NULL COMMENT '**Identificador de profesor' ,
  PRIMARY KEY (`id_ece`) ,
  INDEX `fk_EAutorizacionReporte_MAlumno1_idx` (`id_alu` ASC) ,
  INDEX `fk_EReporte_MProfesor1_idx` (`id_pro` ASC) ,
  CONSTRAINT `fk_EReporte_MAlumno1`
    FOREIGN KEY (`id_alu` )
    REFERENCES `ReCoBD`.`MAlumno` (`id_alu` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_EReporte_MProfesor1`
    FOREIGN KEY (`id_pro` )
    REFERENCES `ReCoBD`.`MProfesor` (`id_pro` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MLaboratorio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MLaboratorio` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MLaboratorio` (
  `id_lab` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador del Laboratorio' ,
  `nom_lab` VARCHAR(45) NULL DEFAULT NULL COMMENT '***Nombre del laboratorio' ,
  PRIMARY KEY (`id_lab`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MEquipo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MEquipo` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MEquipo` (
  `nom_equ` VARCHAR(10) NOT NULL COMMENT '*Nomenclatura del equipo' ,
  `id_lab` INT NULL COMMENT '*Identificador del Laboratorio' ,
  PRIMARY KEY (`nom_equ`) ,
  INDEX `fk_MEquipo_MLaboratorio1_idx` (`id_lab` ASC) ,
  CONSTRAINT `fk_MEquipo_MLaboratorio1`
    FOREIGN KEY (`id_lab` )
    REFERENCES `ReCoBD`.`MLaboratorio` (`id_lab` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`CEstadoReporte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`CEstadoReporte` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`CEstadoReporte` (
  `id_ere` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador del estado de reporte' ,
  `des_ere` VARCHAR(45) NOT NULL COMMENT '***Descripción del estado de reporte' ,
  PRIMARY KEY (`id_ere`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`CTipoFalla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`CTipoFalla` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`CTipoFalla` (
  `id_tfa` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador del tipo de falla' ,
  `des_tfa` VARCHAR(45) NOT NULL COMMENT '***Descripción del tipo de falla' ,
  `tip_fal` TINYINT NULL COMMENT '***Tipo de falla, en caso de ser de software tendrá 0 y de hardware 1' ,
  PRIMARY KEY (`id_tfa`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MReporte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MReporte` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MReporte` (
  `id_rep` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de reporte' ,
  `des_rep` VARCHAR(200) NOT NULL COMMENT '***Descripción del reporte' ,
  `fec_rep` DATETIME NOT NULL COMMENT '***Fecha del reporte' ,
  `nom_asi` VARCHAR(45) NULL COMMENT '***Nombre de la asignatura que se cursaba cuando se levantó el reporte' ,
  `id_ece` INT NULL COMMENT '**Identificador del encabezado del reporte' ,
  `id_ere` INT NULL COMMENT '**Identificador del estado de reporte' ,
  `id_tfa` INT NULL COMMENT '**Identificador del tipo de falla' ,
  `nom_equ` VARCHAR(10) NULL COMMENT '**Nomenclatura del equipo' ,
  PRIMARY KEY (`id_rep`) ,
  INDEX `fk_MReporte_DAutorizacionReporte1_idx` (`id_ece` ASC) ,
  INDEX `fk_MReporte_CEstadoReporte1_idx` (`id_ere` ASC) ,
  INDEX `fk_MReporte_CTipoFalla1_idx` (`id_tfa` ASC) ,
  INDEX `fk_MReporte_MEquipo1_idx` (`nom_equ` ASC) ,
  CONSTRAINT `fk_MReporte_DAutorizacionReporte1`
    FOREIGN KEY (`id_ece` )
    REFERENCES `ReCoBD`.`EReporte` (`id_ece` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MReporte_CEstadoReporte1`
    FOREIGN KEY (`id_ere` )
    REFERENCES `ReCoBD`.`CEstadoReporte` (`id_ere` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MReporte_CTipoFalla1`
    FOREIGN KEY (`id_tfa` )
    REFERENCES `ReCoBD`.`CTipoFalla` (`id_tfa` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MReporte_MEquipo1`
    FOREIGN KEY (`nom_equ` )
    REFERENCES `ReCoBD`.`MEquipo` (`nom_equ` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MTecnico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MTecnico` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MTecnico` (
  `id_tec` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador del tecnico' ,
  `apa_tec` VARCHAR(60) NOT NULL COMMENT '***Apellido paterno de tecnico' ,
  `ama_tec` VARCHAR(60) NOT NULL COMMENT '***Apellido materno de tecnico' ,
  `nom_tec` VARCHAR(60) NOT NULL COMMENT '***Nombre(s) del tecnico' ,
  `cor_tec` VARCHAR(60) NOT NULL COMMENT '***Correo de tecnico' ,
  `id_usu` INT NULL COMMENT '*Identificador de usuario' ,
  PRIMARY KEY (`id_tec`) ,
  INDEX `fk_MTecnico_MUsuario1_idx` (`id_usu` ASC) ,
  CONSTRAINT `fk_MTecnico_MUsuario1`
    FOREIGN KEY (`id_usu` )
    REFERENCES `ReCoBD`.`MUsuario` (`id_usu` )
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`MReparacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`MReparacion` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`MReparacion` (
  `id_rci` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de reparación' ,
  `des_req` VARCHAR(45) NOT NULL COMMENT '***Descripción de la reparación' ,
  `fec_req` DATETIME NOT NULL COMMENT '***Fecha de la reparación' ,
  `id_rep` INT NULL COMMENT '**Identificador del reporte previo a la reparación' ,
  `nom_equ` VARCHAR(10) NULL COMMENT '**Nomenclatura del equipo' ,
  `id_tec` INT NULL COMMENT '**Identificador de tecnico' ,
  PRIMARY KEY (`id_rci`) ,
  INDEX `fk_MReparacion_MReporte1_idx` (`id_rep` ASC) ,
  INDEX `fk_MReparacion_MEquipo1_idx` (`nom_equ` ASC) ,
  INDEX `fk_MReparacion_MTecnico1_idx` (`id_tec` ASC) ,
  CONSTRAINT `fk_MReparacion_MReporte1`
    FOREIGN KEY (`id_rep` )
    REFERENCES `ReCoBD`.`MReporte` (`id_rep` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MReparacion_MEquipo1`
    FOREIGN KEY (`nom_equ` )
    REFERENCES `ReCoBD`.`MEquipo` (`nom_equ` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_MReparacion_MTecnico1`
    FOREIGN KEY (`id_tec` )
    REFERENCES `ReCoBD`.`MTecnico` (`id_tec` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`CPermiso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`CPermiso` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`CPermiso` (
  `id_per` INT NOT NULL AUTO_INCREMENT COMMENT '*Identificador de permiso' ,
  `des_per` VARCHAR(45) NULL COMMENT '***Descripción del permiso' ,
  PRIMARY KEY (`id_per`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ReCoBD`.`EPermisoUsuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ReCoBD`.`EPermisoUsuario` ;

CREATE  TABLE IF NOT EXISTS `ReCoBD`.`EPermisoUsuario` (
  `id_tus` INT NOT NULL COMMENT '**Identificador de cada tipo de usuario' ,
  `id_per` INT NOT NULL COMMENT '**Identificador de permiso' ,
  INDEX `fk_CTipoUsuario_has_CPermisos_CPermisos1_idx` (`id_per` ASC) ,
  INDEX `fk_CTipoUsuario_has_CPermisos_CTipoUsuario1_idx` (`id_tus` ASC) ,
  CONSTRAINT `fk_CTipoUsuario_has_CPermisos_CTipoUsuario1`
    FOREIGN KEY (`id_tus` )
    REFERENCES `ReCoBD`.`MTipoUsuario` (`id_tus` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_CTipoUsuario_has_CPermisos_CPermisos1`
    FOREIGN KEY (`id_per` )
    REFERENCES `ReCoBD`.`CPermiso` (`id_per` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `ReCoBD` ;

-- -----------------------------------------------------
-- Placeholder table for view `ReCoBD`.`VProfesor_EReporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ReCoBD`.`VProfesor_EReporte` (`id_ece` INT, `nom_pro` INT);

-- -----------------------------------------------------
-- View `ReCoBD`.`VProfesor_EReporte`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `ReCoBD`.`VProfesor_EReporte` ;
DROP TABLE IF EXISTS `ReCoBD`.`VProfesor_EReporte`;
USE `ReCoBD`;
CREATE  OR REPLACE VIEW `ReCoBD`.`VProfesor_EReporte` AS SELECT id_ece, nom_pro FROM MProfesor JOIN EReporte ON MProfesor.id_pro = EReporte.id_pro;
;


INSERT INTO MTipoUsuario (des_tus, tie_sem) VALUES ("Administrador",0);
INSERT INTO MUsuario (nic_usu, con_usu, id_tus) VALUES ("admin", "root", 1);
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
