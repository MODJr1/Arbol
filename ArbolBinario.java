public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        raiz = null; // inicializamos el arbol vacio
    }

    // metodo para insertar un nuevo anime en el bst
    public void insertar(String valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, String valor) {
        // si el nodo es null, hemos encontrado la posicion para insertar el nuevo nodo
        if (nodo == null) {
            return new Nodo(valor);
        }
        // comparar el valor para saber donde insertarlo
        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, valor); // insertar en el subarbol izquierdo
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, valor); // insertar en el subarbol derecho
        }
        return nodo;
    }

    // metodo para eliminar un anime
    public void eliminar(String valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo nodo, String valor) {
        if (nodo == null) {
            return null;
        }

        // buscar el nodo a eliminar
        if (valor.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, valor);
        } else if (valor.compareTo(nodo.valor) > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, valor);
        } else {
            // caso 1: nodo sin hijos
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            }
            // caso 2: nodo con un hijo
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            // caso 3: nodo con dos hijos
            nodo.valor = encontrarMinimo(nodo.derecho);
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.valor);
        }
        return nodo;
    }

    private String encontrarMinimo(Nodo nodo) {
        String minimo = nodo.valor;
        while (nodo.izquierdo != null) {
            minimo = nodo.izquierdo.valor;
            nodo = nodo.izquierdo;
        }
        return minimo;
    }

    // metodo para buscar un anime
    public boolean buscar(String valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(Nodo nodo, String valor) {
        if (nodo == null) {
            return false; // no encontrado
        }
        if (valor.compareTo(nodo.valor) == 0) {
            return true; // encontrado
        }
        return valor.compareTo(nodo.valor) < 0 ? buscarRecursivo(nodo.izquierdo, valor) : buscarRecursivo(nodo.derecho, valor);
    }

    // metodo para mostrar los animes en orden alfabetico (recorrido en inorden)
    public void mostrarEnOrden() {
        mostrarEnOrdenRecursivo(raiz);
        System.out.println();
    }

    private void mostrarEnOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            mostrarEnOrdenRecursivo(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            mostrarEnOrdenRecursivo(nodo.derecho);
        }
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        // insercion de animes
        arbol.insertar("Naruto");
        arbol.insertar("One Piece");
        arbol.insertar("Attack on Titan");
        arbol.insertar("Fullmetal Alchemist");
        arbol.insertar("Dragon Ball");

        // mostrar los animes en orden alfabetico
        System.out.println("Animes en orden alfabetico:");
        arbol.mostrarEnOrden();

        // buscar animes
        System.out.println("¿Naruto esta en el arbol? " + arbol.buscar("Naruto"));
        System.out.println("¿Bleach esta en el arbol? " + arbol.buscar("Bleach"));

        // eliminar un anime
        System.out.println("Eliminando 'One Piece'");
        arbol.eliminar("One Piece");

        // mostrar los animes despues de la eliminacion
        System.out.println("Animes despues de eliminar 'One Piece':");
        arbol.mostrarEnOrden();
    }
}
