package com.codepractice;

import java.io.*;
import java.util.*;

public class TopIPs {
    private static String fileName = String.join(File.separator,
                                "XXXX", "XXXX", "XXXX","XXXXXX","logFile.txt");

    private PriorityQueue<IPCount> countOfIPs = new PriorityQueue<>();

    public IPCount findNode(IPCount toBeFound){
        Iterator<IPCount> itr = this.countOfIPs.iterator();
        while(itr.hasNext()){
            IPCount node = itr.next();
            if(node.equals(toBeFound)){
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        writeLogFile();
        printTopIps();
    }

    private static void writeLogFile() throws IOException {
        Random random = new Random();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i<10000){
            sb.append(random.nextInt(100)).append("\n");
            i++;
        }
        bw.write(sb.toString());
        bw.close();
    }


    public static void printTopIps() throws FileNotFoundException {
        TopIPs iPs = new TopIPs();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        try{
            while((line = br.readLine())!=null){
                IPCount obj = new IPCount(line);
                if(iPs.countOfIPs.contains(obj)){
                    IPCount found = iPs.findNode(obj);
                    found.count = found.count + obj.count;
                    iPs.countOfIPs.remove(found);
                    iPs.countOfIPs.add(found);
                }else{
                    iPs.countOfIPs.add(obj);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Prepared a queue of size :: " + iPs.countOfIPs.size());
        List<IPCount> topIps = findMaxElements(iPs.countOfIPs, 10);
        System.out.println("[");
        topIps.stream().forEach(System.out::println);
        System.out.println("]");
    }

    public static List<IPCount> findMaxElements(PriorityQueue<IPCount> ips, long n){
        List<IPCount> topIps = new ArrayList<>();
        int i=0;
        while (i<n){
            IPCount node = ips.poll();
            if(node!=null){
                topIps.add(node);
            }
            i++;
        }
        return topIps;
    }
}

class IPCount implements Comparable<IPCount>{
    String ip;
    long count;

    public IPCount(String ip) {
        this.ip = ip;
        this.count = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPCount ipCount = (IPCount) o;
        return ip.equals(ipCount.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    };

    @Override
    public String toString() {
        return "{IP:" + this.ip + ", Count: " + this.count + "}";
    }

    @Override
    public int compareTo(IPCount ipCount) {
        return (int) (ipCount.count - this.count);
    }
}
