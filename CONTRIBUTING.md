# Guía de colaboración

Este documento explica cómo trabajamos en equipo dentro del repositorio. Su propósito es mantener un proceso sencillo, ordenado y visible para todos.

Estas reglas aplican a todos los integrantes, incluido el administrador del repositorio.

## Regla principal

Nadie debe trabajar ni subir cambios directamente a `main`.

Todo cambio debe:

1. corresponder a un Issue del tablero;
2. realizarse en una rama independiente;
3. enviarse mediante un Pull Request;
4. ser revisado y aprobado por al menos otro integrante;
5. integrarse solamente cuando no queden cambios solicitados ni conversaciones pendientes.

## Estados del tablero

Usamos los siguientes estados en GitHub Projects:

- **Backlog:** trabajo considerado para sprints futuros.
- **Ready:** tarea seleccionada para el sprint actual y lista para comenzar.
- **In progress:** alguien está trabajando en la tarea.
- **In review:** existe un Pull Request o documento esperando revisión.
- **Done:** el trabajo fue aprobado, integrado y comprobado.

Cada Issue debe tener una persona responsable. Cuando cambie el estado real del trabajo, la persona responsable debe mover también su tarjeta.

## Flujo de trabajo

### 1. Seleccionar una tarea

Antes de comenzar:

- elige un Issue ubicado en `Ready`;
- comprueba que estés asignado como responsable;
- lee su descripción, subtareas y criterios de aceptación;
- pregunta en el Issue si algo no está claro.

Cuando comiences, mueve la tarjeta a `In progress`.

### 2. Crear una rama

Actualiza tu copia de `main` y crea una rama exclusiva para el Issue.

Usamos el formato:

```text
tipo/descripcion-corta
```

Tipos permitidos:

- `feat`: funcionalidad nueva.
- `fix`: corrección de un error.
- `docs`: documentación.
- `test`: pruebas.
- `chore`: configuración o mantenimiento.

Ejemplos:

```text
docs/agregar-contributing
docs/completar-readme
feat/crear-ventana-inicial
fix/corregir-movimiento
```

No reutilices una rama para tareas diferentes.

### 3. Realizar los cambios y hacer git add a tus archivos

- Trabaja solamente en lo solicitado por el Issue.
- Evita mezclar correcciones o funcionalidades no relacionadas.
- Comprueba tus cambios antes de subirlos.
- Si descubres trabajo adicional, crea o propone otro Issue.

Nunca subas:

- contraseñas o tokens;
- matrículas o números de cuenta;
- datos personales innecesarios;
- archivos generados que no pertenezcan al proyecto;
- configuraciones privadas del editor o del sistema operativo.

### 4. Crear commits

Los mensajes deben ser breves y explicar claramente el cambio.

Usamos el formato:

```text
tipo: descripción breve
```

Ejemplos:

```text
docs: agrega guía de colaboración
feat: crea ventana inicial del motor
fix: corrige límite de movimiento
```

Puedes realizar varios commits mientras trabajas. El Pull Request se integrará con `Squash and merge` para mantener un historial claro en `main`.

### 5. Abrir un Pull Request

Cuando el trabajo esté listo:

1. sube tu rama a GitHub;
2. abre un Pull Request hacia `main`;
3. escribe un título claro;
4. explica qué cambió y cómo lo comprobaste;
5. relaciona el Pull Request con su Issue;
6. solicita la revisión de otro integrante;
7. mueve la tarjeta a `In review`.

Para relacionar y cerrar automáticamente el Issue al integrar, incluye en la descripción:

```text
Closes #NUMERO_DEL_ISSUE
```

Ejemplo:

```text
Closes #6
```

La descripción del Pull Request debe incluir como mínimo:

```markdown
## Qué cambia

Descripción breve del cambio realizado.

## Cómo se comprobó

Explicación de la revisión, prueba o ejecución realizada.

## Issue relacionado

Closes #NUMERO_DEL_ISSUE
```

## Revisión de Pull Requests

La persona autora no puede aprobar su propio Pull Request. Todo Pull Request necesita al menos una aprobación de otro integrante.

### Responsabilidades del autor

Antes de solicitar revisión, el autor debe comprobar que:

- el cambio cumple el Issue y sus criterios de aceptación;
- no hay cambios ajenos a la tarea;
- el proyecto compila o la documentación se muestra correctamente;
- no hay datos personales ni credenciales;
- la descripción explica cómo comprobar el resultado;
- la tarjeta se encuentra en `In review`.

