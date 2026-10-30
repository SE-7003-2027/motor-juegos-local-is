# Guía de colaboración

Estas reglas aplican a todos los integrantes, incluido el administrador.

## Reglas básicas

- No subir cambios directamente a `main`. Usar una rama por tarea y un Pull Request (PR).
- Trabajar únicamente en el alcance del Issue. Registrar ahí las dudas y cambios de alcance.
- No subir credenciales, matrículas, números de cuenta, datos privados ni archivos generados.
- Mantener un responsable y el estado real de cada tarjeta.

## Flujo de trabajo

1. Selecciona un Issue de **Ready**, asígnatelo y muévelo a **In progress**.
2. Con el directorio de trabajo limpio, actualiza `main` y crea tu rama:

   ```bash
   git switch main
   git pull --ff-only origin main
   git switch -c tipo/descripcion
   ```

3. Realiza los cambios. Si modificas código, ejecuta `./gradlew build` y prueba su funcionamiento. Si modificas documentación, revisa su presentación.
4. Agrega únicamente los archivos de la tarea, crea el commit y sube la rama.
5. Abre un PR hacia `main`, solicita revisión y mueve la tarjeta a **In review**.

En Windows, utiliza `gradlew.bat` en lugar de `./gradlew`.

**Nombres:** ramas `tipo/descripcion`; commits `tipo: descripción breve`. Tipos: `feat`, `fix`, `docs`, `test` y `chore`.

El PR debe indicar:

- Qué cambió.
- Cómo se comprobó y cómo probarlo.
- `Closes #NUMERO` para relacionar y cerrar el Issue.

## Revisión de Pull Requests

Lee el Issue y **Files changed**. Comprueba que cumple la tarea, funciona y no incluye cambios ajenos ni datos privados.

Para probar código localmente, primero confirma que tu directorio esté limpio:

```bash
git status
git fetch origin
git switch --detach origin/NOMBRE-DE-LA-RAMA
./gradlew build
```

Ejecuta la aplicación siguiendo el PR. Al terminar, comprueba nuevamente `git status` y regresa con:

```bash
git switch -
```

No descartes cambios locales para cambiar de rama.

En **Review changes**, selecciona:

- **Comment:** observaciones no bloqueantes.
- **Request changes:** correcciones necesarias.
- **Approve:** cambio revisado y aceptado.

## Integrar y terminar

Integra únicamente cuando la última versión esté comprobada, tenga la aprobación requerida de otro integrante y no queden correcciones ni conversaciones pendientes. La aprobación debe cumplir también la regla de revisión independiente de la última subida.

Usa **Squash and merge**, elimina la rama remota y verifica que el Issue quede cerrado. Después mueve su tarjeta a **Done**.

Los cambios posteriores a una aprobación deben revisarse nuevamente.

## Cambios en la Wiki y seguimiento

La Wiki tiene un historial separado: solicita revisión de otro integrante antes de cerrar su Issue.

Registra observaciones del código en el PR y decisiones importantes en el Issue o la documentación.

**Backlog** contiene propuestas futuras; **Ready**, tareas listas para comenzar.