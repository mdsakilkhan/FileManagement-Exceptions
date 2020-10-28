import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A utility class for log entry container class
 *
 * @author Md Sakil Khan
 * @version 1.0
 */
public class LogUtility{
	
	/**
     * holds all the log entries of a log file
     */
	private final ArrayList<LogEntry> listLogEntries;
	
	/** 
	 * Default constructor that initializes the listLogEntries arraylist. 
	 */
	public LogUtility(){
		listLogEntries = new ArrayList<LogEntry>();
	}

    /**
     * Using the scanner class reads a log file and catogorize the data into an arraylist.
     * If file is not found throws FileNotFoundException.
	 * If data is not in the correct format skips line and does not put it in arraylist.
	 *
     * @param name of the file
     * @return true if the file is found and was able to extract the data
     */
	public boolean parseFile(String str)throws FileNotFoundException{
		Scanner fileInput = new Scanner(new FileInputStream(str));
		String line;
		while (fileInput.hasNext()) {
			line = fileInput.nextLine();
			String arr[] = line.split(",");
			try {
				LogEntry log = new LogEntry(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
				listLogEntries.add(log);
			}
			catch (Exception e1){
				System.out.printf("Skipping line: %s%n", line);
			}
		}
		fileInput.close();
		return true;
	}

    /**
     * Using the date class to determine if the log is between the two dates provided, 
	 * if data is in the range than it is added into an arraylist. 
	 *
     * @param starting date
     * @param last date
     * @return an arraylist that include entries between the two dates
     */
	public ArrayList<LogEntry> getBetween(String startStr, String endStr)throws ParseException{
		ArrayList<LogEntry> newlist = new ArrayList<LogEntry>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      	Date date1 = dateFormat.parse(startStr);
		Date date2 = dateFormat.parse(endStr);
		try{
			int i = 0;
			while(i < listLogEntries.size()) {
				LogEntry log = listLogEntries.get(i);
				Date temp = log.getDateTimeAsDate();
				i++;
				if (temp.compareTo(date1) >= 0 && temp.compareTo(date2) <= 0){
					newlist.add(log);
				}
			}
		}
		catch (Exception e){}
		return newlist;
	}

    /**
     * finds the log containing a given sequence
	 *
     * @param a given sequence to compare to
     * @return the log from the findFirst class
     */	
	public LogEntry findFirstId(String str){
		return findFirst(str, 1);
	}

    /**
     * finds the log containing a given date and time
	 *
     * @param a given date and time to compare to
     * @return the log from the findFirst class
     */
	public LogEntry findFirstTimestamp(String str){
		return findFirst(str, 2);
	}

    /**
     * finds the log containing a given source IP address
	 *
     * @param a given source IP address to compare to
     * @return the log from the findFirst class
     */
	public LogEntry findFirstSourceIP(String str){
		return findFirst(str, 3);
	}

    /**
     * finds the log containing a given destination IP address
	 *
     * @param a given destination IP address to compare to
     * @return the log from the findFirst class
     */	
	public LogEntry findFirstDestinationIP(String str){
		return findFirst(str, 4);
	}

    /**
     * finds the log containing a given protocol
	 *
     * @param a given protocol to compare to
     * @return the log from the findFirst class
     */	
	public LogEntry findFirstProtocol(String str){
		return findFirst(str, 5);
	}

    /**
     * finds the log containing a given packet size
	 *
     * @param a given packet size to compare to
     * @return the log from the findFirst class
     */
	public LogEntry findFirstLength(String str){
		return findFirst(str, 6);
	}

    /**
     * finds the log containing a given comment
	 *
     * @param a given comment to compare to
     * @return the log from the findFirst class
     */
	public LogEntry findFirstDiscription(String str){
		return findFirst(str, 7);
	}

    /**
     * Iterates through the listLogEntries arraylist to shearch for the given string
	 *
     * @param the given string 
     * @param a interger key that represent the logentry data-type
     * @return the log where the sting was found
     */	
	private LogEntry findFirst(String str, int key){
		int i = 0;
		while(i < listLogEntries.size()) {
			LogEntry log = listLogEntries.get(i);
			String s = null;
			switch (key) {
				case 1: s = log.getSequence();
						break;
				case 2: s = log.getDateTime();
						break;
				case 3: s = log.getSourceIP();
						break;
				case 4: s = log.getDestinationIP();
						break;
				case 5: s = log.getProtocol();
						break;
				case 6: s = log.getPacketSize();
						break;
				case 7: s = log.getComment();
						break;
			}
			i++;
			if (str.equals(s)){
				return log;
			}
		}
		LogEntry log = listLogEntries.get(0);
		return log;
	}

    /**
     * returns the size of the listLogEntries arraylist in a sentence
	 *
     * @return the formatted string
     */
	public String toString(){
		String str = String.format("LogUtility: there are %d records", listLogEntries.size());
		return str;
	}
}