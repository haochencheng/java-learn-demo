package com.cc.alogorithm.stack;

import javax.sound.midi.Soundbank;

public class BinaryTree {


    class CBType{
        String data;
        CBType left;
        CBType right;

        @Override
        public String toString() {
            return "CBType{" +
                    "data='" + data + '\'' +
                    ",left=" + left+
                    ",right=" + right+
                    '}';
        }
    }

    CBType initTree(){
        CBType node;

        if ((node=new CBType())!=null){
            node.data="0";
            node.left=null;
            node.right=null;
            if (node!=null){
                return  node;
            }
            return null;
        }
        return null;
    }


    void addTrypeNodeLeft(CBType parentNode,CBType treeNode) {
        parentNode.left=treeNode;
    }

    void addTrypeNodeRight(CBType parentNode,CBType treeNode) {
        parentNode.right = treeNode;
    }

    private CBType TreeFindNode(CBType treeNode, String data) {
        return null;
    }
}
