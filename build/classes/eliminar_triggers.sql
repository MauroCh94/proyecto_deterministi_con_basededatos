-- Script para eliminar los triggers problemáticos

USE modelodeter;

-- Eliminar los triggers que están causando el conflicto
DROP TRIGGER IF EXISTS after_insert_ingeniero;
DROP TRIGGER IF EXISTS after_delete_ingeniero;

-- Verificar que se eliminaron
SHOW TRIGGERS;

-- Si existe la columna numero_orden que no necesitamos, la eliminamos también
-- (Esto es opcional, solo si existe y no la necesitas)
-- ALTER TABLE ingeniero DROP COLUMN IF EXISTS numero_orden;

-- Verificar la estructura de la tabla
DESCRIBE ingeniero;