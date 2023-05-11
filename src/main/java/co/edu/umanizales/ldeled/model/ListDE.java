package co.edu.umanizales.ldeled.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

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
     mientras en el siguiente nodo, o en el brazo exista algo
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

    public void turnOnLedId(int id) {
        if (head != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != null) {
                if (temp.getDataDE().getId() == id) {
                    temp.getDataDE().setLedState(true);
                    break;
                }
                temp = temp.getNextDE();
            }
        }
    }

    public void turnOffLedId(int id) {
        if (head != null) {
            NodeDE temp = this.head;
            while (temp.getNextDE() != null) {
                if (temp.getDataDE().getId() == id) {
                    temp.getDataDE().setLedState(false);
                    break;
                }
                temp = temp.getNextDE();
            }
        }
    }

    public void rebootLeds() {
        NodeDE temp = head;
        while (temp != null) {
            temp.getDataDE().setLedState(false);
            temp.getDataDE().setDateOff(null);
            temp.getDataDE().setDateOn(null);
            temp = temp.getNextDE();
        }
    }

    /**
    -Prender y apagar luces desde la mitad
     Si hay datos
        llamo dos ayudantes y que se paren ambos en la cabeza
        inicializo dos contadores en cero
        inicio una variable donde me va a calcular el punto medio de la lista

        mientras, que se haga un recorrido a la lista con el contador dos para encontrar el punto medio de la lista
            llamo al segundo ayudante que me va a recorrer los nodos hasta encontrar la mitad
            si encuentra, que se incremente
            mientras donde está el segundo ayudante, hay datos
            si el contador es impar, enciende la luz led y establece la fecha y hora de encendido
            se crea un try catch exception para poder realizar el sleep y esperar 1 segundo
            que el segundo ayudante obtenga los datos de donde está parado y haga el proceso de apagar la luz
            que el segundo ayudante pase al siguiente nodo y se incremente

        mientras hayan datos nuevamente
        que el primer ayudante se posicione en el contador impar, haciendo la operación para indicar que es impar
        que establezca la hora y fecha de cuando se enciende
        que se cree el try catch exception para poder realizar el sleep y esperar 1 segundo
        que el primer ayudante obtenga los datos donde está parado y haga el proceso de apagar la luz
        ahora que el primer ayudante obtenga se pase al nodo anterior para conectar los demás y que se incremente.
     */
    public void turnOnAndOffLeds() {
        if (head != null) {
            NodeDE temp = head;
            NodeDE temp2 = head;
            int count1 = 0;
            int count2 = 0;
            int midpoint = size / 2;

            while (count2 < midpoint) {
                temp2 = temp2.getNextDE();
                count2++;
            }
            while (temp2 != null) {
                if (count2 % 2 != 0) {
                    temp2.getDataDE().setLedState(true);
                    temp2.getDataDE().setDateOn(LocalTime.now());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp2.getDataDE().setLedState(false);
                    temp2.getDataDE().setDateOff(LocalTime.now());
                }
                temp2 = temp2.getNextDE();
                count2++;
            }
            while (temp != null) {
                if (count1 % 2 != 0) {
                    temp.getDataDE().setLedState(true);
                    temp.getDataDE().setDateOn(LocalTime.now());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    temp.getDataDE().setLedState(false);
                    temp.getDataDE().setDateOff(LocalTime.now());
                }

                temp = temp.getPrevious();
                count1++;
            }
        }
    }
}
