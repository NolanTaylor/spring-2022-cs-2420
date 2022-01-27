package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Erin Parker and ??
 * @version January 20, 2022
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass, singleClass;
	private CS2420Student sampleStudent;
	private int sampleUNID = 0;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();
		
		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");
		
		// creates a large class of 1000 random students
		largeClass = new CS2420Class();
		
		for (int i = 0; i < 1000; i++) {
			int uNID = (int) (Math.random() * 10000000);
			if (i == 0) { sampleUNID = uNID; }
			CS2420Student newStudent = new CS2420Student("firstName" + i, "lastName" + i, uNID,
					new EmailAddress("email" + i, "gmail.com"));
			
			for (int j = 0; j < 3; j++) {
				newStudent.addScore(Math.random() * 100, "assignment");
				newStudent.addScore(Math.random() * 100, "exam");
				newStudent.addScore(Math.random() * 100, "lab");
				newStudent.addScore(Math.random() * 100, "quiz");
			}
			
			largeClass.addStudent(newStudent);
		}
		
		sampleStudent = largeClass.getFirstStudent();
		
		singleClass = new CS2420Class();
		singleClass.addStudent(new CS2420Student("Foo", "Bar", 3141592, new EmailAddress("noodle", "outlook.com")));
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, 
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100, 
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();   
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");				
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}
	
	@Test
	public void testVerySmallGetContactList() {
		assertEquals(3, verySmallClass.getContactList().size());
	}
	
	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}
	
	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}
		
	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.5345, student.computeFinalScore(), 0.001);
	}
		
	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.356, smallClass.computeClassAverage(), 0.001);
	}
	
	// Large CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testLargeLookupContactInfo() {
		ArrayList<CS2420Student> actualStudents = largeClass.lookup(new EmailAddress("email0", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertTrue(actualStudents.contains(sampleStudent));
	}
	
	@Test
	public void testLargeGetContactList() {
		ArrayList<EmailAddress> actual = largeClass.getContactList();
		assertEquals(1000, actual.size());
	}
	
	@Test
	public void testLargelookupUNID() {
		assertEquals(sampleStudent, largeClass.lookup(sampleUNID));
	}
	
	// Single CS 2420 class tests -------------------------------------------------------------------------
	
	@Test
	public void testSingleLookupContactInfo() {
		ArrayList<CS2420Student> actualStudents = singleClass.lookup(new EmailAddress("noodle", "outlook.com"));
		assertEquals(1, actualStudents.size());
		assertTrue(actualStudents.contains(singleClass.getFirstStudent()));
	}
	
	@Test
	public void testSingleGetContactList() {
		ArrayList<EmailAddress> contactList = singleClass.getContactList();
		assertEquals(1, contactList.size());
		assertEquals(singleClass.getFirstStudent().getContactInfo(), contactList.get(0));
	}
	
	@Test
	public void testSingleAddScore() {
		singleClass.addScore(3141592, 10, "assignment");
		singleClass.addScore(3141592, 10, "exam");
		singleClass.addScore(3141592, 10, "lab");
		singleClass.addScore(3141592, 10, "quiz");
		
		assertEquals("E", singleClass.lookup(3141592).computeFinalGrade());
	}
	
	// Edge case CS 2420 class tests -------------------------------------------------------------------------
	
	@Test
	public void testAddScoreFalseID() {
		// ensure no exceptions are thrown
		singleClass.addScore(012, 23, "assignment");
	}
	
	@Test
	public void testAddScoreFalseCategory() {
		// ensure no exceptions are thrown and no scores are changed
		singleClass.addScore(3141592, 98, "not_a_category");
		assertEquals("N/A", singleClass.getFirstStudent().computeFinalGrade());
	}
}