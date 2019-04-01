CREATE FUNCTION [dbo].[splitList] (@list VARCHAR(MAX), @separator VARCHAR(MAX) = ';')
RETURNS @table TABLE (Value VARCHAR(MAX))
AS BEGIN
  DECLARE @position INT, @previous INT
  SET @list = @list + @separator
  SET @previous = 1
  SET @position = CHARINDEX(@separator, @list)
  WHILE @position > 0
  BEGIN
    IF @position - @previous > 0
      INSERT INTO @table VALUES (SUBSTRING(@list, @previous, @position - @previous))
    IF @position >= LEN(@list) BREAK
      SET @previous = @position + 1
      SET @position = CHARINDEX(@separator, @list, @previous)
  END
  RETURN
END
GO