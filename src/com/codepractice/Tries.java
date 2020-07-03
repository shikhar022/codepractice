package com.codepractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tries {

    public static void main(String[] args) {
        Trie trie = new Trie();
        Arrays.asList("pqrs","pqqt","qqrr").forEach(trie::add);

    }
}

class Trie{

    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }


    public boolean query(String s){
        boolean flag = true;
        TrieNode current = root;
        for(int i=0;i< s.length();i++){
            current = current.next(s.charAt(i));
            if(current == null){
                flag = false;
                break;
            }
        }
        return flag;
    }

    // O(log_26 n)
    public TrieNode add(String s){  // pqrs
        if(s == null || s.isEmpty()){
            return root;
        }
        TrieNode current = root;
        for(int i=0;i< s.length();i++){
            if(!current.has(s.charAt(i))){ // false
                current.insert(s.charAt(i)); // root -> {20 : TrieNode(p) -> {21:TrieNode(q)}}
            }
            current = current.next(s.charAt(i));
        }

        if(current!=null){
            current.setTerminating();
        }
        return root;
    }

    // O(Log_26 n)
    public void delete(String s){ // [pqrs, qrs, qrs, qrst, qrt]
        if(s == null || s.isEmpty()){
            return;
        }
        TrieNode current = root;
        TrieNode prev = root;
        for(int i=0; i<s.length(); i++){
            if(current.has(s.charAt(i))){
                prev = current;
                current = current.next(s.charAt(i));
                if(current==null){
                    return;
                }
                if(current.getTerminating() > 1){
                    current.removeNode(s.charAt(i));
                }else if(current.children() == 0 && current.getTerminating()== 1){
                    prev.removeChild(s.charAt(i));
                }
            }
        }
    }

}

class TrieNode{

    private int terminating = 0;
    private Character data;
    private Map<Character, TrieNode> trieNodes = new HashMap<>();

    public TrieNode(){}

    public TrieNode(char c) {
        this.data = c;
    }

    public TrieNode next(char c){
        return trieNodes.get(c);
    }

    public void insert(char c) {
        this.trieNodes.put(c, new TrieNode(c));
    }

    public void setTerminating() {
        this.terminating += 1;
    }

    public boolean has(char c) {
        return this.trieNodes.get(c) != null;
    }

    public int children() {
        return this.trieNodes.size();
    }

    public void removeNode(char c) {
        if(this.terminating > 0){
            this.terminating--;
        }

        if(this.terminating==0){
            this.trieNodes.remove(c);
        }
    }

    public void removeChild(char c){
        TrieNode node = this.trieNodes.get(c);
        if(node!=null){
            this.trieNodes.remove(c);
        }
    }

    public int getTerminating() {
        return this.terminating;
    }
}