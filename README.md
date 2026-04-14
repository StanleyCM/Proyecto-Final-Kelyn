# Proyecto Final Kelyn Tejada - Sistema gestor de Almacen y Usuarios

## Descripcion
Este proyecto consiste en un sistema de gestion de almacen y usuarios desarrollado en Java, utilizando conexion a base de datos MySQL remota (Aiven Cloud).

El sistema permite manejar:
- Registro de productos
- Gestion de usuarios
- Control de inventario

---

## Conexion a la Base de Datos

Por motivos de seguridad, la contraseña de la base de datos NO se encuentra directamente en el codigo fuente.

GitHub bloquea automaticamente credenciales reales dentro del codigo, ya que pueden ser utilizadas por terceros.

Por esta razon, en el archivo de conexion se utiliza un valor de referencia en lugar de la contraseña real.

<img width="955" height="363" alt="image" src="https://github.com/user-attachments/assets/60109913-2ff9-43d6-8f5b-7433a5b89786" />


---

## Credenciales de acceso a la BD

Para ejecutar el proyecto correctamente, utilizar las siguientes credenciales dentro del archivo (DB/ConexionBD.java):

private static final String URL = "jdbc:mysql://almacenitla-db-itla-3837.e.aivencloud.com:25037/almacenitlafinal?useSSL=true&serverTimezone=UTC"; <br>
private static final String USUARIO = "avnadmin"; <br>
private static final String PASSWORD = "AVNS_pPa2xcIg1Ubj0zcsoMg"; <br>

Codigo que debe cambiar:
<img width="1199" height="72" alt="image" src="https://github.com/user-attachments/assets/54413437-809c-472a-b86e-0ced58fa2c64" />


## Autor
Stanley Camacho Abreu (2025-2271)
