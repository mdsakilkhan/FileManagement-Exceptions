import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * A read-only class container for log entry
 *
 * @author Md Sakil Khan
 * @version 1.0
 */
public class LogEntry{
	
	/**
     * holds the log's sequence number
     */
	private final String sequence;
	
	/**
     * holds the log's packet size
     */
	private final String packetSize;
	
	/**
     * holds the log's date and time
     */
	private final String dateTime;
	
	/**
     * holds the log's source IP address
     */
	private final String sourceIP;
	
	/**
     * holds the log's destination IP address
     */
	private final String destinationIP;
	
	/**
     * holds the log's protocol
     */
	private final String protocol;
	
	/**
     * holds the log's comments 
     */
	private final String comment;
	
	/** 
	 * Non-Default constructor 
	 * First validates the parameters using the if statments if they meet the criteria
	 * than initializes the class’ fields. else throws an InstantiationException.	 
     *
     * @param sequence		{@link StepsToMiles#sequence}
     * @param dateTime		{@link StepsToMiles#dateTime}
     * @param sourceIP		{@link StepsToMiles#sourceIP}
     * @param destinationIP	{@link StepsToMiles#destinationIP}
     * @param protocol		{@link StepsToMiles#protocol}
     * @param packetSize	{@link StepsToMiles#packetSize}
     * @param comment		{@link StepsToMiles#comment}
	 */
	public LogEntry(String sequence, String dateTime, String sourceIP, String destinationIP, String protocol, String packetSize, String comment)throws Exception{
		boolean ExcErr = false;
		int i = Integer.parseInt(sequence);		
		if (i < 1 || i > Integer.MAX_VALUE){
			ExcErr = true;
		}
		if (dateTime.length() != 19){
			ExcErr = true;
		}
		if (sourceIP == null || destinationIP == null){
			ExcErr = true;
		}
		i = Integer.parseInt(packetSize);
		if (i < 1 || i > 1500){
			ExcErr = true;
		}
		if (!protocol.equals("TCP") && !protocol.equals("UDP")){ 
			ExcErr = true;
		}		
		if (ExcErr) {
			throw new InstantiationException("One or more values are invalid");
		}
		
		this.sequence = sequence;
		this.packetSize = packetSize;
		this.dateTime = dateTime;
		this.sourceIP = sourceIP;
		this.destinationIP = destinationIP;
		this.protocol = protocol;
		this.comment = comment;
	}

    /**
     * returns the log sequence
     *
     * @return the log sequence
     */	
	public String getSequence(){
		return sequence;
	}

    /**
     * returns the log date and time
     *
     * @return the log date and time
     */	
	public String getDateTime(){
		return dateTime;		
	}

    /**
     * converts and returns the log date and time as a date data-type
     *
     * @return the log date and time as date format
     */		
	public Date getDateTimeAsDate()throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      	Date date = dateFormat.parse(dateTime);
		return date;		
	}

    /**
     * returns the log source IP address
     *
     * @return the log source IP address
     */		
	public String getSourceIP(){
		return sourceIP;		
	}

    /**
     * returns the log destination IP address
     *
     * @return the log destination IP address
     */		
	public String getDestinationIP(){
		return destinationIP;		
	}

    /**
     * returns the log protocol
     *
     * @return the log protocol
     */		
	public String getProtocol(){
		return protocol;		
	}

    /**
     * returns the log comments
     *
     * @return the log comments
     */		
	public String getComment(){
		return comment;		
	}

    /**
     * returns the log packet size
     *
     * @return the log packet size
     */		
	public String getPacketSize(){
		return packetSize;		
	}

    /**
     * formats and returns the log as a string
     *
     * @return the formatted string
     */		
	public String toString(){
		String str = String.format("%s,%s,%s,%s,%s,%s,%s", sequence, dateTime, 
						sourceIP, destinationIP, protocol, packetSize, comment);
		return str;
	}
}