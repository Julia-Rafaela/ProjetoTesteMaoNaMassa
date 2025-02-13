USE MASTER
--DROP DATABASE TesteMaoNaMassa
CREATE DATABASE TesteMaoNaMassa
GO
USE TesteMaoNaMassa


CREATE TABLE Funcionario (
    id INT IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    funcao VARCHAR(50) NOT NULL
)
GO

CREATE PROCEDURE InserirFuncionario
    @nome VARCHAR(100),
    @data_nascimento DATE,
    @salario DECIMAL(10,2),
    @funcao VARCHAR(50),
    @mensagem VARCHAR(255) OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        INSERT INTO Funcionario (nome, data_nascimento, salario, funcao)
        VALUES (@nome, @data_nascimento, @salario, @funcao);

        SET @mensagem = 'Funcionário inserido com sucesso';
    END TRY
    BEGIN CATCH
        SET @mensagem = 'Erro ao inserir funcionário';
    END CATCH
END;
GO

CREATE PROCEDURE DeletarFuncionario
    @id INT,
    @mensagem VARCHAR(255) OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
       
        DELETE FROM Funcionario 
        WHERE id = @id;

       
        IF @@ROWCOUNT > 0
        BEGIN
            SET @mensagem = 'Funcionário removido com sucesso';
        END
        ELSE
        BEGIN
            SET @mensagem = 'Funcionário não encontrado';
        END
    END TRY
    BEGIN CATCH
        SET @mensagem = 'Erro ao remover funcionário';
    END CATCH
END;

GO
CREATE PROCEDURE AumentarSalario
    @porcentagem DECIMAL(5, 2)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
       
        UPDATE Funcionario
        SET salario = salario + (salario * @porcentagem / 100);

        PRINT 'Salários atualizados com sucesso';
    END TRY
    BEGIN CATCH
        PRINT 'Erro ao atualizar os salários';
    END CATCH
END;

GO
CREATE PROCEDURE ListarAniversariantes 
    @IdMes INT
AS
BEGIN
    SET NOCOUNT ON;

   
    IF @IdMes  < 1 OR @IdMes > 12
    BEGIN
        PRINT 'Erro: O mês deve estar entre 1 e 12.';
        RETURN;
    END

 
    SELECT nome, data_nascimento 
    FROM Funcionario
    WHERE MONTH(data_nascimento) = @IdMes;
    
   
    IF @@ROWCOUNT = 0
    BEGIN
        PRINT 'Nenhum funcionário faz aniversário neste mês.';
    END
END; 

GO
CREATE PROCEDURE MaiorIdade
AS
BEGIN
    DECLARE @func_id INT;
    DECLARE @func_nome VARCHAR(255);
    DECLARE @func_idade INT;

    
    SELECT TOP 1 
           @func_id = id,
           @func_nome = nome,
           @func_idade = DATEDIFF(YEAR, data_nascimento, GETDATE()) - 
                         CASE 
                             WHEN MONTH(data_nascimento) > MONTH(GETDATE()) OR 
                                  (MONTH(data_nascimento) = MONTH(GETDATE()) AND DAY(data_nascimento) > DAY(GETDATE())) 
                             THEN 1 
                             ELSE 0 
                         END
    FROM Funcionario
    ORDER BY data_nascimento;

   
    SELECT @func_nome AS 'Nome', @func_idade AS 'Idade';
END

GO
CREATE PROCEDURE OrdemAlfabetica
AS
BEGIN
    SELECT * 
    FROM Funcionario
    ORDER BY nome ASC;  
END;


GO
CREATE PROCEDURE TotalSalario
AS
BEGIN
    SET NOCOUNT ON;

    SELECT funcao, SUM(salario) AS Total_Salarios
    FROM Funcionario
    GROUP BY funcao
    ORDER BY Total_Salarios DESC;
END

GO
--teste no banco de dados inserir:

INSERT INTO Funcionario (nome, data_nascimento, salario, funcao)
VALUES 
    ('Maria', '2000-10-18', 2009.44, 'Operador'),
    ('João', '1990-05-12', 2284.38, 'Operador'),
    ('Caio', '1961-05-02', 9836.14, 'Coordenador'),
    ('Miguel', '1988-10-14', 19119.88, 'Diretor'),
    ('Alice', '1995-01-05', 2234.68, 'Recepcionista'),
    ('Heitor', '1999-11-19', 1582.72, 'Operador'),
    ('Arthur', '1993-03-31', 4071.84, 'Contador'),
    ('Laura', '1994-07-08', 3017.45, 'Gerente'),
    ('Heloísa', '2003-05-24', 1606.85, 'Eletricista'),
    ('Helena', '1996-09-02', 2799.93, 'Gerente')


