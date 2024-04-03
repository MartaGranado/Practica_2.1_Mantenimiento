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
            assertThrows(DoubleLinkedQueueException.class, list::first);
            assertThrows(DoubleLinkedQueueException.class, list::last);
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

            assertThrows(DoubleLinkedQueueException.class, list::first);
            assertThrows(DoubleLinkedQueueException.class, list::last);
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
    @DisplayName("Operaciones get, remove y sort")
    class Operaciones {

        @Test
        @DisplayName("Obtiene el elemento en una posición específica de forma correcta")
        void get_TieneVariosElementos_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            assertEquals(20, list.get(1));
        }

        @Test
        @DisplayName("Lanza IndexOutOfBoundsException si se intenta obtener un elemento fuera de los límites")
        void get_ElementoFueraDeLimites_ThrowsIndexOutOfBoundsException() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        }

        @Test
        @DisplayName("Comprueba si un elemento está presente en la lista")
        void contains_TieneVariosElementos_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            assertTrue(list.contains(20));
            assertFalse(list.contains(40));
        }

        @Test
        @DisplayName("No hace nada si el elemento a eliminar no está presente en la lista")
        void remove_ListaVacia_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            list.append(10);
            list.append(20);

            list.remove(40);

            assertEquals(2, list.size());
            assertTrue(list.contains(10));
            assertTrue(list.contains(20));
        }

        @Test
        @DisplayName("Elimina el elemento presente en una lista con un solo elemento y la deja vacia")
        void remove_TieneUnElemento_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);

            list.remove(10);

            assertEquals(0, list.size());
            assertFalse(list.contains(10));
        }

        @Test
        @DisplayName("Elimina un elemento presente en una lista con 2 elementos")
        void remove_TieneDosElementos_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);

            list.remove(20);

            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Elimina un elemento presente en la lista y asigna nuevos elementos como primero o último en caso de que sea necesario")
        void remove_TieneMultiplesElementos_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
            list.append(10);
            list.append(20);
            list.append(30);

            list.remove(20);

            assertEquals(2, list.size());
            assertEquals(10, list.first());
            assertEquals(30, list.last());
        }

        @Test
        @DisplayName("Ordena la lista según el comparador proporcionado")
        void sort_TieneVariosElementos_DevuelveValorCorrecto() {
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
