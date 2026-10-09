# Motor de Juegos Multijugador Local

Motor modular para desarrollar juegos multijugador locales en 2D. El proyecto busca proporcionar componentes reutilizables para crear distintos juegos competitivos que puedan ejecutarse en una misma computadora y utilizar teclado (y/o posiblemente controles) como dispositivos de entrada.

> **Estado del proyecto:** Sprint 3: Contrato motor-juego, integración de Pong como primer juego.

## Equipo: Void Team

- Alan Blancas
- Diego Cárdenas
- Héctor Castro
- Javier Díaz
- Juan Mejía

## Descripción

El producto será un motor de juego modular orientado a juegos multijugador locales. El motor administrará las funciones comunes entre los juegos, como el ciclo de ejecución, el control de los jugadores, los dispositivos de entrada, las escenas, los recursos gráficos y las colisiones generales.

Para comprobar que el motor es reutilizable y extensible, el proyecto incluirá al menos tres juegos. La intención es que un juego nuevo pueda integrarse mediante las interfaces públicas del motor sin modificar su núcleo.

## Objetivos específicos

- Definir una arquitectura modular que separe el núcleo del motor de los juegos.
- Permitir partidas locales de al menos dos jugadores con teclado en una misma computadora.
- Explorar la ampliación hasta cuatro jugadores y el uso de controles compatibles si el tiempo lo permite.
- Proporcionar servicios reutilizables de escenas, gráficos 2D, recursos, interacciones y puntuación.
- Desarrollar una interfaz común para registrar e iniciar juegos independientes.
- Crear al menos tres juegos que utilicen las capacidades del motor.
- Mantener un proceso de desarrollo incremental mediante sprints, control de versiones, revisión de cambios y pruebas.
- Documentar la arquitectura y el procedimiento para agregar un juego nuevo.

## Alcance tentativo

- Aplicación de escritorio en 2D.
- Multijugador local en una misma computadora.
- Soporte mínimo para dos jugadores; ampliación posible hasta cuatro.
- Entrada mediante teclado; controles compatibles como objetivo opcional.
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

El motor está dirigido principalmente a estudiantes y desarrolladores que quieran crear juegos 2D multijugador locales pequeños reutilizando una base común. Los juegos demostrativos estarán orientados a grupos de al menos dos personas que compartan una misma computadora.

## Juegos demostrativos propuestos

La selección inicial considera los siguientes juegos:

1. **Pong:** demostrará movimiento, entrada de dos jugadores, colisiones, puntuación y reinicio de rondas.
2. **Tron:** demostrará entrada simultánea de dos jugadores, eliminación y colisiones con rastros; se ampliará hasta cuatro jugadores si el tiempo lo permite.
3. **Guerra de tanques:** demostrará movimiento libre, proyectiles, obstáculos, recursos gráficos, audio y configuración de partidas.

La selección podrá ajustarse de acuerdo con el tiempo disponible y capacidad final del motor, conservando el objetivo de demostrar capacidades diferentes del motor.

## Propuesta tecnológica inicial

- **Lenguaje:** Java 21.
- **Framework gráfico:** libGDX.
- **Plataforma inicial:** escritorio mediante LWJGL3.
- **Construcción y dependencias:** Gradle.
- **Pruebas:** JUnit.

libGDX se utilizará para interactuar con la ventana, gráficos, audio y dispositivos; la arquitectura modular, las interfaces y los servicios de alto nivel serán desarrollados por el equipo.

## Organización del repositorio

El repositorio es un proyecto Gradle con dos módulos, `core` y `lwjgl3`:

```text
motor-juegos-local-is/
├── core/                              # Motor y juegos (módulo Gradle)
│   └── src/
│       ├── main/java/org/voidteam/
│       │   ├── engine/
│       │   │   ├── api/               # Contratos públicos: LocalGame, GameContext,
│       │   │   │                      # PlayerInput, PlayerAction, Renderer2D
│       │   │   └── core/              # Implementaciones: GameEngine, KeyboardInput,
│       │   │                          # LibGdxRenderer2D
│       │   └── games/
│       │       ├── demo/              # MovingRectangleDemo (demo del Sprint 2)
│       │       └── pong/              # PongGame
│       └── test/java/org/voidteam/games/pong/
│                                      # PongGameTest
├── lwjgl3/                           # Lanzador de escritorio (Lwjgl3Launcher)
├── assets/                            # Recursos del juego
└── docs/                              # Documentación (PRD.md)
```

La regla principal es que los juegos usan las interfaces públicas del paquete `engine`, pero el motor no depende de ningún juego concreto.

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

Durante el Sprint 3 se implementó y verificó la integración de Pong como un juego, este utiliza las capacidades del motor.

Actualmente se cuenta con:

- Implementación de `PongGame` como un juego independiente mediante la interfaz `LocalGame`.
- Separación entre el juego y las implementaciones específicas de libGDX mediante los servicios del motor.
- Entrada de los jugadores mediante `PlayerInput`.
- Renderizado mediante `Renderer2D`.
- Actualización del juego mediante `deltaTime`.
- Soporte para dos jugadores en una misma computadora.
- Movimiento automático de la pelota.
- Colisiones entre la pelota y las paletas.
- Sistema de puntuación.
- Condición de victoria al llegar a 5 puntos.
- Reinicio de la partida después de finalizar (Siempre y cuando hagas una nueva pulsación de las teclas `↑` o `w` )
- Pruebas automatizadas para comprobar la integración básica de `PongGame` con los servicios del motor.
- Ejecución de Pong mediante LWJGL3.

