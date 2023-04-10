package MyProjectOs;
import java.util.*;

import java.io.*;

public class FileSystemManager {
    public FileAllocationAlgorithm allocationAlgorithm;
    public List<File> files;
    public List<Directory> directories;
    public int blockSize;

    public FileSystemManager(FileAllocationAlgorithm allocationAlgorithm) {
        this.allocationAlgorithm = allocationAlgorithm;
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
        this.blockSize = 1024; // default block size
    }

    public void createFile(String fileName) 
    {
        int fileSize = new Random().nextInt(1024 * 10); // maximum file size is 10 MB
        File file = new File(fileName, fileSize);
        allocationAlgorithm.allocateSpace(file, blockSize);
        if (file.getBlockList() != null) {
            files.add(file);
            System.out.println("New File Created: " + fileName);
        } else {
            System.out.println("This file failed to create: " + fileName);
        }
    }
    
    public void deleteFile(String fileName) {
        File file = getFileByName(fileName);
        if (file != null) {
            files.remove(file);
            allocationAlgorithm.deallocateSpace(file);       
            System.out.println("Random file deleted is  " + fileName);
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    public void renameFile(String oldFileName, String newFileName) {
        File file = getFileByName(oldFileName);
        if (file != null) {
            allocationAlgorithm.renameFile(file, newFileName);
            file.setName(newFileName);
            System.out.println("Random file that was renamed is  " + oldFileName + " -> " + newFileName);
        } else {
            System.out.println("File not found: " + oldFileName);
        }
    }

    public void moveFile(String fileName, String dirName) {
        File file = getFileByName(fileName);
        Directory dir = getDirectoryByName(dirName);
        if (file != null && dir != null) {
            allocationAlgorithm.moveFile(file, dir);
            file.setDirectory(dir);
            System.out.println("This file has been moved: " + fileName + " -> " + dirName);
        } else if (file == null) {
            System.out.println("File not found: " + fileName);
        } else {
            System.out.println("Directory not found: " + dirName);
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public double getAverageFragmentation() {
        return allocationAlgorithm.getAverageFragmentation();
    }

    public int getWastedBlocks() {
        return allocationAlgorithm.getWastedBlocks();
    }

    private File getFileByName(String fileName) {
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    private Directory getDirectoryByName(String dirName) {
        for (Directory dir : directories) {
            if (dir.getName().equals(dirName)) {
                return dir;
            }
        }
        return null;
    }
}

       
