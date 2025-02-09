package com.cjc.main.sbi.serviceimp;

import java.util.Scanner;

import org.hibernate.Session;

import com.cjc.main.sbi.service.RBI;
import com.cjc.main.sbi.config.*;
import com.cjc.main.sbi.exc.AccountNumberNotValidException;
import com.cjc.main.sbi.exc.AgeNotValidException;
import com.cjc.main.sbi.exc.AmountNotValidException;
import com.cjc.main.sbi.exc.MobileNumberNotValidException;
import com.cjc.main.sbi.model.Account;

public class SBI implements RBI {
	
	public void agevalid(int age) throws AgeNotValidException
	{
		if(age<18)
		{
			throw new AgeNotValidException("Age not valid, should be 18+ .");
		}
	}
	
	public void accnovalid(long acno) throws AccountNumberNotValidException
	{
		int size = 0;
		while(acno!=0)
		{
			acno = acno/10;
			size++;
		}
		
		if(size<10 || size>10) {
			throw new AccountNumberNotValidException("Account should have 10 digits");
		}
	}
	
	public void mobnovalid(long mobno) throws MobileNumberNotValidException
	{
		int size = 0;
		while(mobno!=0)
		{
			mobno = mobno/10;
			size++;
		}
		
		if(size<10 || size>10) {
			throw new MobileNumberNotValidException("Mobile number should have 10 digits");
		}
	}
	
	public void amtzero(double amt) throws AmountNotValidException
	{
		if(amt==0 || amt<0) {
			throw new AmountNotValidException("Amount should be greater than 0");
		}
	}

	Scanner sc = new Scanner(System.in);
	
	@Override
	public void create_account() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Account account = new Account();
		
		System.out.println("Enter the 10 digit Account Number :- ");
		long account_no = sc.nextLong();
		
		try
		{
			accnovalid(account_no);
		}
		catch(AccountNumberNotValidException e)
		{
			System.out.println(e.getMessage()+"\nPlease enter the details correctly");
			create_account();
		}
		account.setAccno(account_no);
		
		System.out.println("Enter the First Name :- ");
		account.setName(sc.next());
		
		System.out.println("Enter the Middle Name :- ");
		account.setMidname(sc.next());
		
		System.out.println("Enter the Last Name :- ");
		account.setSurname(sc.next());
		
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
		
		System.out.println("Enter the Age :- ");
		int age = sc.nextInt();
		try
		{
			agevalid(age);
		}
		catch(AgeNotValidException e)
		{
			System.out.println(e.getMessage()+"\n");
			create_account();
			System.out.println("Enter the account details again... \n");
		}
		account.setAge(age);

		
		System.out.println("Enter the Contact Number :- ");
		long conno = sc.nextLong();
		
		try {
			mobnovalid(conno);
		}
		catch(MobileNumberNotValidException e) {
			System.out.println(e.getMessage()+"\nPlease enter the mobile number correctly...");
			create_account();
		}
		account.setMobno(conno);
		
		System.out.println("Enter the Aadhar Number :- ");
		account.setAadharno(sc.nextLong());
		
		System.out.println("Enter the Permanent Address :- ");
		account.setAddress(sc.next()+sc.nextLine());
		
		System.out.println("Enter the Account Balance :- ");
		double bal = sc.nextDouble();
		
		try
		{
			amtzero(bal);
		}
		catch(AmountNotValidException e)
		{
			System.out.println(e.getMessage()+"\nFirst deposit should be greater than 0");
			create_account();
		}
		account.setBalance(bal);
		
		session.persist(account);
		session.beginTransaction().commit();
		
	}

	@Override
	public void view_account() {
		// TODO Auto-generated method stub
		
		Session session = new HibernateUtil().getSessionFactory().openSession();
		
		System.out.println("Enter the Account Number :- ");
		long acno = sc.nextLong();
		
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

	@Override
	public void update_account() {
		// TODO Auto-generated method stub
		
		Session session = new HibernateUtil().getSessionFactory().openSession();
		
		System.out.println("Enter the Account Number :- ");
		long acno = sc.nextLong();
		
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

	@Override
	public void transaction() {
		// TODO Auto-generated method stub
		
		System.out.println("Select which type of transaction you want to do :- \n1. Withdraw Money \n2. Deposit Money \n3. Cancel transaction");
		int i = sc.nextInt();
		
		System.out.println("Enter the Account Number :- ");
		long acno = sc.nextLong();
		
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

	@Override
	public void delete_account() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the Account Number :- ");
		long acno = sc.nextLong();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Account account = session.get(Account.class, acno);
		
		session.remove(account);
		session.beginTransaction().commit();
		
		System.out.println("\n Account has been successfully deleted.");
		
	}

}
