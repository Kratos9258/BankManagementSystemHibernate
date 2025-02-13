package com.cjc.main.sbi.client;

import java.util.Scanner;

import com.cjc.main.sbi.service.RBI;
import com.cjc.main.sbi.serviceimp.SBI;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
		
		System.out.println("Please select a operation from below :- ");
		
		System.out.println("1. Create a new account. \n2. View an account's detail. \n3. Update an account's detail. \n4. Make transaction in an account. \n5. Delete an account PERMANENTLY. \n6. Exit the program. ");
		
		int i = sc.nextInt();
		
		RBI sbi = new SBI();
		
		
		switch(i)
		{
		case 1:
			sbi.create_account();
			break;
			
		case 2:
			sbi.view_account();
			break;
			
		case 3:
			sbi.update_account();
			break;
			
		case 4:
			sbi.transaction();
			break;
			
		case 5:
			sbi.check_balance();
			break;
			
		case 6:
			sbi.delete_account();
			break;
			
		case 7:
			System.exit(0);
			
		}

		}
	}

}
