
# API CON RETROIT EN KOTLIN(CRUD)

1.Abre la línea de comandos en Windows (CMD) y ejecuta el comando "ipconfig" para obtener tu dirección IP, la cual utilizarás como base_url. En mi caso "https://192.168.18.10:3000"

2.Asegúrate de realizar este procedimiento localmente. Luego, inicia XAMPP para activar los servicios de MySQL y Apache.

3.Ejecuta tu código y, en la terminal de Visual Studio Code (VSCode), utiliza el comando "npm run dev" para lanzar tu API.

## 🛠 Skills
Kotlin, Android Studio, NodeJS, MySQL...


## Aplicación Android:
La MainActivity es el lugar principal donde ocurre la magia. Aquí se usa un RecyclerView para mostrar una lista de tareas de manera ordenada. La estructura del código, utilizando extensiones de vistas y coroutines para manejar operaciones asíncronas.

#### Main.kt
![App Screenshot](https://i.ibb.co/Ptw3QKv/Captura-de-pantalla-2024-01-31-134148.png)

## Adaptador de Tareas:
El TareaAdapter es como el maestro de ceremonias que organiza cómo se presentan las tareas en la lista. También maneja las interacciones del usuario, permitiendo editar y eliminar tareas con botones dedicados.

#### TareaAdapter.kt
![App Screenshot](https://i.ibb.co/RQjSYy6/Captura-de-pantalla-2024-01-31-140826.png)

## Objeto Tarea:
La Tarea es la estructura de datos que representa cada tarea. Tiene propiedades como id, titulo y descripcion.

#### Tarea.kt
![App Screenshot](https://i.ibb.co/XbH1kJW/Captura-de-pantalla-2024-01-31-141014.png)

## Comunicación con el Servidor:
Para llevar a cabo operaciones CRUD (Crear, Leer, Actualizar, Eliminar), se establecio una conexión con un servidor NodeJS. Este servidor, construido con Express, ofrece puntos finales para manejar estas operaciones. Usando MySQL como tu base de datos para almacenar y recuperar información sobre las tareas.

#### app.kt
![App Screenshot](https://i.ibb.co/thJZd0Y/Captura-de-pantalla-2024-01-31-141220.png)

## Retrofit y Coroutines:
La parte interesante es cómo te has conectado con el servidor desde tu aplicación Android. Retrofit hace que las solicitudes HTTP sean más sencillas, y las coroutines para realizar estas operaciones de manera asíncrona, asegurándo de que la interfaz de usuario no se bloquee mientras esperas las respuestas del servidor.

#### WebService.kt
![App Screenshot](https://i.ibb.co/RhF81xb/Captura-de-pantalla-2024-01-31-133928.png)
