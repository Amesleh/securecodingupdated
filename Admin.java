package securecoding;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Admin extends LoginSystem{
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome admin, please choose what you want to do:\n1.Create new user\n2.Create new questions\n3.List the exams\n4.Take an exam\n5.Print the marks");
		int choice = sc.nextInt();
		while(choice!=1 && choice!=2  && choice!= 3 && choice!=4 && choice!= 5) {
			System.out.println("Invalid choice, please try again...");
			choice = sc.nextInt();
		}
		if(choice==1) {
			register(true);
		}else if(choice==2) {
			CreateExam();
		}else if(choice==3) {
			PrintExam(true);
		}else if(choice==4) {
			Exams.main(args);
		}else if(choice==5) {
			PrintMarks(true);
		}else {
			System.out.println("Invalid option, please try again...");
		}
		sc.close();
	}
}
