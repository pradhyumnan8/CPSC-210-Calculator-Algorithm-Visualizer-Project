package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionLinkedListTest {

    ExpressionLinkedList emptyList;
    ExpressionLinkedList twoLongList;
    ExpressionNode node1;
    ExpressionNode node2;

    @BeforeEach
    void runBefore() {
        node1 = new ExpressionNode();
        node2 = new ExpressionNode();

        node1.setOperand(1);
        node2.setOperand(2);

        emptyList = new ExpressionLinkedList();
        twoLongList = new ExpressionLinkedList();

        twoLongList.insertAfterHead(node1.getOperand());
        twoLongList.insertAfterHead(node2.getOperand());
    }

    @Test
    void testIsEmptyTrue() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        assertFalse(twoLongList.isEmpty());
    }

    @Test
    void testDeleteAfterNodeShouldReturnFalse() {
        assertNull(twoLongList.deleteAfterHead(false));
    }

    @Test
    void testDeleteAfterNodeShouldReturnWithEmptyList() {
        assertNull(emptyList.deleteAfterHead(false));
    }

    @Test
    void testDeleteAfterNodeShouldReturnTrue() {
        twoLongList.deleteAfterHead(true);
        assertEquals(1, twoLongList.getListCount());
    }



}
