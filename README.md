# Motor de Juegos Multijugador Local

Motor modular para desarrollar juegos multijugador locales en 2D. El proyecto busca proporcionar componentes reutilizables para crear distintos juegos competitivos que puedan ejecutarse en una misma computadora y utilizar teclado (y/o posiblemente controles) como dispositivos de entrada.

> **Estado del proyecto:** Sprint 1: definición y alcance del proyecto.

## Equipo: Void Team

- Blancas Ochoa Alan
- Cárdenas Galicia Diego
- Castro Cázares Héctor Alonso
- Díaz Anavia Javier Omar
- Mejía Reyes Juan Daniel

## Descripción

El producto será un motor de juego modular orientado a juegos multijugador locales. El motor administrará las funciones comunes entre los juegos, como el ciclo de ejecución, el control de los jugadores, los dispositivos de entrada, las escenas, los recursos gráficos y las colisiones generales.

Para comprobar que el motor es reutilizable y extensible, el proyecto incluirá al menos tres juegos. La intención es que un juego nuevo pueda integrarse mediante las interfaces públicas del motor sin modificar su núcleo.

## Objetivos específicos

- Definir una arquitectura modular que separe el núcleo del motor de los juegos.
- Permitir partidas locales de dos a cuatro jugadores en una misma computadora.
- Admitir teclado y controles como dispositivos de entrada.
- Proporcionar servicios reutilizables de escenas, gráficos 2D, recursos, interacciones y puntuación.
- Desarrollar una interfaz común para registrar e iniciar juegos independientes.
- Crear al menos tres juegos que utilicen las capacidades del motor.
- Mantener un proceso de desarrollo incremental mediante sprints, control de versiones, revisión de cambios y pruebas.
- Documentar la arquitectura y el procedimiento para agregar un juego nuevo.

## Alcance tentativo

- Aplicación de escritorio en 2D.
- Multijugador local en una misma computadora.
- Soporte previsto para dos a cuatro jugadores.
- Entrada mediante teclado y controles compatibles.
- Menú principal, selección de juego y configuración básica de partida.
- Administración de escenas o estados, como menú, partida, pausa y resultados.
- Dibujo de sprites, figuras y texto.
- Reproducción de música y efectos de sonido.
- Carga y administración de recursos.
- Colisiones 2D básicas.
- Marcadores, temporizadores y condiciones de victoria.
- API o conjunto de interfaces para incorporar juegos al motor.
- Al menos tres juegos demostrativos.
- Pruebas automatizadas para la lógica principal que no dependa de gráficos.
- Documentación técnica y guía de extensión.

Estos límites podrán revisarse durante el proyecto, pero cualquier cambio deberá justificarse, registrarse y priorizarse dentro de un sprint.

## Usuarios objetivo

El motor está dirigido principalmente a estudiantes y desarrolladores que quieran crear juegos 2D multijugador locales pequeños reutilizando una base común. Los juegos demostrativos estarán orientados a grupos de dos a cuatro personas que compartan una misma computadora.

## Juegos demostrativos propuestos

La selección inicial considera los siguientes juegos:

1. **Pong:** demostrará movimiento, entrada de dos jugadores, colisiones, puntuación y reinicio de rondas.
2. **Tron:** demostrará entrada simultánea de hasta cuatro jugadores, eliminación y colisiones con rastros.
3. **Guerra de tanques:** demostrará movimiento libre, proyectiles, obstáculos, recursos gráficos, audio y configuración de partidas.

La selección podrá ajustarse de acuerdo con el tiempo disponible y capacidad final del motor, conservando el objetivo de demostrar capacidades diferentes del motor.

## Propuesta tecnológica inicial

- **Lenguaje:** Java 21.
- **Framework gráfico:** libGDX.
- **Plataforma inicial:** escritorio mediante LWJGL3.
- **Construcción y dependencias:** Gradle.
- **Pruebas:** JUnit.

libGDX se utilizará para interactuar con la ventana, gráficos, audio y dispositivos; la arquitectura modular, las interfaces y los servicios de alto nivel serán desarrollados por el equipo.

## Organización prevista

El repositorio se organizará como un proyecto con módulos separados, se propone el esqueleto inicial, basados en documentación de libGDX e investigación de desarrollo, como:

```text
motor-juegos-local-is/
├── engine-api/          # Contratos públicos para los juegos
├── engine-core/         # Servicios y ciclo principal del motor
├── launcher/            # Inicio
├── games/               # Juegos demostrativos
├── assets/              # Imágenes, fuentes, mapas y audio
└── docs/                # Arquitectura y documentación del proyecto
```

Esta estructura es preliminar y se implementará de manera incremental con probables cambios sobre la marcha. La regla principal será que los juegos puedan utilizar la API del motor, pero el núcleo del motor no dependa de un juego concreto.

## Metodología de trabajo

El flujo de colaboración previsto es:

1. Crear o seleccionar una tarea del backlog.
2. Trabajar en una rama corta asociada con la tarea.
3. Abrir un *pull request* hacia la rama `main`.
4. Solicitar la revisión del pull request al equipo.
5. Resolver observaciones y verificar las pruebas disponibles si las hay.
6. Integrar el cambio manteniendo `main` en un estado estable y siempre funcional.

Las responsabilidades de coordinación, revisión, integración y documentación se rotarán entre los integrantes para compartir el conocimiento del proyecto.

## Entregables previstos

- Núcleo y API del motor.
- Aplicación de escritorio para seleccionar y ejecutar juegos.
- Soporte para jugadores y dispositivos de entrada locales.
- Al menos tres juegos demostrativos.
- Pruebas automatizadas de la lógica principal.
- Documentación de arquitectura y decisiones técnicas.
- Guía para desarrollar e integrar un juego nuevo.
- Distribución ejecutable de la versión final.

## Estado actual

Durante el Sprint 1 se realizarán las siguientes actividades:

- Creación y configuración del repositorio.
- Incorporación de los cinco integrantes como colaboradores.
- Definición de la descripción general, los objetivos y el alcance.
- Revisión y aprobación del README por parte del equipo.
- Preparación inicial del backlog para los siguientes sprints.
