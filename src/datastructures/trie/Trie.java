package datastructures.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trie {
    TrieNode root;

    Trie() {
        this.root = new TrieNode();
    }

    static class TrieNode {
        TrieNode[] children;
        List<String> suggestions;

        int terminating;

        TrieNode() {
            this.children = new TrieNode[26];
            this.suggestions = new ArrayList<>();
            this.terminating = 0;
        }
    }

    public void insert(String str) {
        TrieNode curr = this.root;
        for (int i = 0; i < str.length(); i++) {
            int chIdx = str.charAt(i) - 'a';
            if (curr.children[chIdx] == null) {
                curr.children[chIdx] = new TrieNode();
            }
            curr = curr.children[chIdx];
            if (curr.suggestions.size() < 3) {
                curr.suggestions.add(str);
            }
            if (i == str.length()) {
                curr.terminating++;
            }
        }
    }

    private List<List<String>> getSuggestions(String str) {
        List<List<String>> suggestions = new ArrayList<>();
        TrieNode curr = this.root;
        for (int i = 0; i < str.length(); i++) {
            int chIdx = str.charAt(i) - 'a';
            if (curr != null) {
                curr = curr.children[chIdx];
            }
            suggestions.add(curr != null ? curr.suggestions : new ArrayList());
        }
        return suggestions;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] products = new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        Arrays.sort(products);
        for (String product : products) {
            trie.insert(product);
        }
        String searchWord = "mouse";
        System.out.println(trie.getSuggestions(searchWord));
    }
}
