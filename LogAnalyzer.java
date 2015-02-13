/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    
    /**
     * Create an object to analyze hourly web accesses of log.
     * 
     */
    public LogAnalyzer(String filename)
    {
          // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
        
       
    }
    
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    
    
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
         System.out.println("Hr: Count");
        // for(int hour = 0; hour < hourCounts.length; hour++) {
        //     System.out.println(hour + ": " + hourCounts[hour]);
        // }
        int hour = 0;
        while(hour < hourCounts.length)
        {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * devuelva el número total de accesos al servidor web registrados en el archivo de log. 
     */
    public int numberOfAccesses()
    {
        //guardado del numero de todos los accesos
        int numberOfAccesses = 0;
        int hour = 0;
        //sumalizacion de todos los accesos de cada hora
        while(hour < hourCounts.length)
        {
            numberOfAccesses = numberOfAccesses + hourCounts[hour];
            hour++;
        }
        
        return numberOfAccesses;
    }
    
    /**
     * devuelve en qué hora el servidor tuvo que responder a más peticiones. 
     */
    public int busiestHour()
    {
        int maxHour = 0;
        int hour = 0;
        //sumalizacion de todos los accesos de cada hora
        while(hour < hourCounts.length)
        {
          if (maxHour < hourCounts[hour])
          {
              maxHour = hour;
          }
            hour++;
        }  
        return maxHour;
    }
    
    /**
     *  devuelve la hora a la que el servidor estuvo menos sobrecargado
     */
    
    public int quietestHour()
    {
        int maxHour = hourCounts[0];
        int hour = 1;
        //sumalizacion de todos los accesos de cada hora
        while(hour < hourCounts.length)
        {
          if (maxHour > hourCounts[hour])
          {
              maxHour = hour;
          }
            hour++;
        }  
        return maxHour;  
    }
}
