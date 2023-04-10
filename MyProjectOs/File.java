package MyProjectOs;
import java.util.*;
public class File 
{
public String name;
public int size;
public List<Integer> blockList;
public Directory directory;
public File(String name, int size) {
    this.name = name;
    this.size = size;
    this.blockList= new ArrayList<>();
    this.directory=null;
}
public String getName() {
    return name;
}
public int getSize() {
    return size;
}

public List<Integer> getBlockList() {
    return blockList;
}
public Directory getDirectory() {
    return directory;
}
public void setDirectory(Directory directory) {
    this.directory = directory;
}
public void setName(String name){
    this.name =name;
}
public void setBlockList(List<Integer> blockList){
    this.blockList = blockList;
}
}
