package securecoding;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginSystem extends Exams {
	private static final String ADMIN = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\adminlogin";
	private static final String TEACHER = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\teacherlogin";
	private static final String STUDENT = "C:\\Users\\abols\\workspace\\main\\src\\securecoding\\studentlogin";
	public static String username = "";

	private static String encryptPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] passwordBytes = password.getBytes();
		byte[] encryptedPasswordBytes = md.digest(passwordBytes);

		StringBuilder sb = new StringBuilder();
		for (byte b : encryptedPasswordBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	protected static boolean login(boolean success, int accounttype) throws NoSuchAlgorithmException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		File file = null;
		if (accounttype == 1) {
			file = new File(ADMIN);
		} else if (accounttype == 2) {
			file = new File(TEACHER);
		} else if (accounttype == 3) {
			file = new File(STUDENT);
		}
		System.out.println("Enter your username: ");
		username = in.nextLine();
		if(InputValidation(username,false)==true) {
			System.exit(0);
		}
		System.out.println("Enter your password: ");
		String password = in.nextLine();
		if(InputValidation(password,false)==true) {
			System.exit(0);
		}
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(file);

			boolean found = false;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] split= line.split(":");
				if (split[0].equals(username)) {
					found = true;
					String encryptedPassword = encryptPassword(password);
					if (encryptedPassword.equals(split[1])) {
						System.out.println("Login successful!");
						return success = true;
					} else {
						return success = false;
					}
				}
			}

			if (!found) {
				System.out.println("Username not found. Login failed.");
				return success = false;
			}

		} catch (IOException e) {
			System.out.println("An error occurred while reading the credentials file: " + e.getMessage());
		}
		return success;
	}

	protected static void register(boolean allowRegisteration) throws NoSuchAlgorithmException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		if (allowRegisteration == true) {
			System.out.println("Would you like to make an account for\n1. Admin\n2. Teacher\n3. Student");
			int choice = sc.nextInt();
			while (choice != 1 && choice != 2 && choice != 3) {
				System.out.println("Invalid Input, Please try again.");
				choice = sc.nextInt();
			}

			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			System.out.println("Enter a new username: ");
			String username = in.nextLine();

			try {
				File file = null;
				if (choice == 1) {
					file = new File(ADMIN);
				} else if (choice == 2) {
					file = new File(TEACHER);
				} else if (choice == 3) {
					file = new File(STUDENT);
				}

				@SuppressWarnings("resource")
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					String[] split= line.split(":");
					if (split[0].equals(username)) {
						System.out.println("This username is already taken. Registration failed.");
						return;
					}
				}
				System.out.println("Enter a new password: ");
				String password = in.nextLine();

				String encryptedPassword = encryptPassword(password);
				FileWriter writer = null;
				if (choice == 1) {
					writer = new FileWriter(ADMIN, true);
				} else if (choice == 2) {
					writer = new FileWriter(TEACHER, true);
				} else if (choice == 3) {
					writer = new FileWriter(STUDENT, true);
				}

				writer.write(username + ":" + encryptedPassword + "\n");
				writer.close();
				System.out.println("Registration successful!");
			} catch (IOException e) {
				System.out.println("An error occurred while writing to the credentials file: " + e.getMessage());
			}
		} else {
			System.out.println("You are not allowed to register.");
		}
	}
}