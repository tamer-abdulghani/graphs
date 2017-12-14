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

            // init the graph with the first node.
            if (graph.getVertices().size() == 0) {
                V firstNode = newNodes.get(0); // get first node in the nodes list 
                graph.addVertex(firstNode);

                V secondeNode = newNodes.get(1); // get first node in the nodes list 
                graph.addVertex(secondeNode);

                graph.addEdge(firstNode, secondeNode);
                newNodes.remove(firstNode);
                newNodes.remove(secondeNode);
            }

            int totalEdges = graph.getEdgesNumber();

            for (V n : graph.getVertices()) {
                List<V> neighbors = graph.getAdjacencies().get(n);
                float nodeProbability = (float)graph.getAdjacencies().get(n).size() / (float)totalEdges;
                float randomFloat = new Random().nextFloat();
                if (nodeProbability > randomFloat) {
                    toConnect.add(n);
                }
            }

            V currentNode = newNodes.get(0);
            graph.addVertex(currentNode);
            for (V v : graph.getVertices()) {
                if (toConnect.contains(v)) {
                    graph.addEdge(v, currentNode);
                }
            }
            newNodes.remove(currentNode);
        }
        return graph;
    }
}
