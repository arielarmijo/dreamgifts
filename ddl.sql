-- MySQL Script generated by MySQL Workbench
-- Sun Aug  8 12:58:51 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dreamgifts
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dreamgifts
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dreamgifts` DEFAULT CHARACTER SET utf8 ;
USE `dreamgifts` ;

-- -----------------------------------------------------
-- Table `dreamgifts`.`comunas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`comunas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(5) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(150) NOT NULL,
  `comuna_id` INT NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `celular` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `rut_UNIQUE` (`rut` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  INDEX `fk_clientes_comunas_idx` (`comuna_id` ASC) VISIBLE,
  CONSTRAINT `fk_clientes_comunas1`
    FOREIGN KEY (`comuna_id`)
    REFERENCES `dreamgifts`.`comunas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`bancos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`bancos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(5) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`rrss`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`rrss` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(5) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`estados_venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`estados_venta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(5) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(150) NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`packs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`packs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL,
  `costo` INT NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 1,
  `stock_critico` INT NULL DEFAULT 0,
  `fecha_inicio` DATE NULL,
  `fecha_termino` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`ventas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NULL,
  `total` INT NULL,
  `fecha_venta` DATE NULL,
  `fecha_transferencia` DATE NULL,
  `codigo_transferencia` INT NULL,
  `banco_id` INT NULL,
  `nombre_destinatario` VARCHAR(45) NULL,
  `apellido_destinatario` VARCHAR(45) NULL,
  `direccion_destinatario` VARCHAR(150) NULL,
  `comuna_id` INT NULL,
  `telefono_destinatario` VARCHAR(15) NULL,
  `fecha_entrega` DATE NULL,
  `hora_entrega_inicial` TIME NULL,
  `hora_entrega_final` TIME NULL,
  `saludo` VARCHAR(255) NULL,
  `rrss_id` INT NULL,
  `estado_venta_id` INT NULL DEFAULT 1,
  `pack_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ventas_clientes_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_ventas_bancos_idx` (`banco_id` ASC) VISIBLE,
  INDEX `fk_ventas_rrss_idx` (`rrss_id` ASC) VISIBLE,
  INDEX `fk_ventas_estados_venta_idx` (`estado_venta_id` ASC) VISIBLE,
  INDEX `fk_ventas_comunas_idx` (`comuna_id` ASC) VISIBLE,
  INDEX `fk_ventas_packs_idx` (`pack_id` ASC) VISIBLE,
  CONSTRAINT `fk_ventas_clientes1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `dreamgifts`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_bancos1`
    FOREIGN KEY (`banco_id`)
    REFERENCES `dreamgifts`.`bancos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_rrss1`
    FOREIGN KEY (`rrss_id`)
    REFERENCES `dreamgifts`.`rrss` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_estados_venta1`
    FOREIGN KEY (`estado_venta_id`)
    REFERENCES `dreamgifts`.`estados_venta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_comunas1`
    FOREIGN KEY (`comuna_id`)
    REFERENCES `dreamgifts`.`comunas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ventas_packs1`
    FOREIGN KEY (`pack_id`)
    REFERENCES `dreamgifts`.`packs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`categorias_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`categorias_articulo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(5) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`articulos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL DEFAULT 0,
  `fecha_vencimiento` DATE NULL,
  `estado` TINYINT NOT NULL DEFAULT 1,
  `categoria_articulo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_articulos_categorias_articulo_idx` (`categoria_articulo_id` ASC) INVISIBLE,
  CONSTRAINT `fk_articulos_categorias_articulo`
    FOREIGN KEY (`categoria_articulo_id`)
    REFERENCES `dreamgifts`.`categorias_articulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`proveedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`proveedores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(15) NOT NULL,
  `razon_social` VARCHAR(45) NOT NULL,
  `contacto` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(250) NOT NULL,
  `comuna_id` INT NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `rut_UNIQUE` (`rut` ASC) VISIBLE,
  INDEX `fk_proveedores_comunas_idx` (`comuna_id` ASC) VISIBLE,
  CONSTRAINT `fk_proveedores_comunas1`
    FOREIGN KEY (`comuna_id`)
    REFERENCES `dreamgifts`.`comunas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`pack_has_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`pack_has_articulo` (
  `pack_id` INT NOT NULL,
  `articulo_id` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`pack_id`, `articulo_id`),
  INDEX `fk_pack_has_articulo_articulo_idx` (`articulo_id` ASC) VISIBLE,
  INDEX `fk_pack_has_articulo_pack_idx` (`pack_id` ASC) INVISIBLE,
  CONSTRAINT `fk_pack_has_articulo_pack`
    FOREIGN KEY (`pack_id`)
    REFERENCES `dreamgifts`.`packs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pack_has_articulo_articulo`
    FOREIGN KEY (`articulo_id`)
    REFERENCES `dreamgifts`.`articulos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`ordenes_compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`ordenes_compra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_orden` DATE NOT NULL,
  `proveedor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ordenes_compra_proveedores_idx` (`proveedor_id` ASC) VISIBLE,
  CONSTRAINT `fk_ordenes_compra_proveedores1`
    FOREIGN KEY (`proveedor_id`)
    REFERENCES `dreamgifts`.`proveedores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`facturas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `orden_compra_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_facturas_ordenes_compra_idx` (`orden_compra_id` ASC) VISIBLE,
  UNIQUE INDEX `numero_UNIQUE` (`numero` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_ordenes_compra1`
    FOREIGN KEY (`orden_compra_id`)
    REFERENCES `dreamgifts`.`ordenes_compra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`factura_detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`factura_detalle` (
  `articulo_id` INT NOT NULL,
  `factura_id` INT NOT NULL,
  `codigo` VARCHAR(45) NOT NULL,
  `cantidad` INT NOT NULL,
  `valor_unitario` INT NOT NULL,
  `fecha_vencimiento` DATE NULL,
  PRIMARY KEY (`articulo_id`, `factura_id`),
  INDEX `fk_factura_detalle_facturas_idx` (`factura_id` ASC) VISIBLE,
  INDEX `fk_factura_detalle_articulos_idx` (`articulo_id` ASC) VISIBLE,
  CONSTRAINT `fk_factura_detalle_articulos1`
    FOREIGN KEY (`articulo_id`)
    REFERENCES `dreamgifts`.`articulos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_detalle_facturas1`
    FOREIGN KEY (`factura_id`)
    REFERENCES `dreamgifts`.`facturas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dreamgifts`.`orden_compra_detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dreamgifts`.`orden_compra_detalle` (
  `orden_compra_id` INT NOT NULL,
  `articulo_id` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`orden_compra_id`, `articulo_id`),
  INDEX `fk_orden_compra_detalle_articulos_idx` (`articulo_id` ASC) INVISIBLE,
  INDEX `fk_orden_compra_detalle_ordenes_compra_idx` (`orden_compra_id` ASC) VISIBLE,
  CONSTRAINT `fk_orden_compra_detalle_ordenes_compra1`
    FOREIGN KEY (`orden_compra_id`)
    REFERENCES `dreamgifts`.`ordenes_compra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orden_compra_detalle_articulos1`
    FOREIGN KEY (`articulo_id`)
    REFERENCES `dreamgifts`.`articulos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;