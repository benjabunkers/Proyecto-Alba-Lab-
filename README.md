# CORPALIV | Alba Lab

Aplicación Android para presentar los programas de la fundación y facilitar el contacto con personas interesadas. También incluye herramientas locales de seguimiento para el equipo de trabajadores.

## Funcionalidades

- Inicio con el logo y una presentación de la fundación.
- Sección **Sobre nosotros** con descripción, misión, visión y forma de trabajo.
- Programas/campañas con botón **Participar**, que lleva al formulario de contacto.
- Historias de impacto simuladas, identificadas como perfiles ficticios.
- Formulario de contacto con validación de nombre, correo y teléfono.
- Acceso de trabajador con dashboard y directorio de contactos.
- Seguimiento de contactos: estado, observaciones y llamada telefónica.
- Selección de foto para cada contacto desde el selector de fotos del dispositivo.
- Preferencia de apariencia clara, oscura o según el sistema.

## Tecnologías

- Kotlin
- Jetpack Compose y Material 3
- Navigation Compose
- Arquitectura MVVM
- Room para persistencia local
- Gradle con catálogo de versiones (`gradle/libs.versions.toml`)

## Requisitos

- Android Studio compatible con Android Gradle Plugin 9.4.1.
- JDK 17.
- Android SDK con plataforma 37 instalada.
- Emulador o dispositivo Android con API 24 o superior.

## Abrir y ejecutar

1. Abre Android Studio y selecciona **Open**.
2. Elige la carpeta raíz del proyecto (la que contiene `settings.gradle.kts`).
3. Espera a que termine la sincronización de Gradle.
4. Selecciona un emulador o conecta un dispositivo y ejecuta la configuración `app`.

También puedes generar un APK de depuración desde la raíz:

```powershell
.\gradlew.bat :app:assembleDebug
```

El APK se genera en `app/build/outputs/apk/debug/app-debug.apk`.

## Uso del acceso de trabajador

La pantalla de acceso está configurada como demostración local: acepta cualquier correo con formato válido y una contraseña de seis caracteres o más. No verifica credenciales contra un servidor y no debe considerarse autenticación de producción.

## Datos y fotos

Los contactos, sus estados, observaciones y referencias a fotos se guardan en la base Room del dispositivo. La foto se selecciona desde el selector nativo de Android y se asocia al contacto desde el directorio de donadores. Esta versión no sincroniza esos datos entre dispositivos ni con un servidor.

La base de datos incluye una migración para añadir el campo de foto a instalaciones existentes.

## Estructura principal

```text
app/src/main/java/com/example/app_corpaliv_grupo12/
├── data/         # Room, DAO y repositorio
├── model/        # Entidades y modelos de dominio
├── navigation/   # Rutas y navegación de la aplicación
├── ui/
│   ├── components/
│   ├── screens/  # Pantallas Compose
│   └── theme/    # Tema y estilos
└── viewmodel/    # Estado y lógica de presentación
```

## Fundación

**Misión:** Acompañar el desarrollo de niños, niñas, jóvenes y adultos con discapacidad múltiple a lo largo de toda su vida, a través de un modelo integral que respete sus necesidades y diferencias.

**Visión:** Crear y tener un país sin barreras para la discapacidad múltiple.
