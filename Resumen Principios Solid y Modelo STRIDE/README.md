# Michael Sebastian Caicedo Rosero

# Principios SOLID y Modelo STRIDE

Este repositorio contiene ejemplos simples en **Python** que ilustran los **principios SOLID** y el modelo de amenazas **STRIDE**.

---

## Principios SOLID

### 1. Responsabilidad Única (SRP)

Cada función o clase debe tener una sola responsabilidad.

**Ejemplo:** una función que solo calcula un promedio.

---

### 2. Abierto / Cerrado (OCP)

El código debe poder extenderse sin modificarse.

**Ejemplo:** agregar nuevos métodos de pago sin cambiar el código existente.

---

### 3. Sustitución de Liskov (LSP)

Las subclases deben poder reemplazar a su clase base sin romper el comportamiento.

---

### 4. Segregación de Interfaces (ISP)

Interfaces pequeñas y específicas.

---

### 5. Inversión de Dependencias (DIP)

Depender de abstracciones, no de implementaciones concretas.

---

## Modelo STRIDE

STRIDE clasifica amenazas de seguridad en seis categorías:

* **Spoofing**: Suplantación de identidad
* **Tampering**: Manipulación de datos
* **Repudiation**: Negación de acciones
* **Information Disclosure**: Divulgación de información
* **Denial of Service**: Denegación de servicio
* **Elevation of Privilege**: Elevación de privilegios

Cada categoría tiene un ejemplo práctico en el archivo `examples.py`.

---

