# Práctica 1 - MarketPlace (Spring Boot + Thymeleaf + Spring Security)

Aplicación web de productos y carrito de compras con login por formulario, roles y una API REST.

Repo GitHub: https://github.com/edgarvargas-ugr/DSS-Practica1.git

Los productos se persisten en la DB H2, pero el carrito de compras solo se mantiene en tiempo de ejecución.


## Usuarios de prueba

| Usuario | Contraseña | Rol   |
|---------|------------|-------|
| `admin` | `admin`    | ADMIN |
| `user`  | `user`     | USER  |

Los usuarios están definidos en memoria en `SecurityConfig`.

---

## Seguridad: acceso directo a los endpoints

La seguridad **no depende de los botones**. Aunque un botón esté oculto, si alguien escribe la URL directamente en el navegador, se aplican las reglas definidas en `SecurityConfig`.

La /api quedo abierta para busquedas y con BasicAuth para POST, PUT, DELETE, por ahora solamente para gestión de productos.

Dentro del Repositorio hay un archivo para importar en Postman para probar la API.
Practica1 - API Productos.postman_collection.json

### Reglas por endpoint (orden de evaluación en `SecurityConfig`)

| Endpoint | Acceso |
|----------|--------|
| `/` | Público |
| `GET /cart` | Público (ver carrito) |
| `GET /products` | Público (listado) |
| `GET /products/busqueda` | Público (filtros) |
| `/products/**` | Solo ADMIN |
| `/export/**` | Solo ADMIN |
| `/cart/add/{id}` y `/cart/remove/{id}` | ADMIN o USER (sin sesión pide login) |
| `/h2-console/**` | Público (solo desarrollo) |
| `GET /api/**` | Público |
| `/api/**` | Solo ADMIN |
| Cualquier otra ruta | Requiere estar autenticado |
---

## Botones ocultos según el rol

Se usa `sec:authorize` de Thymeleaf. Ocultar un botón es solo comodidad visual; la protección real está en `SecurityConfig`.

| Elemento | Condición | Quién lo ve |
|----------|-----------|-------------|
| Editar producto | `hasRole('ADMIN')` | ADMIN |
| Eliminar producto | `hasRole('ADMIN')` | ADMIN |
| Crear producto | `hasRole('ADMIN')` | ADMIN |
| Exportar productos | `hasRole('ADMIN')` | ADMIN |
| Añadir al carrito / Remover | `isAuthenticated()` | Usuarios con sesión |
| Iniciar sesión | `!isAuthenticated()` | Visitantes |
| Cerrar sesión | `isAuthenticated()` | Usuarios con sesión |

---

## Rutas principales

### Vistas (Thymeleaf)

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/` | Inicio |
| GET | `/products` | Listado |
| GET | `/products/busqueda` | Filtro por nombre o rango de precio |
| GET | `/products/product-form[/{id}]` | Formulario crear/editar |
| POST | `/products` | Crear |
| PUT | `/products` | Editar (vía `_method=put`) |
| GET | `/products/delete/{id}` | Eliminar |
| GET | `/cart` | Ver carrito |
| GET | `/cart/add/{id}` | Añadir al carrito |
| GET | `/cart/remove/{id}` | Remover del carrito |
| GET | `/export` | Descarga `productos.sql` |

### API REST (`/api/product`)

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/product` | Listar |
| GET | `/api/product/{id}` | Obtener por id |
| POST | `/api/product` | Crear |
| PUT | `/api/product/{id}` | Actualizar |
| DELETE | `/api/product/{id}` | Eliminar |
| GET | `/api/product/busqueda` | Filtros `nombre`, `precioMenor`, `precioMayor` |

---