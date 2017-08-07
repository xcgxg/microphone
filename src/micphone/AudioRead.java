package micphone;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioRead implements Runnable 
{
	 
    public boolean stop = false;
          
    @Override
    public void run()
    {
 
        // Set audio recording format (sampling rate, bits, mono/stereo, signed, bigendian/littleEndian)
        AudioFormat format = new AudioFormat(44100.0f,16,1,true,false);
        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        // Check if its supported by OS/hardware
        if(!AudioSystem.isLineSupported(info))
        {
 
            System.out.println("AudioSystem Line Not Supported");
            return;            
        }
         
        try
        {
         	// begin recording
            line = (TargetDataLine)AudioSystem.getLine(info);
            line.open(format);
             
            //int bytesToRead = (int)Math.round(MicrophoneIQ.SAMPLES_PER_CYCLE * MicrophoneIQ.CYCLES_PER_READ)*2;
            int bytesToRead = 512; // Buffer size
            if(bytesToRead > line.getBufferSize())
            {
 
                System.out.println("BufferSize is less than required read size");
                return;
             
            }
             
            line.start();
             
            while(!this.stop)
            {
 
                byte data[] = new byte[bytesToRead];
                line.read(data, 0, bytesToRead);
                
                for (byte b : data) 
                {
					System.out.printf("%d ", data);
				}
                /*
                MicrophoneIQ.stack.push(data);
                MicrophoneIQ.wnd.jTFStackDepth.setText(String.valueOf(MicrophoneIQ.stack.getIndex()));*/
                // TODO:
                // PROCESS the raw data
                // the raw data is inside data[] array.
             
            }
             
            line.stop();
         
        }
        catch(LineUnavailableException ex)
        {
            System.out.println("LineUnavailable");
            return;
         
        }
    }
}