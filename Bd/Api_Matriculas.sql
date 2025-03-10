CREATE DATABASE IF NOT EXISTS api_matriculas;
USE api_matriculas;

CREATE TABLE IF NOT EXISTS matriculas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  
    matricula VARCHAR(15) UNIQUE NOT NULL,  
    nome_completo VARCHAR(100) UNIQUE NOT NULL,  
    data_ingresso TIMESTAMP DEFAULT CURRENT_TIMESTAMP  
);

DROP INDEX IF EXISTS idx_nome_completo ON matriculas;
CREATE INDEX idx_nome_completo ON matriculas(nome_completo);

DROP PROCEDURE IF EXISTS inserir_matricula;

DELIMITER $$

CREATE PROCEDURE inserir_matricula(IN p_nome VARCHAR(70))
BEGIN
    DECLARE v_matricula VARCHAR(15);
    DECLARE v_total_alunos INT;

    IF NOT EXISTS (SELECT 1 FROM matriculas WHERE nome_completo = p_nome) THEN
        SET v_total_alunos = (
            SELECT IFNULL(MAX(CAST(SUBSTRING_INDEX(matricula, '.', -1) AS UNSIGNED)), 0) + 1
            FROM matriculas
            WHERE matricula LIKE CONCAT(YEAR(CURDATE()), '.', LPAD(MONTH(CURDATE()), 2, '0'), '.', LPAD(DAY(CURDATE()), 2, '0'), '.%')
        );

        -- Gera a matrícula única no formato: AAAA.MM.DD.XXX
        SET v_matricula = CONCAT(YEAR(CURDATE()), '.', 
                                 LPAD(MONTH(CURDATE()), 2, '0'), '.', 
                                 LPAD(DAY(CURDATE()), 2, '0'), '.', 
                                 LPAD(v_total_alunos, 3, '0'));

        INSERT INTO matriculas (matricula, nome_completo, data_ingresso)
        VALUES (v_matricula, p_nome, CURRENT_TIMESTAMP);
    END IF;
END $$

DELIMITER ;

DROP PROCEDURE IF EXISTS excluir_matricula;

DELIMITER $$

CREATE PROCEDURE excluir_matricula(IN p_id BIGINT)
BEGIN
    IF EXISTS (SELECT 1 FROM matriculas WHERE id = p_id) THEN
        DELETE FROM Dados WHERE matricula_id = p_id;
        DELETE FROM matriculas WHERE id = p_id;
    END IF;
END $$

DELIMITER ;

SELECT * FROM matriculas;