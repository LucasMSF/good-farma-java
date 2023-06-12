CREATE SCHEMA `goodfarma`;

CREATE TABLE `goodfarma`.`product_types` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(80) NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `goodfarma`.`products` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(80) NULL,
    `quantity` INT NULL,
    `product_type_id` INT NOT NULL,
    `price` DOUBLE NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `product_type_id_foreign` FOREIGN KEY (`product_type_id`) REFERENCES `goodfarma`.`product_types` (`id`)
);

CREATE TABLE `goodfarma`.`employees` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(100) NULL,
    `password` VARCHAR(20) NULL,
    `name` VARCHAR(100) NULL,
    `cpf` VARCHAR(20) UNIQUE,
    `telephone` VARCHAR(20) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `goodfarma`.`suppliers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NULL,
    `cnpj` VARCHAR(20) UNIQUE,
    `state` VARCHAR(100) NULL,
    `telephone` VARCHAR(20) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `goodfarma`.`suppliers_products` (
    `id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`),
    CONSTRAINT `idproducts` FOREIGN KEY (`id`) REFERENCES `goodfarma`.`products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `idsuppliers` FOREIGN KEY (`id`) REFERENCES `goodfarma`.`suppliers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO
    `product_types` (`id`, `name`, `description`)
VALUES
    (1, 'Antibióticos', 'Antibióticos em geral');

INSERT INTO
    `product_types` (`id`, `name`, `description`)
VALUES
    (
        2,
        'Higiêne Pessoal',
        'Produtos relacionados a higiêne pessoal'
    );