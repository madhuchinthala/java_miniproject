import java.util.Scanner;
public class SimpleDateCalculator {
    public static boolean validity(int day,int month,int year){
        switch (month) {
            case 1,3,5,7,8,10,12:
                if(day<1 || day>31 || year<=0){
                    return false;
                }
                break;
            case 2:
                if(leapYear(year)){
                    if(day<1 || day>29 || year<=0){
                        return false;
                    }else if(day<1 || day>28 || year<=0){
                        return false;
                    }
                }
                break;
            case 4,6,9,11:
                if(day<1 || day>30 || year<=0){
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }
    public static boolean leapYear(int year){
        if(year%4==0){
            if(year%100!=0 || year%400==0){
                return true;
            }
        }
        return false;
    }
    // Method to calculate the age based on DOB and reference date
    public static void calculateAge(int dobDay, int dobMonth, int dobYear, int refDay, int refMonth, int refYear) {
        int years = refYear - dobYear;
        int months = refMonth - dobMonth;
        int days = refDay - dobDay;
        // Adjust the months and days if needed
        if (days < 0) {
            months--;
            days += 30; // Approximate days in a month
        }
        if (months < 0) {
            years--;
            months += 12;
        }
        // Print the result
        System.out.println("Age is: " + years + " years, " + months + " months, " + days + " days.");
    }
    // Method to calculate the DOB based on age and reference date
    public static void calculateDOB(int ageDay,int ageMonth,int ageYear, int refDay, int refMonth, int refYear) {
        int dobYear = refYear - ageYear;
        int dobMonth = refMonth-ageMonth;
        int dobDay = refDay-ageDay+1;
        if (dobDay < 0) {
            dobMonth--;
            dobDay += 30; // Approximate days in a month
        }
        if (dobMonth < 0) {
            dobYear--;
            dobMonth += 12;
        }
        System.out.println(dobDay);
        System.out.println(leapYear(dobYear));
        //yeap year condition
        if(dobDay == 29 && dobMonth == 2 && !(leapYear(dobYear))){
            dobDay=1;
            dobMonth=3;

        }
        // Print the DOB
        System.out.println("Date of Birth is: " + dobDay + "-" + dobMonth + "-" + dobYear);
    }
    // Method to parse the date based on the format
    public static int[] parseDate(String dateStr, String format, String delimiter) {
        String[] dateParts = dateStr.split(delimiter);
        int day = 0, month = 0, year = 0;
        if (format.equals("DDdlcMMdlcYYYY")) {
            day = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        } else if (format.equals("YYYYdlcMMdlcDD")) {
            year = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            day = Integer.parseInt(dateParts[2]);
        } else if (format.equals("MMdlcDDdlcYYYY")) {
            month = Integer.parseInt(dateParts[0]);
            day = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        }
        return new int[]{day, month, year};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Input 1: either DOB or AGE
        System.out.println("Enter the input (e.g., DOB=27-02-2001 or AGE=22): ");
        String input = sc.nextLine();
        // Input 2: Reference Date or Current Date
        System.out.println("Enter the reference date or current date (e.g., 27-02-2024): ");
        String refDate = sc.nextLine();
        // Input 3: Date format (DDdlcMMdlcYYYY, YYYYdlcMMdlcDD, MMdlcDDdlcYYYY)
        System.out.println("Enter the date format (e.g., DDdlcMMdlcYYYY, YYYYdlcMMdlcDD, MMdlcDDdlcYYYY): ");
        String format = sc.nextLine();
        // Input 4: Delimiter
        System.out.println("Enter the delimiter (e.g., -, /, .): ");
        String delimiter = sc.nextLine();
        // Parse the reference date
        int[] refDateParts = parseDate(refDate, format, delimiter);
        int refDay = refDateParts[0];
        int refMonth = refDateParts[1];
        int refYear = refDateParts[2];
        if(!validity(refDay, refMonth, refYear)){
            System.out.println("Reference date is invalid.Please Enter valid details");
            sc.close();
            return;
        }
        if (input.startsWith("DOB")) {
            // If the input is DOB, calculate the age
            String dobStr = input.split("=")[1];
            int[] dobParts = parseDate(dobStr, format, delimiter);
            int dobDay = dobParts[0];
            int dobMonth = dobParts[1];
            int dobYear = dobParts[2];
            if(!validity(dobDay, dobMonth, dobYear)){
                System.out.println("DOB is invalid.Please Enter valid details");
                sc.close();
                return;
            }
            calculateAge(dobDay, dobMonth, dobYear, refDay, refMonth, refYear);
        } else if (input.startsWith("AGE")) {
            // If the input is AGE, calculate the DOB
            String agestr = input.split("=")[1];
            int[] ageParts = parseDate(agestr, format, delimiter);
            int ageDay = ageParts[0];
            int ageMonth = ageParts[1];
            int ageYear = ageParts[2];
            calculateDOB(ageDay,ageMonth,ageYear, refDay, refMonth, refYear);
            
        } else {
            System.out.println("Invalid input. Please provide either DOB or AGE.");
        }
        sc.close();
    }
}