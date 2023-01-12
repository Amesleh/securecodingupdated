package securecoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exams {

	private static final String EXAM1 = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\exam1";
	private static final String ANSWERS1 = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\exam1answers";
	private static final String EXAM2 = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\exam2";
	private static final String ANSWERS2 = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\exam2answers";
	private static final String MARKS = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\MARKS";
	private static final String DIRECTORY = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please choose what exam you want to do.\n1.Exam 1\n2.Exam 2");
			int choice = sc.nextInt();
			while (choice != 1 && choice != 2) {
				System.out.println("Invalid choice, please try again..");
				choice = sc.nextInt();
			}
			String exam = "";
			sc.nextLine();
			if (InputValidation(exam, false) == true) {
				System.exit(0);
			}
			BufferedReader questionsReader = null;
			BufferedReader answersReader = null;
			if (choice == 1) {
				exam = "Exam 1";
				questionsReader = new BufferedReader(new FileReader(EXAM1));
				answersReader = new BufferedReader(new FileReader(ANSWERS1));
			} else {
				exam = "Exam 2";
				questionsReader = new BufferedReader(new FileReader(EXAM2));
				answersReader = new BufferedReader(new FileReader(ANSWERS2));
			}

			File file = new File(MARKS);

			Scanner filesc = new Scanner(file);

			while (filesc.hasNextLine()) {
				String line = filesc.nextLine();
				String[] split= line.split(":");
				if (split[0].equals(LoginSystem.username) && split[2].equals(exam)) {
					System.out.println("You have already taken this exam.");
					return;
				}
			}

			int correctAnswers = 0;
			int totalQuestions = 0;

			String question = questionsReader.readLine();
			while ((question = questionsReader.readLine()) != null) {
				totalQuestions++;

				String answerChoices = answersReader.readLine();

				String[] choices = answerChoices.split(",");

				System.out.println(question);

				for (int i = 0; i < choices.length; i++) {
					System.out.println(choices[i]);
				}

				System.out.print("Enter your answer: ");
				String userAnswer = sc.nextLine();
				if (InputValidation(userAnswer, false) == true) {
					System.exit(0);
				}
				String correctAnswer = answersReader.readLine();

				if (userAnswer.equalsIgnoreCase(correctAnswer)) {
					correctAnswers++;
				}
			}

			System.out.println("\n\nYou got " + correctAnswers + " out of " + totalQuestions + " questions correct.");
			sc.close();
			filesc.close();
			try {
				FileWriter fileWriter = new FileWriter(MARKS, true);

				BufferedWriter writer = new BufferedWriter(fileWriter);
				writer.write(LoginSystem.username + ":" + correctAnswers + "/" + totalQuestions + ":" + exam);
				writer.newLine();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			System.out.println("Please try again later.");
		}

	}

	protected static void PrintExam(boolean permission) {
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("Please choose what exam you want to view.\n1.Exam 1\n2.Exam 2");
			int choice = sc.nextInt();
			while (choice != 1 && choice != 2) {
				System.out.println("Invalid choice, please try again..");
				choice = sc.nextInt();
			}
			FileReader fileReader = null;
			if (permission == false) {
				System.out.println("You are not allowed to view this exam.");
				return;
			} else if (permission == true) {
				if (choice == 1) {
					fileReader = new FileReader(EXAM1);
				} else {
					{
						fileReader = new FileReader(EXAM2);
					}
				}

				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String line = null;
				while ((line = bufferedReader.readLine()) != null) {

					System.out.println(line);
				}
				bufferedReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	protected static void PrintMarks(boolean permission) {
		try {
			Scanner sc = new Scanner(System.in);
			FileReader fileReader = new FileReader(MARKS);
			if (permission == false) {
				System.out.println("You are not allowed to view the marks.");
				return;
			} else if (permission == true) {
				System.out.println("Please choose the exam you want the marks of:\n1.Exam 1\n2.Exam 2");
				int choice = sc.nextInt();
				while (choice != 1 && choice != 2) {
					System.out.println("Invalid option, please try again..");
					choice = sc.nextInt();
				}
				String examview = "";
				if (choice == 1) {
					examview = "Exam 1";
				} else {
					examview = "Exam 2";
				}
				Scanner filesc = new Scanner(fileReader);

				while (filesc.hasNextLine()) {
					String line = filesc.nextLine();

					if (line.contains(examview)) {
						System.out.println(line);
					} else {
						String[] split= line.split(":");
						if (split[2].equals(examview)) {
							System.out.println(line);
						}
					}
				}
				fileReader.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean InputValidation(String input, boolean stopcode) {
		int max = 15;
		if (input.length() > max) {
			System.out.println("Error: Input too long.");
			stopcode = true;
			return stopcode;
		}
		byte[] buffer = new byte[max];
		System.arraycopy(input.getBytes(), 0, buffer, 0, input.length());
		return stopcode;
	}

	protected static void CreateExam() {
		System.out.println("Please choose:\n1.Add questions to the question file\n2.Create a new exam.");
		int examnumber = 3;
		Scanner sc = new Scanner(System.in);

		try {

			ArrayList<String> questions = new ArrayList<>();

			System.out.println("Enter your question and 4 answers after it in this format: Question:Answer1:Answer2:Answer3:Answer4. Please type exit when you are done.");
			while (true) {
				String question = sc.nextLine();
				if (question.equals("exit")) {
					break;
				}
				questions.add(question);
			}

			File file = new File(DIRECTORY, "questions.txt");
			PrintWriter writer = new PrintWriter(file);
			for (String question : questions) {
				writer.println(question);
			}
			int j = 1;
			ArrayList<String> examQuestions = new ArrayList<>();
			for (int i = 0; i < questions.size(); i++) {

				System.out.println((i + 1) + ". " + questions.get(i));
				System.out.print("Include this question in the new exam? (yes/no) ");
				String include = sc.nextLine();
				if (include.equals("yes")) {
					
					examQuestions.add("Question "+j+": "+questions.get(i));
					j++;
				}
			}

			String examname = "exam-" + examnumber + ".txt";
			examnumber++;

			File examFile = new File(DIRECTORY, examname);
			PrintWriter newexam = new PrintWriter(examFile);
			for (String examQuestion : examQuestions) {
				newexam.println(examQuestion);
			}
			newexam.close();
			System.out.println("Exam questions saved to file successfully.");

			System.out.println("Exam: ");
			for (String examQuestion : examQuestions) {
				System.out.println(examQuestion);
			}

			newexam.close();
			writer.close();
		} catch (Exception e) {
			System.out.println("Error saving questions to file.");
		}

		sc.close();
	}
}
