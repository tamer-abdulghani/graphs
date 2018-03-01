/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tamer
 */
public class GraphGenerator<V> {

    private boolean isDirected;

    public GraphGenerator(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public Graph<V> generateComplemtGraph(List<V> nodes) {

        Graph<V> completeGraph = new Graph<V>(false);

        for (V v : nodes) {
            completeGraph.addVertex(v);
        }

        for (V s : nodes) {
            for (V t : nodes) {
                if (s != t) {
                    completeGraph.addEdge(s, t);
                }
            }
        }

        return completeGraph;
    }

    public Graph<V> generateScaleFreeNetwork(ArrayList<V> nodes) {

        Graph<V> graph = new Graph<>(false);

        // create new list of the new nodes. 
        List<V> newNodes = new ArrayList<>();
        for (V n : nodes) {
            newNodes.add(n);
        }
        while (newNodes.size() > 0) {
            ArrayList<V> toConnect = new ArrayList<>();

            // init the graph with the first two node.
            if (graph.getVertices().size() == 0) {
                V firstNode = newNodes.get(0); // get first node in the nodes list 
                graph.addVertex(firstNode);

                V secondeNode = newNodes.get(1); // get second node in the nodes list 
                graph.addVertex(secondeNode);

                graph.addEdge(firstNode, secondeNode);
                newNodes.remove(firstNode);
                newNodes.remove(secondeNode);
            }

            int totalEdges = graph.getEdgesNumber();

            V chosenNode = null;
            float randomFloat = new Random().nextFloat();
            int iNode = 0;
            while ((iNode <= graph.getVertices().size()) && (randomFloat >= 0)) {
                chosenNode = graph.getVertices().get(iNode);
                float nodeProbability = (float) graph.getAdjacencies().get(chosenNode).size() / (float) totalEdges;
                //System.out.println("R:" + randomFloat);
                //System.out.println("P:" + nodeProbability);
                randomFloat = randomFloat - nodeProbability;
                iNode++;
            }

            V currentNode = newNodes.get(0);
            graph.addVertex(currentNode);
            graph.addEdge(currentNode, chosenNode);

            newNodes.remove(currentNode);
        }
        return graph;
    }
}
