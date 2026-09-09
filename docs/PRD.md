 PRD — Motor de Juegos Multijugador Local

**Proyecto:** motor-juegos-local-is 
**Versión:** 0.1 (borrador inicial — Sprint 1)
**Relacionado con:** Issue #4 — Redactar el PRD inicial

## 1. Resumen

El producto es un motor de juego modular en 2D orientado a partidas multijugador locales (2 a 4 jugadores compartiendo una misma computadora). El motor centraliza las funciones comunes entre juegos —ciclo de ejecución, entrada, escenas, gráficos, audio, recursos y colisiones— para que distintos juegos puedan integrarse mediante interfaces públicas sin modificar el núcleo.

## 2. Usuarios del motor

Se identifican dos tipos de usuario:

- **Desarrolladores/estudiantes:** quienes usan el motor como base para construir sus propios juegos 2D multijugador locales, integrándose a través de la API pública sin tocar el núcleo.
- **Jugadores finales:** grupos de 2 a 4 personas que comparten una misma computadora y juegan los títulos demostrativos (Pong, Tron, Guerra de tanques) usando teclado y/o control.

## 3. Alcance

### 3.1 Incluido en el alcance

- Aplicación de escritorio en 2D.
- Multijugador local (2 a 4 jugadores) en una misma computadora.
- Entrada por teclado y/o controles compatibles.
- Menú principal, selección de juego y configuración básica de partida.
- Administración de escenas/estados: menú, partida, pausa, resultados.
- Renderizado de sprites, figuras y texto.
- Reproducción de música y efectos de sonido.
- Carga y administración de recursos (assets).
- Sistema de colisiones 2D básico.
- Marcadores, temporizadores y condiciones de victoria.
- API/interfaces públicas para registrar e iniciar juegos.
- Al menos 3 juegos demostrativos (Pong, Tron, Guerra de tanques).
- Pruebas automatizadas para la lógica que no dependa de gráficos.
- Documentación de arquitectura y guía para integrar un juego nuevo.

### 3.2 Fuera del alcance

- Multijugador en red (online)  el enfoque es exclusivamente local, misma máquina.
- Soporte para plataformas móviles o consolas.
- Más de 4 jugadores simultáneos.
- Editor visual de niveles o de escenas.
- Sistema de monetización, cuentas de usuario o backend en la nube.

Cualquier cambio a estos límites debe justificarse, registrarse y priorizarse dentro de un sprint (según la guía de colaboración del equipo).

## 4. Requisitos funcionales iniciales

1. El motor debe permitir iniciar y detener un ciclo de ejecución (game loop) estándar.
2. El motor debe soportar entrada simultánea de 2 a 4 jugadores vía teclado y/o control.
3. El motor debe proveer un sistema de gestión de escenas (menú, partida, pausa, resultados) con transición entre ellas.
4. El motor debe permitir dibujar sprites, formas geométricas y texto en pantalla.
5. El motor debe permitir cargar y reproductr música y efectos de sonido.
6. El motor debe proveer un sistema de carga y administración de recursos (imágenes, audio, fuentes).
7. El motor debe detectar colisiones 2D básicas entre entidades del juego.
8. El motor debe proveer utilidades de marcador (score), temporizador y evaluación de condiciones de victoria.
9. El motor debe exponer una API pública para que un juego se registre e inicie sin modificar el núcleo del motor.
10. Debe ser posible ejecutar al menos 3 juegos demostrativos distintos (Pong, Tron, Guerra de tanques) usando exclusivamente esa API pública.

## 5. Requisitos no funcionales iniciales
.
1. **Extensibilidad:** debe ser posible agregar un juego nuevo siguiendo una guía documentada, sin modificar `engine-core`.
3. **Mantenibilidad:** el código debe seguir las convenciones definidas por el equipo (issue #5) y pasar por revisión de Pull Request antes de integrarse a `main`.
4. **Compatibilidad:** el motor debe ejecutarse en escritorio mediante LWJGL3 sobre Java 21 con libGDX.
5. **Rendimiento:** el ciclo de juego debe mantener una tasa de cuadros estable adecuada para juegos 2D casuales (sin caídas perceptibles de FPS en los juegos demo).
6. **Pruebas:** la lógica principal que no dependa de gráficos debe contar con pruebas automatizadas (JUnit).
7. **Documentación:** la arquitectura y el procedimiento para integrar un juego nuevo deben quedar documentados y accesibles para todo el equipo.

## 6. Criterios de éxito del producto

- Los 3 juegos demostrativos funcionan correctamente usando únicamente la API pública del motor.
- Un integrante del equipo que no participó en el desarrollo del núcleo puede integrar un juego nuevo siguiendo solo la documentación.
- El motor soporta partidas de 2 a 4 jugadores sin errores de entrada ni conflictos entre dispositivos.
- El código del núcleo no requiere cambios al agregarse un juego nuevo.
- Las pruebas automatizadas de la lógica principal pasan de forma consistente en cada integración.

## 7. Riesgos y limitaciones iniciales

- **Curva de aprendizaje de libGDX:** el equipo puede requerir tiempo adicional para dominar el framework, afectando los tiempos de sprint.
- **Alcance ambicioso para el tiempo disponible:** desarrollar un motor modular y 3 juegos completos en los sprints planeados es exigente; puede requerir recortar funcionalidades secundarias.
- **Coordinación entre 5 integrantes:** al trabajar todos sobre módulos que interactúan (API vs. juegos), hay riesgo de conflictos de integración si no se respeta estrictamente la separación de responsabilidades.
- **Rendimiento con múltiples entidades:** las colisiones y el manejo de hasta 4 jugadores simultáneos pueden generar cuellos de botella si no se diseñan con cuidado desde el inicio.
- **Dependencia de decisiones tempranas:** cambios tardíos en la arquitectura del `engine-api` podrían obligar a reescribir partes de los juegos demo ya construidos.
- **Disponibilidad de controles físicos para pruebas:** no todos los integrantes podrían tener controles compatibles para probar esa entrada, limitando la cobertura de pruebas manuales.

---
*Este es un borrador inicial*
