/**
 * Program description: To construct a Java program based on the given class diagram related to the
                        employee salary data from an input file called "employeeSalaries.txt" and given question in Lab Test
 * Programmer: Qib
 * Date: 14 March 2024
 */

//import the respective packages
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

//driver class
public class DemoEmployeeSalaryProgram
{
    //Driver method
    public static void main(String[]args) throws IOException
    {
        //Instantiate the object of DecimalFormat
        DecimalFormat dF = new DecimalFormat("0.00");

        try
        {
            //Set the input/output file
            //Input file
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));

            //Output files
            PrintWriter outputFile = new PrintWriter(new FileWriter("employeeData.txt"));

            //Declare the variables
            String inputData = null;
            String employeeName = "";
            double employeeAnnualSalary = 0.00;
            int employeeWorkingYear = 0;
            double tax = 0.05;

            //Variables for top performing employee
            String top_employeeName = "";
            double top_employeeAnnualSalary = 0.00;
            int top_employeeWorkingYear = 0;
            
            //Variables for latest working employee
            String latest_employeeName = "";
            double latest_employeeAnnualSalary = 0.00;
            int latest_employeeWorkingYear = 0;
            
            //Write the title header
            outputFile.println("====================================================================================================");
            outputFile.println("List of Employee Data");
            outputFile.println("====================================================================================================");
            outputFile.println("Employee Name\t\tEmployee Salary(RM)\t\tEmployee Working Year");
            outputFile.println("====================================================================================================");
            
            
            while((inputData = inputFile.readLine()) != null)
            {
                //Instantiate the object reference of the StringTokenizer
                //class
                //to pass the string line (input data) & to set the delimeter
                StringTokenizer tokenizer = new StringTokenizer(inputData,"|");
                
                employeeName = tokenizer.nextToken();
                employeeAnnualSalary = Double.parseDouble(tokenizer.nextToken());
                employeeWorkingYear = Integer.parseInt(tokenizer.nextToken());
            
                double annualSalary = employeeAnnualSalary + (employeeAnnualSalary * tax);
                
                //To test for the negative number
                if(employeeAnnualSalary < 0 || employeeWorkingYear < 0)
                  throw new IllegalArgumentException();
                
                //Find the top performing employee
                if(annualSalary > top_employeeAnnualSalary){
                    top_employeeName = employeeName;
                    top_employeeAnnualSalary= annualSalary;
                    top_employeeWorkingYear = employeeWorkingYear;
                }
                
                //Find the least working years of employee
                if(latest_employeeWorkingYear == 0 || employeeWorkingYear < latest_employeeWorkingYear){
                    latest_employeeName = employeeName;
                    latest_employeeAnnualSalary = annualSalary;
                    latest_employeeWorkingYear = employeeWorkingYear;
                }
                
                
                //Store list of employees
                String employeeData=employeeName+"\t\t RM "+annualSalary+"\t\t"+employeeWorkingYear+" years";
                outputFile.println(employeeData);

            }
            
            //Top performing employee
            outputFile.println("\n\n -------- Top Performing Employee Details Based On The Salary -------");
            outputFile.println("=========================================================================");
            outputFile.println("Employee Name\tEmployee Salary(RM)\tEmployee Working Year");
            outputFile.println("==========================================================================");
            String top_employeeData=top_employeeName+"\t\t RM "+top_employeeAnnualSalary+"\t\t"+top_employeeWorkingYear+" years";
            outputFile.println(top_employeeData);
            outputFile.println("-------------------------------------------------------------------------");
            
            //Display top performing employee
            JOptionPane.showMessageDialog(null,
            "======================================================================"+
            "\nDetails of Top Performing Employee Based On The Salary"+
            "\n======================================================================"+
            "\nName of employee: "+top_employeeName+
            "\nAnnual Salary: RM"+top_employeeAnnualSalary+"\nWorking Years:"+top_employeeWorkingYear+" years"+
            "\n======================================================================");
            
            //Latest working years  employee
            outputFile.println("\n\n---------Details of Employee With The Least Years Of Service---------");
            outputFile.println("=========================================================================");
            outputFile.println("Employee Name\tEmployee Salary(RM)\tEmployee Working Year");
            outputFile.println("=========================================================================");
            String latest_employeeData=latest_employeeName+"\t\t RM "+latest_employeeAnnualSalary+"\t\t"+latest_employeeWorkingYear+" years";
            outputFile.println(latest_employeeData);
            outputFile.println("-------------------------------------------------------------------------");
            
            //Display working years employee
            JOptionPane.showMessageDialog(null,
            "======================================================================"+
            "\nDetails of Employee With The Least Years Of Service"+
            "\n======================================================================"+
            "\nName of employee: "+latest_employeeName+
            "\nAnnual Salary: RM"+latest_employeeAnnualSalary+
            "\nWorking Years: "+latest_employeeWorkingYear+" years"+
            "\n======================================================================");
            
            //Close the input/output file
            inputFile.close();
            outputFile.close();
        }//end of try block
        
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
        
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input! The number must be a positive number";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
    }
}