package securecoding;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Teachers extends LoginSystem {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome teacher, please choose what you want to do.\n1.View the exams\n2.View the marks\n3.Create a new exam");
		int choice = sc.nextInt();
		
		while(choice!=1 && choice!=2  && choice!= 3) {
			System.out.println("Invalid choice, please try again...");
			choice = sc.nextInt();
		}
		
		if(choice==1) {
			PrintExam(true);
		}else if(choice==2) {
			PrintMarks(true);
		}else if(choice==3) {
			CreateExam();
		}
		
		sc.close();
	}

}
