//********************************************************************
//
//  Developer:            Instructor
//
//  Program #:            Twelve
//
//  File Name:            Employee.java
//
//  Course:               ITSE 2321 Objected-Oriented Programming (Java)
//
//  Instructor:           Fred Kumi 
//
//  Description:
//     Employee class.  Please do NOT modify this Class.  If you
//     do, you will not receive credit for Program 12.
//
//********************************************************************

import java.util.Scanner;

/**
 * The Class Employee.
 */
public class Employee extends Object
{
   /** The first name. */
   private String firstName;
   
   /** The last name. */
   private String lastName;
   
   /** The pay rate. */
   private double payRate;

   /**
    * Instantiates a new employee.
    */
   public Employee()
   {
	  this.firstName = "";
	  this.lastName = "";
	  this.payRate = 0.0;
   }
   
   /**
    * Instantiates a new employee.
    *
    * @param firstName the first name
    * @param lastName the last name
    * @param payRate the pay rate
    */
   public Employee(String firstName, String lastName, double payRate)
   {
      if (payRate < 0.0) 
         throw new IllegalArgumentException("Pay rate must be > 0.0");
      else if (payRate < 7.25)
	      payRate = 7.25;
	  
      this.firstName = firstName;
      this.lastName = lastName;
      this.payRate = payRate;
   } // end constructor

   /**
    * Sets the first name.
    *
    * @param firstName the new first name
    */
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   /**
    * Sets the last name.
    *
    * @param lastName the last name
    * @return the last name
    */
    public void setLastName(String lastName)
   {
      this.lastName = lastName;
   } 

   /**
    * Gets the first name.
    *
    * @return the first name
    */
   public String getFirstName()
   {
      return firstName;
   }

   /**
    * Gets the last name.
    *
    * @return the last name
    */
   public String getLastName()
   {
      return lastName;
   } 

   /**
    * Sets the pay rate.
    *
    * @param payRate the new pay rate
    */
   public void setPayRate(double payRate)
   {
      if (payRate < 0.0) 
         throw new IllegalArgumentException("Pay rate must be >= 0.0");
      else if (payRate < 7.25)
	     this.payRate = 7.25;
      else
		 this.payRate = payRate;
   } 

   /**
    * Gets the pay rate.
    *
    * @return the pay rate
    */
   public double getPayRate()
   {
      return payRate;
   } 

   /**
    *  
    * Returns the String representation of an Employee object.
    *
    * @return the string
    */         
   @Override // indicates that this method overrides a superclass method
   public String toString()                                             
   {                                                                    
      return String.format("%s: %s %s%n%s: %.2f",
         "Employee", firstName, lastName,
         "Pay rate", payRate);
   }


} // End class Employee

class HourlyEmployee extends Employee {
   private double totalHours;
   private double totalReg;
   private double totalOver;

   public double getTotalReg() {
      return totalReg;
   }

   public double getTotalOver() {
      return totalOver;
   }

   public double getHourlyTotalReg() {
      return totalReg;
   }

   public double getHourlyTotalOver() {
      return totalOver;
   }

   //***************************************************************
   //
   //  Method:       setTotalHoursWorked
   //
   //  Description:  Takes in weekly input and calculates regular and overtime hours
   //
   //  Parameters:   double
   //
   //  Returns:      N/A
   //
   //**************************************************************

   public void setTotalHoursWorked(double hoursWorked) {
      if (hoursWorked < 0) {
         throw new IllegalArgumentException("Hours worked cannot be less than zero.");
      }
      else if (hoursWorked > 40) {
         totalReg += 40;
         totalOver += hoursWorked - 40;
      }
      else {
         totalReg += hoursWorked;
      }
      totalHours += hoursWorked;
   }

   //***************************************************************
   //
   //  Method:       HourlyEmployee
   //
   //  Description:  Constructor for hourly employees
   //
   //  Parameters:   String, String, double
   //
   //  Returns:      N/A
   //
   //**************************************************************

   public HourlyEmployee(String firstName, String lastName, double payRate) {
      if (payRate < 0.0) {
         throw new IllegalArgumentException("Pay rate must be > 0.0");
      }
      else if (payRate < 7.25) {
         payRate = 7.25;
      }
      setPayRate(payRate);
      setFirstName(firstName);
      setLastName(lastName);
   }

   public double getRegularPay() {
      return totalReg * getPayRate();
   }

