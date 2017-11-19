package cab.invoice.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.*;

public class InvoiceGenerator {
	DatabaseConnection db = new DatabaseConnection();
	//Function To Generate AlphaNumeric Random Strings that will be used as unique Invoice ID.
	public String invoiceIDGenerator(){
		long decimalNumber=System.nanoTime();
		String strBaseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String invoiceID = "";
		int mod = 0;
		while(decimalNumber!= 0){
            mod=(int) (decimalNumber % 36);
            invoiceID=strBaseDigits.substring(mod,mod+1)+invoiceID;
            decimalNumber=decimalNumber/36;
        }
		return invoiceID;

	}
	
	public void writeToFile(String invID, String carNumber, String carModel, String packName){
		String separator = System.getProperty("line.separator");
		//variable declarations for storing the data from the database.
		String bookedBy = new String();
		String usedBy = new String();
		String branch = new String();
		int dd = 0, mm = 0, yyyy = 0, usedKms = 0, usedHours = 0, packKms = 0, packHours = 0;
		Double eKmsCharge = 0.0;
		Double eHoursCharge = 0.0;
		Double osaDays = 0.0;
		Double osaNights = 0.0;
		Double parking = 0.0;
		Double others = 0.0;
		Double subTotalA = 0.0;
		Double subTotalB = 0.0;
		Double serviceTax = 0.0;
		Double bookingAmount = 0.0;
		Double finalAmount = 0.0;
		
		String compName = new String();
		String compAddr = new String();
		String compPAN = new String();
		String compDD = new String();
		
		
		try{
			Connection con = db.createConnection();
			Connection con2 = db.createConnection();
			String retrieveStatement = "select * from invoices where invoiceno=?";
			String retrieveCompanyInfo = "select * from company";
			PreparedStatement ps = con.prepareStatement(retrieveStatement);
			Statement st = con2.createStatement();
			ps.setString(1, invID);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = st.executeQuery(retrieveCompanyInfo);
			while(rs.next()){
				bookedBy = rs.getString(2);
				usedBy = rs.getString(3);
				branch = rs.getString(4);
				dd = rs.getInt(5);
				mm = rs.getInt(6);
				yyyy = rs.getInt(7);
				usedKms = rs.getInt(8);
				usedHours = rs.getInt(9);
				packKms = rs.getInt(10);
				packHours = rs.getInt(11);
				eKmsCharge = rs.getDouble(13);
				eHoursCharge = rs.getDouble(14);
				osaDays = rs.getDouble(15);
				osaNights = rs.getDouble(16);
				parking = rs.getDouble(17);
				others = rs.getDouble(18);
				subTotalA = rs.getDouble(19);
				subTotalB = rs.getDouble(20);
				serviceTax = rs.getDouble(21);
				bookingAmount = rs.getDouble(22);
				finalAmount = rs.getDouble(23);
			}
			while(rs2.next()){
				compName = rs2.getString(1);
				compAddr = rs2.getString(2);
				compPAN = rs2.getString(3);
				compDD = rs2.getString(4);
			}
			
			
			
			File file = new File("temp.txt");
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(separator+" Invoice ID: "+invID);
			writer.write(separator+" Company Name: "+compName);
			writer.write(separator+" Used By:   "+usedBy+separator+" Booked By: "+bookedBy+"                        Branch Requested: "+branch);
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+" Car No.: "+carNumber+"\t\tCar Alotted: "+carModel);
			writer.write(separator+" Package: "+packName);
			writer.write(separator+" Kms Used: "+usedKms+"                Extra Kms: "+(usedKms-packKms)+"             Extra Kms Charge(@10% extra rate): "+eKmsCharge);
			writer.write(separator+" Hours Used: "+usedHours+"                Extra Hours: "+(usedHours-packHours)+"          Extra Hours Charge(@Rs.50/hr): "+eHoursCharge);
			writer.write(separator+" Car Used On: "+dd+"-"+mm+"-"+yyyy+"                                                                    Sub Total (A): "+subTotalA);
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+" Parking & Toll: "+parking+"                                                    Others: "+others);
			writer.write(separator+" Out Station Allowance (in Rs.) for \t Days: "+osaDays+"                Nights: "+osaNights);
			writer.write(separator+"                                                                                                                      Sub Total (B): "+subTotalB);
			writer.write(separator+"------------------------------------------------------------------------------------------------------------------");
			writer.write(separator+"                                                                                                                      Total (A+B): "+(subTotalA+subTotalB));
			writer.write(separator+separator+" PAN: "+compPAN+"                                                                                 Service Tax: "+serviceTax);
			writer.write(separator+" Service Tax Category: Rent-a-Cab\t\tTotal: "+finalAmount);
			writer.write(separator+" All Cheques/Drafts to be drawn in favor of\t\tBooking Amount: "+bookingAmount+separator+" "+compDD+"\t\tNet Payable: "+(finalAmount-bookingAmount));
			writer.write(separator+separator+" Billing Address"+separator+" "+compAddr);
			
			writer.flush();
			con.close();
			con2.close();
			Invoice i = new Invoice();
			i.setVisible(true);
			
		}catch(Exception ee){
			ee.printStackTrace();
		}
		
				
	}	
}
