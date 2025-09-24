# Semana5_2 Android CRUD App

## Descripción General
Este proyecto es una aplicación Android desarrollada en Kotlin que implementa un CRUD (Crear, Leer, Actualizar, Eliminar) básico de usuarios utilizando SQLite a través de un helper personalizado. La interfaz está construida con Jetpack Compose y la arquitectura sigue el patrón MVVM (Model-View-ViewModel).

---

## Estructura del Proyecto
- **MainActivity.kt**: Punto de entrada de la app. Inicializa el ViewModel y configura el tema y la navegación.
- **navigation/Nav.kt**: Define la navegación entre pantallas usando NavHost y composables.
- **view/Home.kt**: Pantalla principal para agregar usuarios. Incluye campos de texto y un botón para guardar.
- **view/ListUser.kt**: Pantalla para mostrar la lista de usuarios (estructura base incluida).
- **model/User.kt**: Data class que representa la entidad Usuario.
- **model/openhelper/OpenHelper.kt**: Helper para gestionar la base de datos SQLite. Incluye métodos para crear la tabla, insertar y leer usuarios.
- **viewmodels/UserViewModel.kt**: ViewModel que conecta la UI con la base de datos y expone métodos para agregar y mostrar usuarios.

---

## Teoría y Aspectos Clave

### 1. Jetpack Compose
- **Declarativo**: La UI se describe como funciones composables.
- **Modificadores**: Permiten ajustar el layout, tamaño y estilo de los componentes.

### 2. MVVM
- **Model**: Representa los datos (User y OpenHelper).
- **View**: Pantallas composables (Home, ListUser).
- **ViewModel**: Lógica de negocio y comunicación con la base de datos.

### 3. SQLite y OpenHelper
- **SQLiteOpenHelper**: Facilita la creación y gestión de la base de datos.
- **onCreate/onUpgrade**: Métodos clave para inicializar y actualizar la estructura de la base de datos.
- **ContentValues**: Utilizado para insertar datos de forma segura.

### 4. Navegación
- **NavHost y composable**: Permiten cambiar entre pantallas de forma sencilla y segura.

### 5. State Management
- **mutableStateOf y remember**: Permiten que la UI reaccione a cambios en los datos.

---

## Puntos Importantes
- **Validación de datos**: Actualmente no se valida la entrada del usuario (ejemplo: edad vacía o no numérica puede causar errores).
- **Gestión de recursos**: Se recomienda cerrar correctamente los cursores y bases de datos para evitar fugas de memoria.
- **Escalabilidad**: Para apps más grandes, se recomienda usar Room en vez de SQLiteOpenHelper.
- **UI Responsiva**: Los campos de texto usan `fillMaxWidth()` para evitar ocupar todo el alto de la pantalla.
- **Separación de responsabilidades**: El ViewModel no expone directamente la base de datos a la UI, sino métodos específicos.

---

## Ejecución
1. Clona el repositorio y ábrelo en Android Studio.
2. Ejecuta la app en un emulador o dispositivo físico.
3. Usa la pantalla principal para agregar usuarios.
4. (Opcional) Implementa la pantalla ListUser para mostrar los usuarios almacenados.

---

## Mejoras Sugeridas
- Validar los campos antes de guardar.
- Implementar las funciones de actualizar y eliminar usuarios.
- Mejorar la UI con Material Design.
- Migrar a Room para una gestión de base de datos más robusta.

---

## Recursos
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [MVVM en Android](https://developer.android.com/jetpack/guide)
- [SQLiteOpenHelper](https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper)
- [Room Database](https://developer.android.com/training/data-storage/room)

---

## Autor
Estudiantes de Ing Software - UPC

