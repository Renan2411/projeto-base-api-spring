CREATE TABLE tb_usuario
(
    us_id              INTEGER      NOT NULL,
    us_nome            VARCHAR(255) NOT NULL,
    us_cpf             VARCHAR(255) NOT NULL,
    us_email           VARCHAR(255) NOT NULL,
    us_data_nascimento TIMESTAMP(6)
);

ALTER TABLE tb_usuario
    ADD CONSTRAINT tb_usuario_pk PRIMARY KEY (us_id);

ALTER TABLE tb_usuario
    ADD CONSTRAINT uc_us_email UNIQUE (us_email);

ALTER TABLE tb_usuario
    ADD CONSTRAINT uc_us_cpf UNIQUE (us_cpf);