package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("1+1");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
        int hash = 0;

		assertEquals("1+1", e.getDescription());
        assertEquals(false, e.getDescription().equals("testing"));
        assertEquals(false, e.getDescription().equals(null));
		assertEquals(d.toString(), e.getDate().toString());

        assertEquals(false, e.equals("hello"));
        assertEquals(false, e.equals(null));

        hash = e.hashCode();

        assertEquals(hash, e.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "1+1", e.toString());
	}
}
