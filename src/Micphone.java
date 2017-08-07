 
// Simple example how to read Microphone line in Java.
// AudioRead class implements Runnable, however you can
// just call AudioRead instance's run() function.
 
// AudioFormat sets the format that you will read from
// microphone. TargetDataLine can be seen as the data source,
// which represents your audio stream.

public class Micphone 
{
	public static void main(String args[])
	{
		Runnable myRunnable = new AudioRead();
		
		Thread myThread = new Thread(myRunnable);
		
		myThread.start();
		
		try
		{
			myThread.wait();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
}