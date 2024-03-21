package org.mps.deque;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedNodeTest {

    LinkedNode<Integer> nodoBase = new LinkedNode<>(null, null, null);

    @Nested
    @DisplayName("Creando un item de LinkedNode")
    class SetItem {

        @Test
        @DisplayName("Deberia hacer set del item de forma correcta")
        void setItem_DevuelveValorCorrecto() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, null);

            node.setItem(10);

            assertEquals(10, node.getItem());
        }
    }

    @Nested
    @DisplayName("Mira si LinkedNode es el ultimo nodo")
    class UltimoNodo {

        @Test
        @DisplayName("Devuelve verdadero si es el ultimo nodo")
        void isLastNode_EsUltimoNodo_DevuelveVerdadero() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, null);

            assertTrue(node.isLastNode());
        }

        @Test
        @DisplayName("Devuelve falso si no es el ultimo nodo")
        void isLastNode_NoUltimoNodo_DevuelveFalso() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, new LinkedNode<>(null, nodoBase, null));

            assertFalse(node.isLastNode());
        }
    }

    @Nested
    @DisplayName("Mira si LinkedNode es el primer nodo")
    class PrimerNodo {

        @Test
        @DisplayName("Devuelve verdadero si es el primer nodo")
        void isFirstNode_EsPrimerNodo_DevuelveVerdadero() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, null);

            assertTrue(node.isFirstNode());
        }

        @Test
        @DisplayName("Devuelve falso si no es el primer nodo")
        void isFirstNode_NoPrimerNodo_DevuelveFalso() {
            LinkedNode<Integer> node = new LinkedNode<>(null, new LinkedNode<>(null, null, nodoBase), null);

            assertFalse(node.isFirstNode());
        }
    }

    @Nested
    @DisplayName("Mira si LinkedNode no es un nodo terminal")
    class NoNodoTerminal {

        @Test
        @DisplayName("Devuelve verdadero si no es un nodo terminal")
        void isNotATerminalNode_NoTerminal_DevuelveVerdadero() {
            LinkedNode<Integer> node = new LinkedNode<>(null, new LinkedNode<>(null, null, nodoBase),
                    new LinkedNode<>(null, nodoBase, null));

            assertTrue(node.isNotATerminalNode());
        }

        @Test
        @DisplayName("Devuelve falso si es un nodo terminal")
        void isNotATerminalNode_Terminal_DevuelveFalso() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, null);

            assertFalse(node.isNotATerminalNode());
        }

        @Test
        @DisplayName("Devuelve falso si el nodo terminal es el primer nodo")
        void isNotATerminalNode_EsPrimerNodo_DevuelveFalso() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, new LinkedNode<>(null, nodoBase, null));

            assertFalse(node.isNotATerminalNode());
        }

        @Test
        @DisplayName("Devuelve falso si el nodo terminal es el ultimo nodo")
        void isNotATerminalNode_EsUltimoNodo_DevuelveFalso() {
            LinkedNode<Integer> node = new LinkedNode<>(null, new LinkedNode<>(null, null, nodoBase), null);

            assertFalse(node.isNotATerminalNode());
        }

        @Test
        @DisplayName("Devuelve falso si el nodo terminal es el unico nodo")
        void isNotATerminalNode_EsUnicoNodo_DevuelveFalso() {
            LinkedNode<Integer> node = new LinkedNode<>(null, null, null);

            assertFalse(node.isNotATerminalNode());
        }

    }
}
