# Laboratorio SSH

Michael Sebastian Caicedo Rosero

En general, lo realizado en los puntos consistió en implementar la lógica básica para actualizar una tarea de forma segura dentro de una API REST para un primer endpoint.

![imagen](imagenes/imagen1.png)

Para un segundo endpoint se implementó la lógica necesaria para eliminar tareas de manera segura dentro de la API.

![imagen](imagenes/imagen2.png)

## 1. Registro de usuario

![imagen](imagenes/imagen3.png)

## 2. Guardar y verificar el token

![imagen](imagenes/imagen4.png)

## 3. Crear y ver lista de tareas

![imagen](imagenes/imagen5.png)

## 4. Actualizar y eliminar tarea

![imagen](imagenes/imagen6.png)

Posteriormente se modificó el servidor para que escuchara solicitudes provenientes de toda la red local.

![imagen](imagenes/imagen7.png)

Después se instaló el servicio OpenSSH Server en Windows utilizando PowerShell con permisos de administrador mediante el comando:

`Add-WindowsCapability -Online -Name OpenSSH.Server~~~~0.0.1.0`

Luego se verificó que el servicio estuviera disponible con:

`Get-Service sshd`

Posteriormente se inició el servicio SSH usando:

`Start-Service sshd`

![imagen](imagenes/imagen7.png)

Después nos conectamos a Termius desde el celular utilizando la misma red WiFi.

![imagen](imagenes/imagen8.png)

## 5. Registro y login

![imagen](imagenes/imagen9.png)

## 6. Crear tarea

![imagen](imagenes/imagen10.png)

## 7. Modificar tarea

![imagen](imagenes/imagen11.png)