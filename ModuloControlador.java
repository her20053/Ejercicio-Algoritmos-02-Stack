import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
/**
 * @author Jose Andres Hernandez Guerra 20053
 * Implementacion de la hoja de Trabajo 02 Algoritmos
 * y Estructura de Datos - 1 de febrero 2021 - 
 * clase ModuloControlador.java
 */
public class ModuloControlador{
    /**
     * 
     * Metodo main en el que inicia el programa >>
     * Ejecuta el programa llamando a la funcion Controlador()
     * @param args Parametro utilizado por el metodo main del JDK
     * 
     */
    public static void main(String[] args) {

        // Para leer archivos es necesario crear un try-catch debido a que se puede dar el error de que el 
        // Scanner no encuentre un input valido.
        try{

            File archivoTexto = new File("datos.txt"); 

            Scanner scTexto = new Scanner(archivoTexto);

            String operacionObtenida = scTexto.nextLine();

            System.out.println("Operacion obtenida: " + operacionObtenida); 

            // Luego de leer el archivo, la operacion en forma de String es pasada como parametro al metodo 
            // Controlador() sin espacios para poder administrar solamente los caracteres que son necesarios.
            Controlador(operacionObtenida.replace(" ",""));

        } catch (FileNotFoundException errorArchivoNoEncontrado) {
            // Se le advierte al usuario que el archivo no es existente, se termina el programa.
            System.out.println("Archivo de texto 'datos.txt' no ha sido encontrado.");
        }
    }
    /**
     * Funcion encargada de administrar e interpretar el texto utilizado como parametro el cual es la operacion
     * que se ha leido del archivo de texto. Esta funcion no sera utilizada si el archivo no ha sido encontrado
     * @param STRoperacion Operacion que se analiza ejemplo ' 1 2 + 4 * 3 + '
     */
    private static void Controlador(String STRoperacion){ 
        // Instancia de la clase Generica "VECTOR", este tomara como parametro Integer debido a que 
        // las instrucciones especifcan que se trabajaran solamente con enteros.
        VECTOR<Integer> stack = new VECTOR<>();
        Calculadora cl = new Calculadora();
        // Forloop encargado de recorrer cada pos(n) de STRoperacion[n] con su metodo length -> int:
        for(int pos = 0; pos < STRoperacion.length(); pos++){
            // Se utiliza try por castear y pushear un Integer al stack, si este proceso tira error es porque se esta
            // trabajando con un caracter, en el catch este es procesado.
            try {
                stack.push( Integer.parseInt(Character.toString(STRoperacion.charAt(pos))) );
            } catch (Exception e) {
                // Para este punto en el cual se procesa un caracter ya han de haber por lo menos dos enteros 
                // almmacenados, por lo que se eliminan del Stack y se almacenan como ints temporales.
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                // El caracter es analizado en el siguiente Switch, reconociendo que signo de operacion se emplea,
                // se realiza la operacion entre los dos caracteres y posteriormente el resultado es pusheado al Stack.
                int numeroPush = (int)Math.round(cl.resolver(temp1, temp2, STRoperacion.charAt(pos)));
                stack.push(numeroPush);
            }
        }
        // Al finalizar el forloop se imprime el peek del Stack que en teoria siempre es el unico dato restante por lo que 
        // da lo mismo utilzar el metodo peek() o pop() debido a que ya es el final del programa. 
        System.out.println("El resultado de la operacion es de: " + stack.peek());
    }
}
/**
 * @author Jose Andres Hernandez Guerra 20053
 * Implementacion de la hoja de Trabajo 02 Algoritmos
 * y Estructura de Datos - 1 de febrero 2021 - 
 * interface ICalculadora.java
 */
interface ICalculadora{
    // Uso de sobrecarga de metodos debido a que solo se pueden dar ocasiones
    // en las que se tengan que usar ambos o casos especificos.
    public Double resolver(String input);
    public Double resolver(int n1, int n2, char operando);
}
/**
 * @author Jose Andres Hernandez Guerra 20053
 * Implementacion de la hoja de Trabajo 02 Algoritmos
 * y Estructura de Datos - 1 de febrero 2021 - 
 * clase Calculadora.java
 */
class Calculadora implements ICalculadora{
    @Override
    public Double resolver(String input) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    /**
     * @param n1, numero entero uno
     * @param n2, numero entero dos
     * @param operando, caracter que se estara utilizando
     * en la operacion. 
     * @return se retorna un Double siendo este el resultado
     * de ambos numeros operados con su caracter.
     */
    public Double resolver(int n1, int n2, char operando) {
        switch(operando){
            case '+': 
            return Double.valueOf(n1 + n2);
            case '-':
            return Double.valueOf(n1 - n2);
            case '/':
            return Double.valueOf(n1 / n2);
            case '*':
            return Double.valueOf(n1 * n2);
        }
        return null;
    }
}
/**
 * @author Jose Andres Hernandez Guerra 20053
 * Implementacion de la hoja de Trabajo 02 Algoritmos
 * y Estructura de Datos - 1 de febrero 2021 - 
 * clase VECTOR.java
 */
class VECTOR<E> implements Stack<E>{
    // Al ser un almacenamiento lineal 'o(n)' los datos son almacenados como vector(Implementando metodos de la clase List). 
    private Vector<E> numerosAcumulados = new Vector<>();

    @Override
    /**
     * Encargado de almacenar en el Vector el dato que se pasa como parametro
     */
    public void push(E item) {
        numerosAcumulados.add(item);
    }
    @Override
    /**
     * Encargado de eliminar el dato mas actualizado de la lista y posteriormente retornarlo
     */
    public E pop() {
        E objetoTemp = numerosAcumulados.get(numerosAcumulados.size() - 1);
        numerosAcumulados.remove(numerosAcumulados.size() - 1);
        return objetoTemp;
    }
    @Override
    /**
     * Devuelve el ultimo numero agregado al Vector.
     */
    public E peek() {
        return numerosAcumulados.get(numerosAcumulados.size() - 1);
    }
    @Override
    /**
     * Retorna si el Vector esta vacio - true / tiene datos - false
     */
    public boolean empty() {
        return numerosAcumulados.isEmpty();
    }
    @Override
    /**
     * Retorna el Size del Vector
     */
    public int size() {
        return numerosAcumulados.size();
    }
}
interface Stack<E> 
{
   public void push(E item);
   // pre: 
   // post: item is added to stack
   // will be popped next if no intervening push
   
   public E pop();
   // pre: stack is not empty
   // post: most recently pushed item is removed and returned
   
   public E peek();
   // pre: stack is not empty
   // post: top value (next to be popped) is returned
   
   public boolean empty();
   // post: returns true if and only if the stack is empty
   
   public int size();
   // post: returns the number of elements in the stack
}



        