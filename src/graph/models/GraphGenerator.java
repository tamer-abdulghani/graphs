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

        // nodes list that should be added to the graph
        // m0 the initial number of nodes that should the graph contain at first before adding any node.
        // i.e. construct complete graph with m0 nodes.
        // minDegree is the minimum number of edges that should every node have.
        /*
        1) Add m0 nodes to G.
        2) Connect every node in G to every other node in G, i.e. create a complete graph.
        3) Create a new node i.
        4) Pick a node j uniformly at random from the graph G. Set P = (k(j)/k_tot)^a.
        5) Pick a real number R uniformly at random between 0 and 1.
        6) If P > R then add j to i's adjacency list.
        7) Repeat steps 4 - 6 until i has m nodes in its adjacency list.
        8) Add i to the adjacency list of each node in its adjacency list.
        9) Add i to to the graph.
        10) Repeat steps 3 - 9 until there are N nodes in the graph.
        {
            ArrayList<V> 
        }
        m0Nodes = new ArrayList<V>();
        ArrayList<V> nodesToAdd = new ArrayList<>();
        for (int i = 0;
                i < m0;
                i++) {
            m0Nodes.add(nodes.get(i));
        }
        Graph<V> graph = generateComplemtGraph(m0Nodes);

        for (int i = m0;
                i < nodes.size();
                i++) {
            nodesToAdd.add(nodes.get(i));
        }

        int nodes_added = 0;
        int nodesToBeAdded = nodesToAdd.size();

        while (nodes_added < nodesToBeAdded) {
            ArrayList<V> to_connect = new ArrayList<V>(); // List of Vertexes to connect to newly added

            int counter = 0;
            while (counter < m0) //  m0 is wrong :)   # For each branch to be connected
            {
                Random random = new Random();//          # generate a random number
                Float rnd = random.nextFloat();
                // Nodes are # Iterate over all nodes of the graph 
                double temp = 0.0;
                int nb_edges = graph.getEdgesNumber();//len(graph.edges())     

                //#Populate to_connect
                for (V node : nodesToAdd) {
                    if (nb_edges == 0)//# If no edge is present in graph, avoid divergence
                    {
                        temp = 1.0;

                    } else //#Otherwise increase value of temp proportionaly
                    {
                        temp += (float) graph.getAdjacencies().get(node).size() / (float) (graph.getEdgesNumber());
                    }
                    if (temp > rnd)//      # If temp is larger than rnd, add the BA node
                    {
                        to_connect.add(node);
                        break;
                    }
                }
                counter += 1;
                //# Connect the new node
                //graph.addVertex(nodes_to_add);

                for (V node : to_connect) {
                    // graph.addVertex(node);
                    //graph.addEdge(node, node);

                }
                nodes_added += 1;
            }

        }

         */
        return graph;
    }
}