El autor debe responder los comentarios y realizar los cambios solicitados. No debe resolver una conversación hasta haber atendido o acordado el comentario con el revisor.

### Responsabilidades del revisor

El revisor debe leer el Issue relacionado y revisar la pestaña `Files changed` del Pull Request.

### Probar un Pull Request localmente

Si el cambio modifica código ejecutable, el revisor debe descargar la versión propuesta, compilarla y probarla antes de aprobar.

Cambiar de rama no modifica los commits guardados en tu propia rama. Sin embargo, los cambios locales que todavía no tienen commit pueden mezclarse o impedir el cambio de rama. Por eso, primero ejecuta:

```bash
git status
```

#### Si tu directorio de trabajo está limpio

1. En la página del Pull Request, identifica su número y el nombre de su rama.
2. Descarga la información más reciente del repositorio:

   ```bash
   git fetch origin
   ```

3. Abre temporalmente la rama remota del Pull Request sin crear trabajo nuevo sobre ella:

   ```bash
   git switch --detach origin/NOMBRE-DE-LA-RAMA
   ```

   Ejemplo:

   ```bash
   git switch --detach origin/feat/crear-ventana-inicial
   ```

4. Compila, ejecuta y prueba el proyecto siguiendo las instrucciones del Pull Request.
5. Vuelve a comprobar que no hayas creado cambios que deban conservarse:

   ```bash
   git status
   ```

6. Regresa a la rama en la que estabas antes:

   ```bash
   git switch -
   ```




Nunca uses comandos que descarten cambios locales si no estás completamente seguro de que esos cambios no se necesitan.

Durante la revisión debe comprobar:

1. que el cambio cumple la tarea solicitada.
2. compila, se ejecuta o se muestra correctamente, según corresponda.
3. los nombres, el código y la documentación se entienden.
4. no se incluyeron cambios que pertenecen a otra tarea.
5. no hay credenciales, datos personales ni archivos innecesarios.
6. la documentación fue actualizada cuando el uso del proyecto cambió.

Al terminar la revisión, debe seleccionar una de estas opciones:

- **Comment:** deja observaciones o sugerencias que no impiden integrar.
- **Request changes:** solicita correcciones necesarias antes de integrar.
- **Approve:** confirma que el cambio puede integrarse.

No se debe aprobar un Pull Request sin revisarlo. Los comentarios deben ser claros para que se puedan atender los cambios rapidamente.

Si se agregan cambios importantes después de una aprobación, el Pull Request debe revisarse nuevamente.

## Integrar el Pull Request

Un Pull Request puede integrarse cuando:

- recibió al menos una aprobación;
- no tiene una revisión con `Request changes` pendiente;
- todas las conversaciones fueron atendidas y resueltas;
- cumple los criterios de aceptación del Issue;
- la versión más reciente fue comprobada.

Para integrarlo:

1. selecciona `Squash and merge`;
2. usa un título final claro para el commit;
3. confirma la integración;
4. elimina la rama en GitHub;
5. verifica que el Issue se haya cerrado;
6. mueve la tarjeta a `Done` si no se movió automáticamente.

## Criterio de terminado

Una tarea está `Done` solamente cuando:

- cumple todos sus criterios de aceptación;
- sus cambios fueron comprobados;
- recibió la aprobación de otro integrante;
- fue integrada en `main`;
- el Issue quedó cerrado;
- la tarjeta está en `Done`.


## Cambios en la Wiki

La Wiki tiene un historial separado y sus cambios no pasan por los Pull Requests del repositorio principal.

Para una tarea de la Wiki:

1. mueve el Issue a `In progress`;
2. edita la página correspondiente;
3. escribe un mensaje claro al guardar;
4. mueve la tarjeta a `In review`;
5. pide a otro integrante que revise la página;
6. atiende las observaciones;
7. cierra el Issue y mueve la tarjeta a `Done` cuando sea aprobada.

## Comunicación y decisiones

- Las dudas de una tarea deben escribirse en su Issue.
- Las observaciones sobre un cambio deben escribirse en su Pull Request.
- Las decisiones importantes deben registrarse en la Wiki o en el documento correspondiente.
- Evita que una decisión importante exista solamente en mensajes privados.

Si una tarea cambia de alcance, actualiza primero el Issue para que todo el equipo conozca el nuevo acuerdo.