La partida de Pong fue probada manualmente y se verificó que ambos jugadores pueden controlar sus paletas, que la pelota se mueve automáticamente, que la partida termina cuando un jugador llega a 5 puntos y que puede reiniciarse mediante la tecla de flecha arriba `↑` si eres el jugador 2 o la tecla `w` si eres el jugador 1. Mantener la tecla presionada al finalizar la partida no provoca un reinicio automático.

## Arquitectura

El proyecto separa el motor de los juegos mediante interfaces públicas. Un juego implementa `LocalGame` y recibe los servicios que necesita mediante `GameContext`.

La ejecución de Pong sigue el siguiente flujo:

```text
Lwjgl3Launcher
      |
      v
  GameEngine
      |
      v
   PongGame
      |
      v
  GameContext
    /        \
   v          v
PlayerInput  Renderer2D
   |           |
   v           v
KeyboardInput  LibGdxRenderer2D
   |           |
   v           v
 libGDX      libGDX
 ```

### Dirección de dependencias

- `engine.api` contiene solo los contratos y no depende de libGDX ni de ningún juego.
- Los juegos (`games.*`) dependen únicamente de `engine.api`.
- `engine.core` implementa los contratos sobre libGDX.
- El launcher usa libGDX para crear la ventana y es el único punto donde se indica qué
  juego iniciar; el resto del motor no conoce ningún juego concreto.

### Cómo el motor inicia un juego

`Lwjgl3Launcher` crea la ventana y arranca `GameEngine` con el `LocalGame` elegido. El motor
le entrega un `GameContext` con los servicios de entrada y renderizado, y en cada ciclo llama
al juego con `deltaTime`.

### Dónde colocar un juego nuevo

1. Crear un paquete nuevo en `core/src/main/java/org/voidteam/games/<nombre-del-juego>/`.
2. Implementar la interfaz `LocalGame` y usar solo los servicios de `GameContext`.
3. No modificar `engine.api` ni `engine.core` para agregar condiciones del juego.
4. Indicar al launcher (módulo `lwjgl3`) qué juego iniciar.

## Ejecutar Juegos

### Requisitos

* Tener instalado Java 21.
* Tener Git instalado para clonar el repositorio.
* Una computadora compatible con la ejecución de una aplicación de escritorio mediante LWJGL3.

### Obtener el proyecto

Clonar el repositorio y entrar a la carpeta del proyecto:

```bash
git clone https://github.com/SE-7003-2027/motor-juegos-local-is.git
cd motor-juegos-local-is
```

### Compilar el proyecto

Desde la carpeta raíz del proyecto, ejecutar:

**Windows:**
```text
gradlew.bat build
```

**Linux / macOS:**
```bash
./gradlew build
```

Si la compilación termina correctamente, Gradle mostrará el mensaje `BUILD SUCCESSFUL`.

### Seleccionar el juego

Este se selecciona por medio de comandos en la terminal, pong es el juego establecido por defecto.
**Windows:**
```text
gradlew.bat lwjgl3:run
```

**Linux / macOS:**
```bash
./gradlew lwjgl3:run
```

Existe la opción de elegir el juego pong mediante los comandos:
**Windows:**
```text
gradlew.bat lwjgl3:run --args="pong"
```

**Linux / macOS:**
```bash
./gradlew lwjgl3:run --args="pong"
```

Existe la opción de elegir el la demo mediante los siguientes comandos:
**Windows:**
```text
gradlew.bat lwjgl3:run --args="demo"
```

**Linux / macOS:**
```bash
./gradlew lwjgl3:run --args="demo"
```

Las opciones disponibles son:

- `pong`: inicia Pong.
- `demo`: inicia el juego de demostración.
- Sin argumentos: inicia Pong.
- Cualquier otro argumento: muestra un mensaje indicando que el juego no es reconocido y muestra las opciones disponibles, sin iniciar ningún juego.


### Controles de Pong

Pong es un juego para dos jugadores:

- **Jugador 1:** `W` para subir y `S` para bajar.
- **Jugador 2:** `↑` para subir y `↓` para bajar.
- El jugador que llegue primero a **5 puntos** gana.
- Después de terminar la partida, el **Jugador 1** puede reiniciarla pulsando `W`, mientras que el **Jugador 2** puede reiniciarla pulsando `↑`.
- Una aclaración útil es que para reiniciar la partida, la tecla debe soltarse y volver a presionarse. Mantener la tecla presionada cuando termina la partida no provoca un reinicio automático.

Los controles son gestionados por el servicio de entrada del motor. Pong no consulta directamente el teclado de libGDX.

## Fuera del alcance

En la versión inicial del proyecto no se contempla:

- Multijugador en línea o conexión entre diferentes computadoras.
- Desarrollo para dispositivos móviles o consolas.
- Soporte para juegos 3D.
- Sistemas avanzados de física o simulación.
- Creación de un editor visual de niveles.
- Implementación de servicios externos o sistemas de cuentas de usuario.

## Recursos del proyecto

- [Project](https://github.com/orgs/SE-7003-2027/projects/6/views/1)
- [Wiki](https://github.com/SE-7003-2027/motor-juegos-local-is/wiki) 
- [Guía de colaboración](https://github.com/SE-7003-2027/motor-juegos-local-is/blob/main/CONTRIBUTING.md)

