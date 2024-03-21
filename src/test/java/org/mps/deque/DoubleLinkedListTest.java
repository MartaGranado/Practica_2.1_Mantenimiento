package org.mps.deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DoubleLinkedListTest {
    @Nested
    class Creacion {

        @Test
        @DisplayName("Al crear esta vacio")
        void constructor_DevuelveValorCorrecto() {
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            assertEquals(0, list.size());
            assertNull(list.first());
            assertNull(list.last());
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
            // Arrange
            DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

            // Act & Assert
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
}
