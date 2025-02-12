package com.cjc.main.sbi.serviceimp;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import org.hibernate.Session;
import com.cjc.main.sbi.service.RBI;
import com.cjc.main.sbi.config.*;
import com.cjc.main.sbi.exc.AadharNotValidException;
import com.cjc.main.sbi.exc.AccountNumberNotValidException;
import com.cjc.main.sbi.exc.AgeNotValidException;
import com.cjc.main.sbi.exc.AmountNotValidException;
import com.cjc.main.sbi.exc.MobileNumberNotValidException;
import com.cjc.main.sbi.exc.NameNotValidException;
import com.cjc.main.sbi.model.Account;

public class SBI implements RBI {
	
	public int agevalid(int age) throws AgeNotValidException
	{
		if(age<18)
		{
			throw new AgeNotValidException("Age not valid, should be 18+ .");
		}
		return age;
	}
	
	public int accnovalid(long acno) throws AccountNumberNotValidException
	{
		
		int size = 0;
		while(acno!=0)
		{
			acno = acno/10;
			size++;
		}
		
		if(size!=10) {
			throw new AccountNumberNotValidException("Account should have 10 digits");
		}
		return size;
	}
	
	public long mobnovalid(long mobno) throws MobileNumberNotValidException
	{
		int size = 0;
		while(mobno!=0)
		{
			mobno = mobno/10;
			size++;
		}
		
		if(size!=10) {
			throw new MobileNumberNotValidException("Mobile number should have 10 digits");
		}
		return size;
	}
	
	public long aadharnovalid(long adno) throws AadharNotValidException
	{
		int size = 0;
		while(adno!=0)
		{
			adno = adno/10;
			size++;
		}
		
		if(size!=12) {
			throw new AadharNotValidException("Aadhar number should have 10 digits");
		}
		return size;
	}
	
	public double amtzero(double amt) throws AmountNotValidException
	{
		if(amt==0 || amt<0) {
			throw new AmountNotValidException("Amount should be greater than 0");
		}
		return amt;
	}
	
	public String namevalid(String name) throws NameNotValidException
	{
		if(!name.matches("[a-zA-Z]+"))
		{
			throw new NameNotValidException("Only characters are allowed");
		}
		return name;
	}
	

	Scanner sc = new Scanner(System.in);
	
	@Override
	public void create_account() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Account account = new Account();
		
		
		
		try {
			System.out.println("Enter the 10 digit Account Number :- ");
			long account_no = sc.nextLong();
				if(accnovalid(account_no)==10)
				{
					account.setAccno(account_no);
				}
			}
			catch(AccountNumberNotValidException e)
			{
				System.out.println(e.getMessage()+"Please Enter the Account number of 10 digits only.");
				create_account();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please Enter the Account number in digits only.");
				create_account();
			}
		
		try
		{
			System.out.println("Enter the First Name :- ");
			String name = sc.next();
			if(namevalid(name).matches("[a-zA-Z]+"))
			{
				account.setName(name);
			}
		}
		catch(NameNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter the account details");
			create_account();
		}
		
		try
		{
			System.out.println("Enter the Middle Name :- ");
			String name = sc.next();
			if(namevalid(name).matches("[a-zA-Z]+"))
			{
				account.setMidname(name);
			}
		}
		catch(NameNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter the account details");
			create_account();
		}
		
		try
		{
			System.out.println("Enter the Last Name :- ");
			String name = sc.next();
			if(namevalid(name).matches("[a-zA-Z]+"))
			{
				account.setSurname(name);
			}
		}
		catch(NameNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter the account details");
			create_account();
		}
		
		System.out.println("Select the Gender :- \n 1. Male \n 2.Female ");
		int i = sc.nextInt();
		
		if(i==1 || i==2)
		{
		switch(i)
		{
		case 1:
			account.setGender("Male");
			break;
			
		case 2:
			account.setGender("Female");
		}
		}
		else
		{
			System.out.println("In this planet earth only 2 genders exist so choose wisely.");
			create_account();
		}
		
		
		
		try
		{
			System.out.println("Enter the Age :- ");
			int age = sc.nextInt();
			
			if(agevalid(age)>=18 || agevalid(age)<120)
			{
				account.setAge(age);
			}
		}
		catch(AgeNotValidException e)
		{
			System.out.println(e.getMessage()+"\nEnter the account details again... \\n");
			create_account();
			
		}
		catch(InputMismatchException e)
		{
			System.out.println("Please enter a valid Age");
			create_account();
		}
		
		
		
		try {
			System.out.println("Enter the Contact Number :- ");
			long conno = sc.nextLong();
			if(mobnovalid(conno)==10)
			{
				account.setMobno(conno);
			}
		}
		catch(MobileNumberNotValidException e) {
			System.out.println(e.getMessage()+"\nPlease enter the mobile number correctly...");
			create_account();
		}
		
		try
		{
			System.out.println("Enter the Aadhar Number :- ");
			long adno = sc.nextLong();
			if(aadharnovalid(adno)==12)
			{
				account.setAadharno(adno);
			}
		}
		catch(AadharNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter 12 digits of Aadhar Number");
			create_account();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Please enter a valid Aadhar Number");
			create_account();
		}
		
		System.out.println("Enter the Permanent Address :- ");
		account.setAddress(sc.next()+sc.nextLine());
		
		
		
