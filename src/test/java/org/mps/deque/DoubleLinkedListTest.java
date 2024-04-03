package org.mps.deque;

/*
 * Marta Granado Rodriguez
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DoubleLinkedListTest {
    @Nested
    @DisplayName("Inicializacion del constructor")
    class Creacion {

        @Test
        @DisplayName("Al crear esta vacio")
        void constructor_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            assertEquals(0, list.size());
            assertThrows(DoubleLinkedQueueException.class, list::first);
            assertThrows(DoubleLinkedQueueException.class, list::last);
        }
    }

    @Nested
    @DisplayName("Añade elementos a la DoubleLinkedList")
    class AniadirElementos {

        @Test
        @DisplayName("Debería hacer prepend de forma correcta")
        void prepend_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            list.prepend(10);
            list.prepend(20);

            assertEquals(2, list.size());
            assertEquals(20, list.first());
            assertEquals(10, list.last());
        }

        @Test
        @DisplayName("Debería hacer append de forma correcta")
        void append_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            list.append(10);
            list.append(20);

            assertEquals(2, list.size());
            assertEquals(10, list.first());
            assertEquals(20, list.last());
        }

    }

    @Nested
    @DisplayName("Cuando eliminamos elementos de la DoubleLinkedList")
    class EliminarElementos {

        @Test
        @DisplayName("Elimina el primer elemento correctamente y queda solo uno")
        void deleteFirst_TieneDosElementos_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);

            list.deleteFirst();

            assertEquals(1, list.size());
            assertEquals(20, list.first());
            assertEquals(20, list.last());
        }

        @Test
        @DisplayName("Elimina el primer elemento correctamente y no queda ninguno")
        void deleteFirst_TieneUnElemento_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            list.deleteFirst();

            assertEquals(0, list.size());
            assertNull(list.first());
            assertNull(list.last());
        }

        @Test
        @DisplayName("Lanza DoubleLinkedQueueException al intentar eliminar el primer elemento de una lista vacia")
        void deleteFirst_ListaVacia_ThrowsDoubleLinkedQueueException() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, list::deleteFirst);
        }

        @Test
        @DisplayName("Elimina el ultimo elemento correctamente y queda solo uno")
        void deleteLast__TieneDosElementos_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);

            list.deleteLast();

            assertEquals(1, list.size());
            assertEquals(10, list.first());
            assertEquals(10, list.last());
        }

        @Test
        @DisplayName("Elimina el ultimo elemento correctamente y no queda ninguno")
        void deleteLast_TieneUnElemento_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            list.deleteLast();

            assertEquals(0, list.size());
            assertNull(list.first());
            assertNull(list.last());
        }

        @Test
        @DisplayName("Lanza DoubleLinkedQueueException al intentar eliminar el ultimo elemento de una lista vacia")
        void deleteLast_ListaVacia_ThrowsDoubleLinkedQueueException() {

            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, list::deleteLast);
        }

    }

    @Nested
    @DisplayName("Operaciones con listas vacias")
    class EmptyListOperations {

        @Test
        @DisplayName("Lanza DoubleLinkedQueueException cuando pide el primer elemento y la lista es vacia")
        void first_ListaVacia_ThrowsDoubleLinkedQueueException() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, list::first);
        }

        @Test
        @DisplayName("Lanza DoubleLinkedQueueException cuando pide el ultimo elemento y la lista es vacia")
        void last_ListaVacia_ThrowsDoubleLinkedQueueException() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, list::last);
        }
    }

    @Nested
    @DisplayName("Operaciones de búsqueda, eliminación y ordenamiento")
    class Operaciones {

        @Test
        @DisplayName("Obtiene el elemento en una posición específica")
        void get_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            assertEquals(20, list.get(1));
        }

        @Test
        @DisplayName("Lanza IndexOutOfBoundsException si se intenta obtener un elemento fuera de los límites")
        void get_ElementoFueraDeLimites_LanzaIndexOutOfBoundsException() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        }

        @Test
        @DisplayName("Comprueba si un elemento está presente en la lista")
        void contains_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            assertTrue(list.contains(20));
            assertFalse(list.contains(40));
        }

        @Test
        @DisplayName("Elimina un elemento presente en la lista")
        void remove_EliminaElementoExistente_ListaActualizada() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            list.remove(20);

            assertFalse(list.contains(20));
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("No hace nada si el elemento a eliminar no está presente en la lista")
        void remove_ElementoNoPresente_NoCambiaLaLista() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            list.remove(40);

            assertEquals(3, list.size());
        }

        @Test
        @DisplayName("Ordena la lista según el comparador proporcionado")
        void sort_OrdenaListaCorrectamente() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(30);
            list.append(10);
            list.append(20);

            list.sort(Comparator.naturalOrder());

            assertEquals(10, list.get(0));
            assertEquals(20, list.get(1));
            assertEquals(30, list.get(2));
        }
    }
}
