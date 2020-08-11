package company.amazon.oa.amcat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestions {
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

    static class Trie {
        TrieNode root;

        Trie() {
            this.root = new Trie.TrieNode();
        }

        static class TrieNode {
            Trie.TrieNode[] children;
            List<String> suggestions;

            int terminating;

            TrieNode() {
                this.children = new Trie.TrieNode[26];
                this.suggestions = new ArrayList<>();
                this.terminating = 0;
            }
        }

        public void insert(String str) {
            Trie.TrieNode curr = this.root;
            for (int i = 0; i < str.length(); i++) {
                int chIdx = str.charAt(i) - 'a';
                if (curr.children[chIdx] == null) {
                    curr.children[chIdx] = new Trie.TrieNode();
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
            Trie.TrieNode curr = this.root;
            for (int i = 0; i < str.length(); i++) {
                int chIdx = str.charAt(i) - 'a';
                if (curr != null) {
                    curr = curr.children[chIdx];
                }
                suggestions.add(curr != null ? curr.suggestions : new ArrayList());
            }
            return suggestions;
        }
    }
}
