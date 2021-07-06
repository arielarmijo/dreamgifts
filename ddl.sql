CREATE TABLE IF NOT EXISTS `dreamgifts`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_unique` (`nombre` ASC) INVISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(150) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `celular` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `correo_unique` (`correo` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`bancos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_unique` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`comunas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_unique` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`rrss` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_unique` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`estados_venta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_unique` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`proveedores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rut` VARCHAR(45) NOT NULL,
  `razon_social` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `rut_unique` (`rut` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dreamgifts`.`categoria_articulo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  UNIQUE INDEX `codigo_unique` (`codigo` ASC) INVISIBLE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;






