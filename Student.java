package securecoding;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Student extends LoginSystem{
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
			Scanner sc = new Scanner(System.in);
			System.out.println("Hello student! Please choose what you want to do.\n1.Take an exam\n2.View an exam");
			int choice = sc.nextInt();
			while(choice!=1 && choice!=2) {
				System.out.println("Invalid choice, please try again...");
			}
			if(choice==1) {
				Exams.main(args);
			}else {
				PrintExam(false);
		}
		
		sc.close();
	}

}
