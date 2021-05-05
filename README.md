# MarvelProject
test using marvel api


# Script Data Base MySQL

CREATE TABLE `sys`.`marvel_character` (
  `id_marvel_character` BIGINT(100) NOT NULL,
  `name` VARCHAR(100) NULL,
  `resource_uri` VARCHAR(250) NULL,
  `last_sync` DATETIME NULL,
  PRIMARY KEY (`id_marvel_character`));


CREATE TABLE `sys`.`comic` (
  `id_comic` BIGINT(100) NOT NULL,
  `name` VARCHAR(250) NULL,
  `resource_uri` VARCHAR(250) NULL,
  `id_marvel_character` BIGINT(100) NULL,
  PRIMARY KEY (`id_comic`),
  INDEX `id_marvel_character_idx` (`id_marvel_character` ASC) VISIBLE,
  CONSTRAINT `id_marvel_character`
    FOREIGN KEY (`id_marvel_character`)
    REFERENCES `sys`.`marvel_character` (`id_marvel_character`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `sys`.`creator` (
  `id_creator` BIGINT(100) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `rol` VARCHAR(45) NULL,
  `id_comic` BIGINT(100) NULL,
  PRIMARY KEY (`id_creator`),
  INDEX `id_comic_idx` (`id_comic` ASC) VISIBLE,
  CONSTRAINT `id_comic_creator`
    FOREIGN KEY (`id_comic`)
    REFERENCES `sys`.`comic` (`id_comic`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `sys`.`co_worker` (
  `id_co_worker` BIGINT(100) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `id_comic` BIGINT(100) NULL,
  PRIMARY KEY (`id_co_worker`),
  INDEX `id_comic_idx` (`id_comic` ASC) VISIBLE,
  CONSTRAINT `id_comic_co_worker`
    FOREIGN KEY (`id_comic`)
    REFERENCES `sys`.`comic` (`id_comic`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


# Credentials Marvel API

public key 
5d0ae517240b160a02ec0bfc41da0b17

private key 
fe63d4ab470d3a90d1e9d7154a320402f00a2720

# Scheduler

Ejecuta de manera diaria la sincronización con el api de marvel y guarda y actualiza los registros en BD

# Services

Servicios de consulta de la información almacenada en la BD despues de la sincronización
- Se puede accecer mediante /swagger-ui.html para validar los servicios
 

