package rcCarLoversPortalTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rcCarLoversPortal.Feedback;
import rcCarLoversPortal.RCCarLoversPortal;

public class RCCarLoversPortalTest {
	
	private Feedback fb1;
	private Feedback fb2;
	private Feedback fb3;
	private ArrayList<Feedback> feedbackList;
	
	@Before
	public void setUp() throws Exception {
		// Feedback test data
		fb1 = new Feedback("John",98765432,"I do not like x");
		fb2 = new Feedback("Ken",87654321,"I do not like y");
		feedbackList = new ArrayList<Feedback>();
	}
	//==============================<Feedback>==============================//
	@Test
	public void addFeedbackTest() {
		
		assertNotNull("Check if there is a valid ArrayList to add to", feedbackList);
		
		feedbackList.add(fb1);
		assertEquals("Check that feedbackList's size is 1", 1, feedbackList.size());
		assertSame("Check the correct Feedback is added", fb1, feedbackList.get(0));
		
		feedbackList.add(fb2);
		assertEquals("Check that feedbackList's size is 2", 2, feedbackList.size());
		assertSame("Check the correct Feedback is added", fb2, feedbackList.get(1));
		
		boolean isAdded = RCCarLoversPortal.addFeedback(feedbackList, fb3);
		assertFalse("Check if empty Feedback is added",isAdded);
		assertEquals("Check if empty Feedback is NOT added to feedbackList", 2, feedbackList.size());
	}
	
	@After
	public void tearDown() throws Exception {
		// Feedback test data
		fb1 = null;
		fb2 = null;
		fb3 = null;
		feedbackList = null;
	}
}
