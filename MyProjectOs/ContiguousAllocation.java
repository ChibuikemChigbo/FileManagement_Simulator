package MyProjectOs;
import java.util.ArrayList;
import java.util.List;

public class ContiguousAllocation implements FileAllocationAlgorithm {
    private List<Integer> disk;
    private int blockSize;
    private int numOfBlocks;

    public ContiguousAllocation() {
        this.disk = new ArrayList<>();
        this.blockSize = 1024;
        this.numOfBlocks = 1000; // default number of blocks
        for (int i = 0; i < numOfBlocks; i++) {
            disk.add(0);
        }
    }

    @Override
    public List<Integer> allocateSpace(File file, int blockSize) {
        int numBlocks = (int) Math.ceil((double) file.getSize() / blockSize);
        int startBlock = findContiguousFreeBlocks(numBlocks);
        if (startBlock != -1) {
            for (int i = startBlock; i < startBlock + numBlocks; i++) {
                disk.set(i, 1);
            }
            List<Integer> blockList = new ArrayList<>();
            for (int i = startBlock; i < startBlock + numBlocks; i++) {
                blockList.add(i);
            }
            file.setBlockList(blockList);
        }
        return file.getBlockList();
    }

    @Override
    public void deallocateSpace(File file) {
        for (int block : file.getBlockList()) {
            disk.set(block, 0);
        }
        file.setBlockList(null);
    }

    @Override
    public List<Integer> moveFile(File file, Directory directory) {
        List<Integer> blockList = file.getBlockList();
        if (blockList != null) {
            deallocateSpace(file);
            List<Integer> newBlockList = allocateSpace(file, blockSize);
            file.setBlockList(newBlockList);
        }
        return file.getBlockList();
    }

    @Override
    public void renameFile(File file, String newName) {
        file.setName(newName);
    }
    

    private int findContiguousFreeBlocks(int numBlocks) {
        int count = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == 0) {
                count++;
                if (count == numBlocks) {
                    return i - numBlocks + 1;
                }
            } else {
                count = 0;
            }
        }
        return -1;
    }

    public int getNumOfBlocks() {
        return numOfBlocks;
    }

    public void setNumOfBlocks(int numOfBlocks) {
        this.numOfBlocks = numOfBlocks;
        disk.clear();
        for (int i = 0; i < numOfBlocks; i++) {
            disk.add(0);
        }
    }

    @Override
    public double getAverageFragmentation() {
        int freeBlocks = 0;
        int occupiedBlocks = 0;
        int maxFreeBlocks = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == 0) {
                freeBlocks++;
                if (freeBlocks > maxFreeBlocks) {
                    maxFreeBlocks = freeBlocks;
                }
            } else {
                occupiedBlocks++;
                freeBlocks = 0;
            }
        }
        return (double) maxFreeBlocks / numOfBlocks;
    }
    @Override
    public int getWastedBlocks() {
        int wastedBlocks = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == 0) {
                wastedBlocks++;
            }
        }
        return wastedBlocks;
    }
}

    