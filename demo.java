package securecoding;

import java.util.Scanner;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class demo extends LoginSystem {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String answer;
		boolean success = null != null;

		System.out.println("Hello! are you a student?");
		answer = sc.next();
		if (InputValidation(answer, false) == true) {
			System.exit(0);
		}
		if (answer.equalsIgnoreCase("yes")) {
			if (login(success, 3)) {
				Student.main(args);
			} else {
				System.out.println("Login unsuccessful, please try again..");
			}
		} else if (answer.equalsIgnoreCase("no")) {
			System.out.println("Are you a teacher?");
			answer = sc.next();
			if (InputValidation(answer, false) == true) {
				System.exit(0);
			}
			if (answer.equalsIgnoreCase("yes")) {
				if (login(success, 2)) {
					Teachers.main(args);
				} else {
					System.out.println("Login unsuccessful, please try again..");
				}
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Are you an admin?");
				answer = sc.next();
				if (InputValidation(answer, false) == true) {
					System.exit(0);
				}
				if (answer.equalsIgnoreCase("yes")) {
					if (login(success, 1)) {
						Admin.main(args);
					} else {
						System.out.println("Login unsuccessful, please try again..");
					}
				}
			}
		}

	}

}
