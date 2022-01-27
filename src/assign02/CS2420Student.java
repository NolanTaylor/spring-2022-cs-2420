package assign02;

public class CS2420Student extends UofUStudent {
	private EmailAddress contactInfo;
	private double examScore, assignmentScore, quizScore, labScore = 0.0;
	private int examsTaken, assignmentsTaken, quizzesTaken, labsTaken = 0;
	
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super (firstName, lastName, uNID);
		
		this.contactInfo = contactInfo;
	}

	public EmailAddress getContactInfo() {
		return contactInfo;
	}
	
	public void addScore(double score, String category) {
		switch (category) {
		case "assignment":
			assignmentScore += score;
			assignmentsTaken++;
			break;
		case "exam":
			examScore += score;
			examsTaken++;
			break;
		case "lab":
			labScore += score;
			labsTaken++;
			break;
		case "quiz":
			quizScore += score;
			quizzesTaken++;
			break;
		default:
			break;
		}
	}
	
	public double computeFinalScore() {
		double finalScore = 0.0;
		if (examScore / examsTaken >= 65) {
			finalScore += (assignmentScore / assignmentsTaken) * 0.40;
			finalScore += (examScore / examsTaken) * 0.40;
			finalScore += (labScore / labsTaken) * 0.10;
			finalScore += (quizScore / quizzesTaken) * 0.10;
		}
		else {
			finalScore = (examScore / examsTaken);
		}
		
		if (assignmentsTaken == 0 || examsTaken == 0 || labsTaken == 0 || quizzesTaken == 0) {
			finalScore = 0.0;
		}
		
		return finalScore;
	}
	
	public String computeFinalGrade() {
		double finalScore = computeFinalScore();
		String finalGrade = null;
		
		if (finalScore >= 93) {
			finalGrade = "A";
		}
		else if (finalScore >= 90) {
			finalGrade = "A-";
		}
		else if (finalScore > 87) {
			finalGrade = "B+";
		}
		else if (finalScore > 83) {
			finalGrade = "B";
		}
		else if (finalScore > 80) {
			finalGrade = "B-";
		}
		else if (finalScore > 77) {
			finalGrade = "C+";
		}
		else if (finalScore > 73) {
			finalGrade = "C";
		}
		else if (finalScore > 70) {
			finalGrade = "C-";
		}
		else if (finalScore > 67) {
			finalGrade = "D+";
		}
		else if (finalScore > 63) {
			finalGrade = "D";
		}
		else if (finalScore > 60) {
			finalGrade = "D-";
		}
		else {
			finalGrade = "E";
		}
		
		if (assignmentsTaken == 0 || examsTaken == 0 || labsTaken == 0 || quizzesTaken == 0) {
			finalGrade = "N/A";
		}
		
		return finalGrade;
	}
}
