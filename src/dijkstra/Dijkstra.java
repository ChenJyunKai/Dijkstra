package dijkstra;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Dijkstra {

    public static void main(String[] args) {
        ArrayList<Node> nodeArray = new ArrayList<>();
        Node NodeA = new Node("A");
        Node NodeB = new Node("B");
        Node NodeC = new Node("C");
        Node NodeD = new Node("D");
        Node NodeE = new Node("E");
        Node NodeF = new Node("F");

        NodeA.setNearNodes(new HashMap() {
            {
                put(NodeB, 3);
                put(NodeD, 8);
            }
        });
        NodeB.setNearNodes(new HashMap() {
            {
                put(NodeD, 5);
                put(NodeE, 6);
            }
        });
        NodeD.setNearNodes(new HashMap() {
            {
                put(NodeE, 3);
                put(NodeF, 2);
            }
        });
        NodeE.setNearNodes(new HashMap() {
            {
                put(NodeC, 9);
                put(NodeF, 1);
            }
        });
        NodeF.setNearNodes(new HashMap() {
            {
                put(NodeC, 3);
            }
        });
        nodeArray.add(NodeA);
        nodeArray.add(NodeB);
        nodeArray.add(NodeC);
        nodeArray.add(NodeD);
        nodeArray.add(NodeE);
        nodeArray.add(NodeF);
        NodeA.setminDistance(0);
        sort(NodeA, nodeArray);
        //輸出最短路徑及距離
        String[] path = NodeC.previousNodes.split("");
        for (int i = 0; i <= path.length; i++) {
            if (i == path.length) {
                System.out.println(NodeC.name + " : " + NodeC.minDistance);

            } else {
                System.out.print(path[i] + " -> ");
            }
        }
    }

    public static void sort(Node start, ArrayList<Node> nodeArray) {
        start.setminDistance(0);
        Node nowNode = start;
        while (nodeArray.size() > 1) {
            int index = 0;
            for (int i = 0; i < nodeArray.size(); i++) {
                if (nodeArray.get(i).name.equals(nowNode.name)) {
                    index = i;
                }
            }
            Node node = nodeArray.remove(index);
            node.nodes.forEach((u, v) -> {
                if (u.minDistance > nowNode.minDistance + v) {
                    //紀錄各鄰近節點的最短距離
                    u.setminDistance(nowNode.minDistance + v);
                    //紀錄各鄰近節點的最短路徑
                    if (!node.name.equals("A")) {
                        u.setPreviousNodes(nowNode.previousNodes + nowNode.name);
                    } else {
                        u.setPreviousNodes(nowNode.name);
                    }
                }
            });
            //找尋當前最佳解
            int minDistanse = nodeArray.get(0).minDistance;
            int minDistanseIndex = 0;
            for (int i = 0; i < nodeArray.size(); i++) {
                if (nodeArray.get(i).minDistance < minDistanse) {
                    minDistanse = nodeArray.get(i).minDistance;
                    minDistanseIndex = i;
                }
            }
            Node temp = nodeArray.get(minDistanseIndex);
            nowNode.setData(temp.name, temp.minDistance, temp.previousNodes);
        }
    }

}

class Node {

    //節點
    protected String name;
    //最小路徑
    protected Integer minDistance = Integer.MAX_VALUE;
    //關聯子節點
    protected Map<Node, Integer> nodes = new HashMap<>();
    //上一個節點
    protected String previousNodes;

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public void setNearNodes(Map<Node, Integer> nodes) {
        this.nodes = nodes;
    }

    public void setData(String name, int minDistance, String previousNodes) {
        this.name = name;
        this.minDistance = minDistance;
        this.previousNodes = previousNodes;
    }

    public void setminDistance(int distance) {
        this.minDistance = distance;
    }

    public void setPreviousNodes(String node) {
        this.previousNodes = node;
    }
}