   public double getOvertimePay() {
      return totalOver * (getPayRate() * 1.5);
   }

   //***************************************************************
   //
   //  Method:       getTax
   //
   //  Description:  Calculates tax based upon employee pay
   //
   //  Parameters:   None
   //
   //  Returns:      N/A
   //
   //**************************************************************

   public double getTax() {
      double tax;
      if (getRegularPay() + getOvertimePay() > 15000) {
         tax = (getRegularPay() + getOvertimePay()) * 0.36;
      }
      else if (getRegularPay() + getOvertimePay() > 10000) {
         tax = (getRegularPay() + getOvertimePay()) * 0.31;
      }
      else if (getRegularPay() + getOvertimePay() > 8000) {
         tax = (getRegularPay() + getOvertimePay()) * 0.28;
      }
      else if (getRegularPay() + getOvertimePay() > 4500) {
         tax = (getRegularPay() + getOvertimePay()) * 0.22;
      }
      else if (getRegularPay() + getOvertimePay() > 2500) {
         tax = (getRegularPay() + getOvertimePay()) * 0.15;
      }
      else {
         tax = (getRegularPay() + getOvertimePay()) * 0.11;
      }
      return tax;
   }

   //***************************************************************
   //
   //  Method:       to_String
   //
   //  Description:  Calls super.toString then adds hourly employee data
   //
   //  Parameters:   None
   //
   //  Returns:      N/A
   //
   //**************************************************************

   public String toString() {
      return String.format("%n%s%n%n" +
                           "%-19s%10.2f%n%-19s%10.2f%n%-19s%10.2f%n%n" +
                           "%-19s%10.2f%n%-19s%10.2f%n%-19s%10.2f%n%-19s%10.2f%n%-19s%10.2f%n",
                           super.toString(),
                           "Regular Hours: ", totalReg,
                           "Overtime Hours", totalOver,
                           "Total Hours", totalHours,

                           "Regular Pay: ", totalReg * getPayRate(),
                           "Overtime amount: ", totalOver * (getPayRate()*1.5),
                           "Monthly Gross Pay: ", (getRegularPay() + getOvertimePay()),
                           "Taxes: ", getTax(),
                           "Monthly net pay: ",(getRegularPay() + getOvertimePay()) - getTax());
   }

}


class HourlyEmployeeTest {

   private static final Scanner input = new Scanner(System.in);

   //***************************************************************
   //
   //  Method:       getEmployeeInput
   //
   //  Description:  Gathers input from user about hourly employee
   //
   //  Parameters:   None
   //
   //  Returns:      N/A
   //
   //**************************************************************

   public void getEmployeeInput() throws ArrayIndexOutOfBoundsException {
      String answer;
      HourlyEmployee employee;
      try {
         do {
            System.out.println("Enter your name (First Last): ");
            String[] fullName = (input.nextLine().split(" "));
            String first = fullName[0];
            String last = fullName[1];
            System.out.println("Enter your hourly rate: ");
            double payRate = input.nextDouble();
            employee = new HourlyEmployee(first, last, payRate);
            for (int i = 1; i <= 4; i++) {
               double hoursWorked;
               System.out.printf("%s%d%s", "Enter hours worked for week ", i, ": ");
               hoursWorked = input.nextDouble();
               employee.setTotalHoursWorked(hoursWorked);
            }
            System.out.print(employee);
            System.out.print("\n\nDo you want to run the program again <yes/no>? ");
            answer = input.next();
            input.nextLine();
         }
         while (answer.equals("yes"));
         if (answer.equals("no")) {
            System.out.println("Thanks for using this program.");
         }
      } catch (Exception e) {
         System.out.println("Must use a space between names. (First Last)");
         e.printStackTrace();
      }

   }

   public static void main(String[] args) {

      developerInfo();

      HourlyEmployeeTest test = new HourlyEmployeeTest();

      test.getEmployeeInput();

   }

   //***************************************************************
   //
   //  Method:       developerInfo
   //
   //  Description:  The developer information method of the program
   //
   //  Parameters:   None
   //
   //  Returns:      N/A
   //
   //**************************************************************

   public static void developerInfo() {
      System.out.printf("%s%n", "Name:    Joe Cooper");
      System.out.printf("%s%n", "Course:  ITSE 2321 Object-Oriented Programming");
      System.out.printf("%s%n%n", "Program: Twelve");
   } // end of developer info

}




