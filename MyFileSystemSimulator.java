import java.util.*;
import java.io.*;
import MyProjectOs.*;
public class MyFileSystemSimulator {
    public static void main(String[] args) {
        
        FileSystemManager FM = new FileSystemManager(new ContiguousAllocation());
        Scanner sc = new Scanner(System.in);
        
        // time of simulation is in seconds 
        System.out.print("Enter how long simulation should last(in seconds): ");
        int simulationTime = sc.nextInt();
        // time unit is in seconds
        System.out.print("Enter the time unit you want for this simulation(Number must be a factor of your simulation time): ");
        int timeUnit = sc.nextInt();

        // Creating files,deleting,renaming and moving file in a set amount of time
        for (int i = 0; i < simulationTime; i += timeUnit) {
            // let us create a random number of files
            int numOfFiles = new Random().nextInt(10);
            for (int j = 0; j < numOfFiles; j++) {
                String fileName = "file" + UUID.randomUUID().toString().substring(0, 8) ;
                FM.createFile(fileName);
            }

            // deleting a random number of files
            int numOfDeletedFiles = numOfFiles > 0 ? new Random().nextInt(numOfFiles) : 0;

            for (int j = 0; j < numOfDeletedFiles; j++) {
                int fileIndex = new Random().nextInt(FM.getFiles().size());
                String fileName = FM.getFiles().get(fileIndex).getName();
                FM.deleteFile(fileName);
            }

            // random file renaming
            if (FM.getFiles().size() > 0) {
                int fileIndex = new Random().nextInt(FM.getFiles().size());
                String oldFileName = FM.getFiles().get(fileIndex).getName();
                String newFileName = "file" + UUID.randomUUID().toString().substring(0, 8) + ".txt";
                FM.renameFile(oldFileName, newFileName);
            }

            // random file to be moved into a random directory
            if (FM.getFiles().size() > 0 && FM.getDirectories().size() > 1) {
                int fileIndex = new Random().nextInt(FM.getFiles().size());
                int dirIndex = new Random().nextInt(FM.getDirectories().size());
                String fileName = FM.getFiles().get(fileIndex).getName();
                String dirName = FM.getDirectories().get(dirIndex).getName();
                FM.moveFile(fileName, dirName);
            }

            // Printing the required outputs
            double avgFragmentation = FM.getAverageFragmentation();
            int wastedBlocks = FM.getWastedBlocks();
            System.out.println("Unit of time taken at each instance: " + i + "-" + (i + timeUnit) + " second(s)");
            System.out.println("Average fragmentation: " + avgFragmentation);
            System.out.println("Wasted disk blocks are " + wastedBlocks);
        }
    }
}
