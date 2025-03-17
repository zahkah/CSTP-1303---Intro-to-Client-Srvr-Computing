package byteStream_assignment;

import java.io.*;

public class FileCopyComparison {
    public static void main(String[] args) {
        // File paths for the source File and the target Files for each implementation
        String sourceFile = "D:\\source.txt";
        String targetFileByteStream = "D:\\target_byte_stream.txt";
        String targetFileCharStream = "D:\\target_char_stream.txt";
        String targetFileBufferedStream = "D:\\target_buffered_stream.txt";
        String targetFileBufferedReaderWriter = "D:\\target_buffered_reader_writer.txt";

        // Part 1:I Copied the file using Byte Streams and measure the time taken 
        
        long startTimeByteStream = System.nanoTime(); // Start time measurement
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(targetFileByteStream)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTimeByteStream = System.nanoTime(); // End time measurement
        System.out.println("File copied using byte streams. Time taken: " + (endTimeByteStream - startTimeByteStream) + " ns");

        // Part 2: copy file using Character Streams and measure time taken 
        long startTimeCharStream = System.nanoTime();
        try (Reader reader = new FileReader(sourceFile);
             Writer writer = new FileWriter(targetFileCharStream)) {
            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charsRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTimeCharStream = System.nanoTime();
        System.out.println("File copied using character streams. Time taken: " + (endTimeCharStream - startTimeCharStream) + " ns");

        // Part 3: Copy the file using buffered streams and measure the time taken.
        long startTimeBufferedStream = System.nanoTime();
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(targetFileBufferedStream);
             BufferedInputStream bin = new BufferedInputStream(in);
             BufferedOutputStream bout = new BufferedOutputStream(out)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bin.read(buffer)) != -1) {
                bout.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTimeBufferedStream = System.nanoTime();
        System.out.println("File copied using buffered streams. Time taken: " + (endTimeBufferedStream - startTimeBufferedStream) + " ns");

        // Part 4: Copy the file using readers and writers with buffering and measure the time taken.
        
        long startTimeBufferedReaderWriter = System.nanoTime();
        try (Reader reader = new FileReader(sourceFile);
             Writer writer = new FileWriter(targetFileBufferedReaderWriter);
             BufferedReader br = new BufferedReader(reader);
             BufferedWriter bw = new BufferedWriter(writer)) {
            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = br.read(buffer)) != -1) {
                bw.write(buffer, 0, charsRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTimeBufferedReaderWriter = System.nanoTime();
        System.out.println("File copied using buffered reader/writer. Time taken: " + (endTimeBufferedReaderWriter - startTimeBufferedReaderWriter) + " ns");
    }
}
