package MyProjectOs;
import java.util.*;

interface FileAllocationAlgorithm {
    List<Integer> allocateSpace(File file, int blockSize);
    void deallocateSpace(File file);
    
    void renameFile(File file, String newFileName);
    
    List<Integer> moveFile(File file, Directory directory);
    
    double getAverageFragmentation();
    
    int getWastedBlocks();
    }
    
    
    