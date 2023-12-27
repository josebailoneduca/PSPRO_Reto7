/**
 * 
 */
package reto7_13.b;

/**
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class Condiciones {
/**
 * 3333333333333333
 * volver quien quiera
 * deben viajar 2
 * 
 * 
 * 
 * 222222222222222
 * debe volver cualquiera
 * deben viajar 2 si ha viajado un matematico
 * debe viajar 1 si ha viajado un no matematico
 * 
 * 1111111111111111111
 * debe volver 1
 * debe montar 1
 *  
 *  000000000000000
 * no debe volver un no matematico 
 *  
 * 
 * 
 */
	<p>SELECCION DE LA SUBIDA DE PASAJEROS</p>	
	<ul>
	<li>Si quedan 2 NO matematicos por subir al bote y en el bote hay un matematico: Deben subir ambos NO matematicos</li>
	<li>Si quedan 2 NO matematicos por subir al bote, algun matematico esperando y en el bote hay un NO matematico: Sube 1 matematico y 1 no matematico (para evitar una vuelta con un NO matematico solitario)</li>
	<li>Si queda 1 NO matematico por subir: Debe subir (otra regla asegura que a por el ha tenido que volver 1 NO matematico)</li>
	<li>Si hay 1 matematico en el bote, queda 1 matematico esperando y quedan NO matematicos esperando: Entra 1 NO matematico para evitar el bloqueo</li>
	<li>En cualquier otro caso suben al azar conservando la restriccion de no poder haber 2 matematicos y 1 NO matematico</li>
	<ul>
	
	<p>SELECCION DE QUE PASAJERO HACE LA VUELTA</p>	
	<ul>
	<li>Si no hay nadie esperando a subir al bote entonces bajan todos y el programa termina</li>
	<li>Si solo quedan matematicos esperando vuelve 1 matematico</li>
	<li>Si solo queda 1 NO matematico esperando vuelve 1 NO matematico</li>
	<li>En cualquier otra situacion vuelve cualquiera</li>
	</ul>
}
