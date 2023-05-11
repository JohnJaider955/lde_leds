package co.edu.umanizales.ldeled.model;

import lombok.Data;

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





}
