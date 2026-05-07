**Laboratorio SSH**

Michael Sebastian Caicedo Rosero

En general, lo hecho en los puntos fue implementar la lógica básica para
actualizar una tarea de forma segura dentro de una API REST para un
primer endpoint.

!\[imagen\](imagenes/imagen1.png)

Y para un segundo endpoint se implementó una la lógica para eliminar
tareas de manera segura dentro de la API..

!\[imagen\](imagenes/imagen2.png)

1.  Registro de usuario

!\[imagen\](imagenes/imagen3.png)

2.  Guardar y verificar el token

!\[imagen\](imagenes/imagen4.png)

3.  Crear y ver lista de tareas

!\[imagen\](imagenes/imagen5.png)

4.  Actualizar y eliminar tarea

!\[imagen\](imagenes/imagen6.png)

Modificamos para que el servidor escuche toda la red

!\[imagen\](imagenes/imagen7.png)

Despues instalamos el servicio OpenSSH Server en Windows utilizando
PowerShell con permisos de administrador mediante el comando:

**Add-WindowsCapability -Online -Name OpenSSH.Server\~\~\~\~0.0.1.0**

Después verificamos que el servicio estuviera disponible con:

**Get-Service sshd**

Luego iniciamos el servicio SSH usando:

**Start-Service sshd**

!\[imagen\](imagenes/imagen7.png)

Posteriormente no conectamos a terminus desde el celular, usando la
misma red wifi.

!\[imagen\](imagenes/imagen8.png)

Registro y login

!\[imagen\](imagenes/imagen9.png)

Crear tarea

!\[imagen\](imagenes/imagen10.png)

Modificar tarea