		try
		{
			System.out.println("Enter the Account Balance :- ");
			double bal = sc.nextDouble();
			if(amtzero(bal)>0)
			{
				account.setBalance(bal);
			}
		}
		catch(AmountNotValidException e)
		{
			System.out.println(e.getMessage()+"\nFirst deposit should be greater than 0");
			create_account();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Please enter a valid amount.");
			create_account();
		}
		
		session.persist(account);
		session.beginTransaction().commit();
		
	}

	@Override
	public void view_account() {
		// TODO Auto-generated method stub
		
		Session session = new HibernateUtil().getSessionFactory().openSession();
		
		
		
		try {
			
			System.out.println("Enter the Account Number :- ");
			long acno = sc.nextLong();
			
			if(accnovalid(acno)==10)
			{
				
				Account account = session.get(Account.class, acno);
				System.out.println(account.getAccno());
				System.out.println(account.getName()+" "+account.getMidname()+" "+account.getSurname());
				System.out.println(account.getGender());
				System.out.println(account.getAge());
				System.out.println(account.getMobno());
				System.out.println(account.getAadharno());
				System.out.println(account.getAddress());
				System.out.println(account.getBalance());
			}
		}
		catch(AccountNumberNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter a valid 10 digit account number");
			view_account();
		}
		catch(NullPointerException e)
		{
			System.out.println("No Account exist with the given account number. \nRe-Enter the account number");
			view_account();
		}
		
	}

	@Override
	public void update_account() {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			
			System.out.println("Enter the Account Number :- ");
			long acno = sc.nextLong();
			
			if(accnovalid(acno)==10)
			{
				Account account = session.get(Account.class, acno);
				
				System.out.println("Select a option from below to update :- \n1. Update Name \n2. Update Age \n3. Update Contact Number \n4. Update Aadhar Number \n4. Update Address");
				
				int i = sc.nextInt();
				
				switch(i)
				{
				
				case 1:
					System.out.println("Enter the Name to be updated :- ");
					System.out.println("First Name :- ");
					account.setName(sc.next());
					
					System.out.println("Middle Name :- ");
					account.setMidname(sc.next());
					
					System.out.println("Last Name :- ");
					account.setSurname(sc.next());
					
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				case 2:
					System.out.println("Enter the Age to be updated :- ");
					account.setAge(sc.nextInt());
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				case 3:
					System.out.println("Enter the Contact Number to be updated :- ");
					account.setMobno(sc.nextLong());
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				case 4:
					System.out.println("Enter the Aadhar Number to be updated :- ");
					account.setAadharno(sc.nextLong());
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				case 5:
					System.out.println("Enter the Permanent address to be updated :- ");
					account.setAddress(sc.next()+sc.nextLine());
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				default:
					System.out.println("Enter the correct selection.");
					update_account();
					break;
				}
			}
		}
		catch(AccountNumberNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter a valid 10 digit account number");
			update_account();
		}
		catch(NullPointerException e)
		{
			System.out.println("No Account exist with the given account number. \nRe-Enter the account number");
			update_account();
		}
		
	}

	@Override
	public void transaction() {
		// TODO Auto-generated method stub
		
		System.out.println("Select which type of transaction you want to do :- \n1. Withdraw Money \n2. Deposit Money \n3. Cancel transaction");
		int i = sc.nextInt();
		
		try {
			
			System.out.println("Enter the Account Number :- ");
			long acno = sc.nextLong();
			
			if(accnovalid(acno)==10)
			{
				Session session = HibernateUtil.getSessionFactory().openSession();
				Account account = session.get(Account.class, acno);
				
				switch(i)
				{
				case 1:
					System.out.println("Enter the amount to withdraw :- ");
					double wdt_bal = sc.nextDouble();
					
					try
					{
						amtzero(wdt_bal);
					}
					catch(AmountNotValidException e)
					{
						System.out.println(e.getMessage());
						transaction();
					}
					account.setBalance(account.getBalance()-wdt_bal);
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				case 2:
					System.out.println("Enter the amount to deposit :- ");
					double dep_bal = sc.nextDouble();
					
					try
					{
						amtzero(dep_bal);
					}
					catch(AmountNotValidException e)
					{
						System.out.println(e.getMessage());
						create_account();
					}
					account.setBalance(account.getBalance()+dep_bal);
					session.merge(account);
					session.beginTransaction().commit();
					break;
					
				case 3:
					System.exit(0);
					
				}
			}
		}
		catch(AccountNumberNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter a valid 10 digit account number");
			view_account();
		}
		catch(NullPointerException e)
		{
			System.out.println("No Account exist with the given account number. \nRe-Enter the account number");
			view_account();
		}
		
		
		
	}

	@Override
	public void delete_account() {
		// TODO Auto-generated method stub
		
		try {
			
			System.out.println("Enter the Account Number :- ");
			long acno = sc.nextLong();
			
			if(accnovalid(acno)==10)
			{
				Session session = HibernateUtil.getSessionFactory().openSession();
				Account account = session.get(Account.class, acno);
				
				session.remove(account);
				session.beginTransaction().commit();
				
				System.out.println("\n Account has been successfully deleted.");
			}
		}
		catch(AccountNumberNotValidException e)
		{
			System.out.println(e.getMessage()+"Please enter a valid 10 digit account number");
			view_account();
		}
		catch(NullPointerException e)
		{
			System.out.println("No Account exist with the given account number. \nRe-Enter the account number");
			view_account();
		}
		
	}

}
