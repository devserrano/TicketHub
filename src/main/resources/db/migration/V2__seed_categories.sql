-- V2: catálogo inicial de categorías.
-- Los usuarios semilla (admin, agentes, solicitantes) se agregan en la fase 2, cuando exista el login.

INSERT INTO categories (name, description) VALUES
    ('Hardware', 'Equipos, impresoras, periféricos'),
    ('Software', 'Instalación o fallas de programas'),
    ('Red', 'Internet, WiFi, VPN'),
    ('Accesos', 'Cuentas, contraseñas y permisos'),
    ('Otro', 'Lo que no encaja en las demás');
