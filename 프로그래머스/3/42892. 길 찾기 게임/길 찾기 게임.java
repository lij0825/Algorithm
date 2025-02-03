import java.util.*;

class Solution {
    
    static int[][] ans;
    static int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        
        int nodeNum = nodeinfo.length; // 노드 수
        
        Node[] node = new Node[nodeNum];
        for(int i = 0;i < nodeNum;i++){
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        
        Arrays.sort(node, (a, b) -> {
            if (a.y == b.y) {
                return a.x - b.x;
            }
            return b.y - a.y;
        });
        
        Node root = node[0];
        for(int i = 1; i < nodeNum; i++) {
            insertNode(root, node[i]); 
        }
        
        // for(int i = 0; i < nodeNum; i++) {
        //     System.out.println("node " + i + " 번째" +
        //                       "\nx : " + node[i].x +
        //                       "\ny : " + node[i].y +
        //                       "\nn : " + node[i].n); 
        // }
        
        ans = new int[2][nodeNum];
        idx = 0;
        System.out.println("preorder");
        preorder(root);
        idx = 0;
        System.out.println("postorder");
        postorder(root);
        return ans;
    }
    
    public void insertNode(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if(parent.right == null) {
                parent.right = child;
            } else { 
                insertNode(parent.right, child);
            }
        }
    }
    
     public void preorder(Node root) {
        if(root != null) {
            System.out.println("n : " + root.n);
            ans[0][idx++] = root.n;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    public void postorder(Node root) {
        if(root != null) {
            System.out.println("n : " + root.n);
            postorder(root.left);
            postorder(root.right);
            ans[1][idx++] = root.n;
        }
    }
    
    static class Node{
        int x, y, n; // 좌표, 번호
        Node right, left; // 자식 노드
        
        public Node(int x, int y, int n, Node right, Node left){
            this.x = x;
            this.y = y;
            this.n = n;
            this.right = right;
            this.left = left;
        }
            
    }
}