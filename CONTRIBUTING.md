# ✨ Guía para Trabajar con Ramas en Git y GitHub (Terminal & Android Studio)

## **1. Clonar el Repositorio**

Si es la primera vez que descargas el proyecto, usa el siguiente comando en la terminal:

```bash
git clone https://github.com/usuario/ProyectoFinal.git
cd ProyectoFinal
```

**En Android Studio:**

1. **VCS > Get from Version Control**.
2. **Introduce la URL del repositorio** y selecciona la carpeta donde deseas clonar.
3. Pulsa **Clone**.

---

## **2. Tipos de Ramas en el Proyecto**

Este repositorio sigue una estructura organizada con las siguientes ramas:

- **`main`** → Rama principal protegida. Solo se fusionan cambios desde `develop` mediante Pull Requests.
- **`develop`** → Rama de desarrollo protegida. Aquí se integran las nuevas funcionalidades antes de fusionarlas en `main`.
- **`feature/*`** → Ramas para desarrollar nuevas funcionalidades.
- **`fix/*`** → Ramas para corregir errores y bugs.
- **`conceptTesting/*`** → Ramas para probar ideas y conceptos antes de implementarlos en `feature/`.

⚠ **Las ramas `main` y `develop` están protegidas.** Para fusionar cambios en ellas, se debe hacer una **Pull Request (PR)** en GitHub.

---

## **3. Obtener las Ramas desde GitHub**

Si las ramas ya existen en GitHub pero no en tu equipo, sincroniza con:

```bash
git fetch --all
```

**En Android Studio:**

1. **VCS > Git > Fetch** para obtener las ramas remotas.
2. Luego ve a **Git > Branches > Remote Branches** para verlas.

---

## **4. Cambiar de Rama**

**En la Terminal:**

```bash
git checkout nombre-de-la-rama
```

**Ejemplo:**

```bash
git checkout develop
```

Si la rama solo existe en GitHub y no la tienes en local:

```bash
git checkout -b nombre-de-la-rama origin/nombre-de-la-rama
```

**En Android Studio:**

1. Ve a **Git > Branches**.
2. En **Remote Branches**, selecciona la rama y haz clic en **Checkout**.

---

## **5. Crear una Nueva Rama**

**Para nuevas funcionalidades (`feature/`):**

```bash
git checkout -b feature/nueva-funcionalidad
```

**Para correcciones de errores (`fix/`):**

```bash
git checkout -b fix/error-en-login
```

Súbela a GitHub con:

```bash
git push -u origin nombre-de-la-rama
```

**En Android Studio:**

1. **Git > Branches > New Branch**.
2. Introducir el nombre y confirmar.
3. Para subirla a GitHub, ir a **VCS > Git > Push**.

---

## **6. Hacer Cambios y Subirlos**

**En la Terminal:**

```bash
git add .
git commit -m "Descripción clara del cambio"
git push origin nombre-de-la-rama
```

**En Android Studio:**

1. **VCS > Commit**.
2. Escribir un mensaje de commit y presionar **Commit**.
3. Luego ir a **VCS > Git > Push** para subir los cambios.

---

## **7. Fusionar una Rama en `develop` (Mediante Pull Request)**

Cuando una funcionalidad esté terminada:

1. **Subir los cambios a GitHub**
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
2. **Abrir GitHub y crear una Pull Request (PR)**
    - Ir al repositorio en GitHub.
    - Ir a la pestaña **Pull Requests**.
    - Crear una nueva PR desde `feature/nueva-funcionalidad` hacia `develop`.
    - Esperar revisión y aprobación.
3. **Cuando se apruebe la PR**, la funcionalidad quedará fusionada en `develop`.
4. **Eliminar la rama local y remota si ya no es necesaria:**
   ```bash
   git branch -d feature/nueva-funcionalidad
   git push origin --delete feature/nueva-funcionalidad
   ```

**En Android Studio:**

1. **VCS > Git > Push** para subir los cambios.
2. Ir a GitHub y seguir los pasos anteriores para la Pull Request.

---

## **8. Mantener el Proyecto Actualizado**

Antes de empezar a trabajar cada día, asegúrate de tener los últimos cambios.

**En la Terminal:**

```bash
git checkout develop
git pull origin develop
git fetch --all
```

Si estás trabajando en una rama, también puedes traer los últimos cambios de `develop`:

```bash
git merge develop
```

**En Android Studio:**

1. **Git > Pull** para actualizar `develop`.
2. Si estás en una rama, ve a **Git > Merge Changes** y selecciona `develop`.

---

## **9. Pruebas y Experimentación en `conceptTesting`**

Para probar ideas sin afectar `develop`, usa la rama `conceptTesting/concept`:

**En la Terminal:**

```bash
git checkout conceptTesting/concept
git pull origin conceptTesting/concept
```

**En Android Studio:**

1. **Git > Branches**, seleccionar `conceptTesting/concept` y hacer **Checkout**.
2. Hacer cambios, commit y push normalmente.

Si decides convertir una prueba en una funcionalidad real, crea una nueva rama `feature/` desde `develop` y mueve el código allí.

---

## **Conclusión**

Siguiendo estos pasos, podréis trabajar en equipo sin pisaros el código y asegurando que cada cambio esté bien organizado en Git y GitHub, ya sea desde la **terminal o Android Studio**. 🚀

⚠ **Recuerda que `main` y `develop` están protegidas.** Todo cambio debe hacerse mediante **Pull Requests**.

