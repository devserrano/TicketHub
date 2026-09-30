# k6

Scripts de carga:

- `smoke-test.js`: 1 usuario, verifica que todo responde.
- `load-test.js`: 50 usuarios durante 2 min sobre login, listar y crear ticket.
- `spike-test.js`: pico de 100 usuarios.

Meta: p95 < 500 ms y < 1% de errores.
