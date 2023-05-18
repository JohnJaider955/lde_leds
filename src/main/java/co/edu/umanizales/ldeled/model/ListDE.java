package co.edu.umanizales.ldeled.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;
    private List<Led> leds = new ArrayList<>();

    /**
    -Añadir una luz led:
    si hay datos
        llamo a un ayudante y que se posicione en la cabeza
        mientras en el siguiente nodo, o en el brazo exista algo
            que el ayudante se pase al siguiente nodo
        que se cree un nuevo nodo con el objeto led que se proporcionó previamente
        que el ayudante se pase al nodo siguiente y se establezca como nuevo nodo
        que se establezca el nodo anterior del nuevo nodo con el ayudante
        si está vacía, que se establezca como primer nodo siendo la cabeza

     que se incremente el tamaño de la lista
     */
    public void addLed(Led led) {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != null) {
                temp = temp.getNextDE();
            }
            NodeDE newLed = new NodeDE(led);
            temp.setNextDE(newLed);
            newLed.setPrevious(temp);
        } else {
            this.head = new NodeDE(led);
        }
        size++;
    }

    /**
    -Añadir luz led al inicio
     Si hay datos
        creamos un nuevo nodo con el objeto del led proporcionado
        que se establezca el siguiente nodo como la cabeza
        que se establezca el nodo de la cabeza actual como el nuevo nodo
        que se actualice la cabeza con el nuevo nodo
     si no
        si la lista está vacía, que se establezca el primer nodo como el nuevo nodo.
     que se incremente el tamaño de la lista
     */

    public void addLedsToStart(Led led) {
        if (head != null) {
            NodeDE newNodeDE = new NodeDE(led);
            newNodeDE.setNextDE(head);
            head.setPrevious(newNodeDE);
            head = newNodeDE;
        } else {
            head = new NodeDE(led);
        }
        size++;
    }

    /**
    -Añadir luz led al final
     Se crea un nuevo nodo con en objeto led proporcionado
        si no hay natos
        que se establezca el nuevo nodo como la cabeza y finalice el método
     si no, se llama a un ayudante y que se posicione en la cabeza
     mientras en el siguiente nodo, o en el brazo (o cable) exista algo
        que el ayudante tome el siguiente nodo (o se pase al final)
     que se establezca el siguiente nodo del nodo del ayudante como el nuevo nodo

     */
    public void addLedsToFinal (Led led) {
        NodeDE newNode = new NodeDE(led);
        if (head == null) {
            head = newNode;
            return;
        }
        NodeDE temp = head;
        while (temp.getNextDE() != null) {
            temp = temp.getNextDE();
        }
        temp.setNextDE(newNode);
    }

    /**
    -Encender luces led
     llamo a un ayudante y que se posicione en la cabeza
        mientras hayan datos donde está el ayudante
        que el ayudante prenda el LED del nodo actual estableciendo el estado en true
        que el ayudante tome el siguiente nodo (o se pase al final)
     */
    public void turnOnLeds() {
        NodeDE temp = head;
        while (temp != null) {
            temp.getDataDE().setLedState(true);
            temp = temp.getNextDE();
        }
    }

    /**
    -Apagar luces led
     llamo a un ayudante y que se posicione en la cabeza
        mientras hayan datos donde está el ayudante
        que el ayudante prenda el LED del nodo actual estableciendo el estado en false
        que el ayudante tome el siguiente nodo (o se pase al final)
     */
    public void turnOffLeds() {
        NodeDE temp = head;
        while (temp != null) {
            temp.getDataDE().setLedState(false);
            temp = temp.getNextDE();
        }
    }

    /**
    -Reiniciar Leds
     si en la cabeza hay datos
        le decimos a todos los leds que se apaguen
        le decimos a todos los leds que se enciendan
     */
    public void rebootLeds(){
        if (head != null){
            turnOnLeds();
            turnOffLeds();
        }
    }

        /**
    -Prender y apagar luces desde la mitad. Extremos encendidos
    Si hay datos en la cabeza
        llamo a un ayudante y que se posicione en la cabeza
        inicializo un contador en 1 (teniendo en cuenta que hayan luces en la lista y rastrear la posición)
        inicializo un contador que se llame start que representará la posición del inicio para encender y apagar
        si el tamaño de la lista es impar
        que se calcule la posición del inicio como la mitad del tamaño + 1
        mientras hayan datos
        y el contador es igual a la posición del inicio
        que recorra la lista hasta encontrar el nodo de la posición del inicio
        se le asigna un ayudante que tendrá en cuenta el cable o enlace del siguiente
        ahora que el ayudante encienda y establezca la fecha de encendido para el nodo actual y el siguiente
            ahora que el ayudante recorra todos las luces y los apague, estableciendo la fecha para cada uno
            ahora que se haga el sleep de un segundo para apagar o encender
            que se apaguen las luces led y se establezca la fecha de apagado para los nodos actual y siguiente
            para ello, es necesario que el ayudante también esté atento de las luces previas (enlace) y del siguiente
            ahora que apunte hacia el siguiente el ayudante y se establezca la fecha de encendido para los nodos actual y siguiente
            que se salga del bucle al haber hecho los impares
            que se incremente el contador
            que el ayudante se pase al siguiente o llegue al final
        ahora, si el tamaño de la lista es par
            y dentro de la lista hayan datos
                y el contador es igual a la posición del inicio
                se le asigna otro ayudante que tendrá en cuenta el cable o enlace del siguiente
                ahora que establezca el otro ayudante las fechas de encendido
                mientras sigan habiendo datos en los siguientes enlaces
                que recorra el ayudante los leds, los apague y establezca la fecha de cada uno
                ahora que se haga el sleep de un segundo para apagar o encender
                que se apaguen los leds y vuelva a establecer la fecha de apagado para los nodo actual y siguiente
                ahora, que tome el otro ayudante el enlace anterior y posterior
                que encienda las luces led y establezca la fecha de encendido para los nodos actual y siguiente
                que se salga del bucle al haber hecho los pares
                que se incremente el contador
                que el otro ayudante se pase al siguiente o llegue al final
     */
    public void turnOffOn() {
        if (head != null) {
            NodeDE temp = head;
            int count = 1;
            int start;
            if ((size % 2) != 0) {
                start = (size / 2) + 1;
                while (temp != null) {
                    if (count == start) {
                        NodeDE tempNext = temp;
                        temp.getDataDE().setLedState(true);
                        temp.getDataDE().setDateOn(LocalTime.now());

                        while (tempNext.getNextDE() != null) {
                            long startMillis = System.currentTimeMillis();
                            long elapsedMillis = 0;

                            while (elapsedMillis < 1000) {
                                elapsedMillis = System.currentTimeMillis() - startMillis;
                            }

                            temp.getDataDE().setLedState(false);
                            temp.getDataDE().setDateOff(LocalTime.now());
                            tempNext.getDataDE().setLedState(false);
                            tempNext.getDataDE().setDateOff(LocalTime.now());

                            temp = temp.getPrevious();
                            tempNext = tempNext.getNextDE();

                            temp.getDataDE().setLedState(true);
                            temp.getDataDE().setDateOn(LocalTime.now());
                            tempNext.getDataDE().setLedState(true);
                            tempNext.getDataDE().setDateOn(LocalTime.now());
                        }
                        break;
                    }
                    count++;
                    temp = temp.getNextDE();
                }
            } else {
                start = size / 2;
                while (temp != null) {
                    if (count == start) {
                        NodeDE tempNext = temp.getNextDE();
                        temp.getDataDE().setLedState(true);
                        temp.getDataDE().setDateOn(LocalTime.now());
                        tempNext.getDataDE().setLedState(true);
                        tempNext.getDataDE().setDateOn(LocalTime.now());

                        while (tempNext.getNextDE() != null) {
                            long startMilliSeconds = System.currentTimeMillis();
                            long elapsedMilliSeconds = 0;

                            while (elapsedMilliSeconds < 1000) {
                                elapsedMilliSeconds = System.currentTimeMillis() - startMilliSeconds;
                            }

                            temp.getDataDE().setLedState(false);
                            temp.getDataDE().setDateOff(LocalTime.now());
                            tempNext.getDataDE().setLedState(false);
                            tempNext.getDataDE().setDateOff(LocalTime.now());

                            temp = temp.getPrevious();
                            tempNext = tempNext.getNextDE();

                            temp.getDataDE().setLedState(true);
                            temp.getDataDE().setDateOn(LocalTime.now());
                            tempNext.getDataDE().setLedState(true);
                            tempNext.getDataDE().setDateOn(LocalTime.now());
                        }
                        break;
                    }
                    count++;
                    temp = temp.getNextDE();
                }
            }
        }
    }

    //Para que me pueda imprimir más de dos elementos de la lista
    public List<Led> print(){
        leds.clear();
        if(head != null){
            NodeDE temp = head;
            while(temp != null){
                leds.add(temp.getDataDE());
                temp = temp.getNextDE();
            }
        }
        return leds;
    }
}
