# PRD — Motor de Juegos Multijugador Local

**Proyecto:** motor-juegos-local-is  
**Versión:** 0.2 (Sprint 1)  
**Issue relacionado:** #4 — Redactar el PRD inicial

## 1. Problema

Al desarrollar varios juegos 2D multijugador locales desde cero, se repiten tareas como crear el ciclo de ejecución, leer la entrada de los jugadores, gestionar escenas, dibujar elementos y cargar recursos. Esto dificulta mantener los juegos y aprovechar el trabajo realizado en uno para desarrollar otro.

## 2. Objetivo del producto

Construir un motor modular que reúna esas funciones comunes y permita desarrollar juegos 2D multijugador locales mediante una API pública, sin modificar el núcleo del motor para cada juego.

Los objetivos específicos son:

- permitir partidas locales de al menos dos jugadores con teclado en una misma computadora;
- separar el núcleo del motor de las reglas y el contenido de cada juego;
- demostrar la reutilización del motor con al menos tres juegos distintos;
- documentar cómo integrar un juego nuevo y comprobar el funcionamiento de la lógica principal.

## 3. Usuarios

- **Desarrolladores y estudiantes:** utilizarán el motor y su API para crear juegos 2D multijugador locales pequeños.
- **Jugadores finales:** utilizarán los juegos demostrativos en una misma computadora. Cada juego deberá admitir al menos dos jugadores con teclado.

## 4. Alcance

### 4.1 Alcance mínimo comprometido

- Aplicación de escritorio en 2D.
- Dos jugadores locales simultáneos en una misma computadora, utilizando teclado.
- Ciclo principal de ejecución y gestión de escenas o estados del juego.
- Dibujo de figuras, sprites y texto.
- Carga y administración básica de imágenes, fuentes y audio.
- Reproducción básica de música o efectos de sonido.
- Detección de colisiones 2D necesarias para los juegos demostrativos.
- API pública para registrar, iniciar y desarrollar juegos sin modificar el núcleo para cada uno.
- Posibilidad de que cada juego defina sus propias reglas, puntuación, temporizadores y condiciones de victoria utilizando los servicios del motor.
- Al menos tres juegos demostrativos que utilicen el mismo motor. Las propuestas iniciales son Pong, Tron y Guerra de tanques; la selección concreta puede ajustarse si se conservan tres juegos que demuestren capacidades distintas.
- Pruebas automatizadas para la lógica principal que no dependa de gráficos.
- Documentación de arquitectura y guía para integrar un juego nuevo.

### 4.2 Objetivos opcionales sujetos al tiempo

- Ampliar el soporte de dos a cuatro jugadores simultáneos.
- Admitir controles físicos compatibles además del teclado.
- Ofrecer opciones adicionales de configuración de partidas.
- Incorporar un cuarto juego demostrativo.

Estos objetivos opcionales no son requisitos para considerar terminado el producto mínimo. Se priorizarán solamente si el alcance comprometido está funcionando y el tiempo restante lo permite.

### 4.3 Fuera del alcance

- Multijugador en red o entre computadoras distintas.
- Juegos 3D.
- Soporte para dispositivos móviles o consolas.
- Editor visual de niveles o escenas.
- Sistemas de cuentas, monetización o servicios en la nube.

Cualquier cambio de alcance debe justificarse, registrarse en un Issue y priorizarse durante la planificación de un sprint.

## 5. Requisitos funcionales iniciales

1. El motor debe iniciar, actualizar y detener el ciclo principal de un juego.
2. El motor debe permitir que dos jugadores utilicen simultáneamente teclas distintas para controlar un juego local.
3. El motor debe permitir cambiar entre las escenas o estados necesarios, por ejemplo menú, partida, pausa y resultados.
4. El motor debe permitir dibujar sprites, figuras geométricas y texto.
5. El motor debe cargar imágenes, fuentes y archivos de audio, y permitir reproducir audio básico.
6. El motor debe proporcionar detección de colisiones 2D básicas.
7. El motor debe ofrecer servicios reutilizables para que cada juego gestione sus propias reglas, puntuación, temporizadores y condiciones de victoria.
8. El motor debe exponer una API pública con la que se pueda registrar e iniciar un juego sin modificar el núcleo.
9. Debe ser posible ejecutar al menos tres juegos demostrativos distintos utilizando esa API.

El soporte para tres o cuatro jugadores y para controles físicos se considera una ampliación opcional, no parte de los requisitos funcionales mínimos.

## 6. Requisitos no funcionales iniciales

1. **Extensibilidad:** agregar un juego nuevo siguiendo la guía de integración no debe requerir modificar el núcleo del motor.
2. **Mantenibilidad:** el código debe seguir la guía de estilo del equipo y todo cambio al repositorio principal debe pasar por revisión de Pull Request.
3. **Compatibilidad:** la primera versión debe ejecutarse como aplicación de escritorio en los equipos de desarrollo del grupo.
4. **Rendimiento:** los tres juegos demostrativos deberán alcanzar como objetivo al menos 30 cuadros por segundo durante una partida de dos minutos en un equipo de referencia que el grupo documentará al comenzar la implementación. Si no se alcanza, se registrará el resultado y la causa antes de dar la tarea por terminada.
5. **Pruebas:** la lógica principal que no dependa de gráficos debe contar con pruebas automatizadas que puedan ejecutarse de forma reproducible.
6. **Documentación:** la arquitectura y el procedimiento para integrar un juego nuevo deben quedar accesibles para el equipo.

## 7. Criterios de éxito

- Los tres juegos demostrativos se ejecutan y permiten jugar partidas locales de al menos dos personas con teclado.
- Los tres juegos utilizan la API pública del motor y no necesitan cambios específicos en su núcleo.
- Un integrante puede integrar un juego nuevo siguiendo la documentación del proyecto.
- Las pruebas automatizadas de la lógica principal pasan de forma consistente.
- El equipo puede compilar y ejecutar el proyecto siguiendo las instrucciones del repositorio.

El soporte para cuatro jugadores, controles físicos o un cuarto juego se evaluará como ampliación, no como condición de éxito del alcance mínimo.

## 8. Propuesta tecnológica inicial

Se propone utilizar Java 21, Gradle, libGDX, LWJGL3 para escritorio y JUnit para pruebas. Java es el lenguaje que el equipo conoce mejor; libGDX proporcionaría las funciones de ventana, gráficos, audio y entrada sobre las cuales se construiría la arquitectura modular propia.

Esta propuesta se validará con un proyecto mínimo ejecutable antes de considerarla una decisión técnica definitiva.

## 9. Riesgos y limitaciones iniciales

- **Curva de aprendizaje:** el equipo necesita tiempo para familiarizarse con libGDX y la estructura de un motor.
- **Alcance:** construir un motor y tres juegos en el tiempo disponible puede exigir recortar funcionalidades opcionales.
- **Integración:** cambios tardíos en la API podrían obligar a ajustar los juegos que la utilizan.
- **Pruebas de dispositivos:** no todos los integrantes disponen de controles físicos; por eso su soporte no forma parte del mínimo comprometido.
- **Colaboración:** el trabajo en módulos relacionados requiere Issues claros, revisiones y coordinación para evitar conflictos de integración.

---

Este PRD es la definición inicial del producto. Puede actualizarse por acuerdo del equipo conforme avance el proyecto, incluyendo cambios de alcance.
