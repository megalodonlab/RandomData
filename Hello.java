import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hello {

    static String FILENAME_WRITE_DATA = "randomData.txt";
    static String[] COUNTRIES = {"Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla","Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon","Cape Verde","Cayman Islands","Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica","Cote D Ivoire","Croatia","Cruise Ship","Cuba","Cyprus","Czech Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Estonia","Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia","French West Indies","Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam","Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kuwait","Kyrgyz Republic","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Mauritania","Mauritius","Mexico","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Namibia","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Norway","Oman","Pakistan","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Pierre &amp; Miquelon","Samoa","San Marino","Satellite","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","South Africa","South Korea","Spain","Sri Lanka","St Kitts &amp; Nevis","St Lucia","St Vincent","St. Lucia","Sudan","Suriname","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor L'Este","Togo","Tonga","Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan","Turks &amp; Caicos","Uganda","Ukraine","United Arab Emirates","United Kingdom","Uruguay","Uzbekistan","Venezuela","Vietnam","Virgin Islands (US)","Yemen","Zambia","Zimbabwe"};
    static char[] ALPHABETS   = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    static ArrayList<String> PRODUCTS_DESCRIPTIONS = new ArrayList<String>();

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String generateCountry() {
        int index = getRandomNumber(0, COUNTRIES.length);
        return COUNTRIES[index];
    }

    public static int generateInvoiceNo(){
        return getRandomNumber(100000, 999999);
    }

    public static int generateCustomerID(){
        return getRandomNumber(0, 99999);
    }

    public static String generateStockCode() {
        int index = getRandomNumber(0, ALPHABETS.length);
        return String.valueOf( getRandomNumber(10000, 99999) ) + ALPHABETS[index];
    }

    public static int generateQuantity() {
        return getRandomNumber(1, 100);
    }

    public static String generateUnitPrice() {

        int wholePrice = getRandomNumber(1, 100);
        int cent    = getRandomNumber(0, 99);

        return wholePrice + "." + cent;
    }

    public static String generateInvoiceDate() {
        String month = String.valueOf( getRandomNumber(1, 12) );
        String date = String.valueOf( getRandomNumber(1, 31) );
        String year = String.valueOf( getRandomNumber(2010, 2022) );

        String hours = String.valueOf( getRandomNumber(0, 23) );
        String mins  = String.valueOf( getRandomNumber(0, 59) );

        return month + "/" + date + "/" + year + " " + hours + ":" + mins;
    }

    // For loading descriptions
    public static boolean loadData() {

        String fileName = "data.txt";

        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                PRODUCTS_DESCRIPTIONS.add(data);
            }

            myReader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public static String generateDescription() {
        int index = getRandomNumber( 0, PRODUCTS_DESCRIPTIONS.size() );
        return PRODUCTS_DESCRIPTIONS.get(index);
    }

    public static String generateEntry() {
        Scanner sc = new Scanner(System.in);

        //InvoiceNo,StockCode,Description,Quantity,InvoiceDate,UnitPrice,CustomerID,Country
        return    generateInvoiceNo() + "," + generateStockCode() + ","
                + generateDescription() + "," + generateQuantity() + ","
                + generateInvoiceDate() + "," + generateUnitPrice() + ","
                + generateCustomerID() + "," + generateCountry();
    }

    public static void createDataFile() {
        try {

            File myObj = new File(FILENAME_WRITE_DATA);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file.");
            e.printStackTrace();
        }
    }

    public static void writeDataToFile(int num) {
        try {
            createDataFile();
            FileWriter myWriter = new FileWriter(FILENAME_WRITE_DATA, true);

            for (int i = 0; i < num; i++) {
                myWriter.write( generateEntry() + '\n' );
            }
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the data entries to be generated.");
        int num = sc.nextInt();
        loadData();
        writeDataToFile(num);
    }
}

//Description

//InvoiceNo,StockCode,Description,Quantity,InvoiceDate,UnitPrice,CustomerID,Country
//536365,85123A,WHITE HANGING HEART T-LIGHT HOLDER,6,12/1/10 8:26,2.55,17850,United Kingdom
//536365,71053,WHITE METAL LANTERN,6,12/1/10 8:26,3.39,17850,United Kingdom
//536365,84406B,CREAM CUPID HEARTS COAT HANGER,8,12/1/10 8:26,2.75,17850,United Kingdom
//536365,84029G,KNITTED UNION FLAG HOT WATER BOTTLE,6,12/1/10 8:26,3.39,17850,United Kingdom
//536365,84029E,RED WOOLLY HOTTIE WHITE HEART.,6,12/1/10 8:26,3.39,17850,United Kingdom
//536365,22752,SET 7 BABUSHKA NESTING BOXES,2,12/1/10 8:26,7.65,17850,United Kingdom
//536365,21730,GLASS STAR FROSTED T-LIGHT HOLDER,6,12/1/10 8:26,4.25,17850,United Kingdom
//536366,22633,HAND WARMER UNION JACK,6,12/1/10 8:28,1.85,17850,United Kingdom
//536366,22632,HAND WARMER RED POLKA DOT,6,12/1/10 8:28,1.85,17850,United Kingdom
//536367,84879,ASSORTED COLOUR BIRD ORNAMENT,32,12/1/10 8:34,1.69,13047,United Kingdom



// MM -> 1 - 12
// DD -> 1 - 31
// YYYY -> 2010 - 2022
// hh -> 00 - 23
// mm -> 00 - 59

// MM/DD/YYYY        hh:mm
// 12/1/10           08:26