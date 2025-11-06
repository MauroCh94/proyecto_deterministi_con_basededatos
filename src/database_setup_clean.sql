-- Script para limpiar y recrear la base de datos sin conflictos

-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS modelodeter;

-- Crear la base de datos
CREATE DATABASE modelodeter;
USE modelodeter;

-- Tabla ingeniero (sin triggers que puedan causar conflictos)
CREATE TABLE ingeniero (
    id_ing INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla resultados
CREATE TABLE resultados (
    id_resultado INT AUTO_INCREMENT PRIMARY KEY,
    id_ing INT NOT NULL,
    numero_camino INT NOT NULL,
    informatell INT NOT NULL,
    sistecom INT NOT NULL,
    technologic INT NOT NULL,
    ganancia INT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_ing) REFERENCES ingeniero(id_ing) ON DELETE CASCADE
);

-- Vista que incluye id_ing para compatibilidad con el código
CREATE OR REPLACE VIEW vista_ingeniero_resultados AS
SELECT 
    i.id_ing,
    i.nombre AS ingeniero,
    r.numero_camino,
    r.informatell,
    r.sistecom,
    r.technologic,
    r.ganancia,
    r.fecha_registro
FROM ingeniero i
INNER JOIN resultados r ON i.id_ing = r.id_ing
ORDER BY i.id_ing, r.numero_camino;

-- Verificar que no hay triggers problemáticos
SHOW TRIGGERS;