PROGRAM PrimeraPrueba; 
CONST 
Mensaje = 'Introduce un valor entero: '; 
Respuesta = 'El valor es: '; 
VAR 
Entero : Integer; 
BEGIN 
Write(Mensaje); 
{Escribe en pantalla el mensaje definido como constante} 
ReadLn(Entero); 
{Lee un valor de teclado y lo almacena en la variable Entero} 
WriteLn(Respuesta, Entero); 
{Escribe en pantalla el contenido de Respuesta y el valor que se ingresó de teclado} 
END. 