/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.models;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tamer
 */
public class Dijkstra<V> {

    private V source;
    private Graph<V> graph;
    private HashMap<V, Double> distances; // The distance between the source and other vertices.  V & Double : distance
    private HashMap<V, V> previous;// To store previous node in optimal path.

    public Dijkstra(V s, Graph<V> g) {
        this.source = s;
        this.graph = g;
        distances = new HashMap<>();
        previous = new HashMap<>();
    }

    public void execute() {
        List<V> nonvisited = new ArrayList<>(); //create list of nonvisited vertexes

        distances.put(source, 0.0);// Distance from source to source is zero.

        for (V v : graph.getVertices()) // Initialization
        {
            if (!distances.containsKey(v)) {
                distances.put(v, Double.POSITIVE_INFINITY); // Set distance = "infinity" to all vertices from source.
            }
            previous.put(v, null);  // Previous node in optimal path from source
            nonvisited.add(v); // Nodes at first are all nonvisited
        }

        while (nonvisited.size() != 0) {
            V u = min_distance(nonvisited);  // get the vertex with the minimum distance from the source.

            // where neighbor is still in Q.
            if (u != null) {
                nonvisited.remove(u);// remove u from nonvisited list 
                for (V neighbor : graph.getAdjacencies().get(u)) {
                    if (nonvisited.contains(neighbor)) {
                        Double alt = distances.get(u) + 1;
                        if (alt < distances.get(neighbor)) // means that there is shorter path has been found
                        {
                            distances.replace(neighbor, alt);
                            if (!previous.containsKey(neighbor)) {
                                previous.put(neighbor, u);
                            } else {
                                previous.replace(neighbor, u);
                            }
                        }
                    }
                }
            } else { // means that all nonvisted distance are infinity and no way to them.
                for (V nonReachable : nonvisited) {
                    System.out.println("Un reachable Nodes in Graph from Source '" + source + "':" + nonReachable);
                }
                nonvisited.clear();
            }
        }
    }

    private V min_distance(List<V> unvisited) {
        V v = null;
        Double min = Double.POSITIVE_INFINITY;
        for (Map.Entry<V, Double> el : distances.entrySet()) {
            if (unvisited.contains(el.getKey())) {
                if (el.getValue() != Double.POSITIVE_INFINITY && el.getValue() < min) {
                    min = el.getValue();
                    v = el.getKey();
                }
            }
        }
        return v;
    }

    public void print_result() {

        // Printing Result
        System.out.println("Distances Matrix from the Source A is:");
        for (Map.Entry<V, Double> el : distances.entrySet()) {
            System.out.println(el.getKey().toString() + ":" + el.getValue());
        }

        // Shortest Path is:
        System.out.println("Shortest Path is :");
        for (Map.Entry<V, V> el : previous.entrySet()) {
            System.out.println(el.getKey() + ":" + el.getValue());
        }
    }

    public String shortestPathNodes(V target) {
        String result = "";
        ArrayList<V> shortestPathNodes = new ArrayList<V>();
        V last = target;
        if (source != target && distances.get(target) != Double.POSITIVE_INFINITY) {
            while (last != source) {
                for (Map.Entry<V, V> el : previous.entrySet()) {

                    if (el.getValue() != null && el.getKey() == last) {
                        shortestPathNodes.add(el.getKey());
                        last = el.getValue();
                        continue;
                    }
                }
            }

            shortestPathNodes.add(source);
            for (int i = shortestPathNodes.size() - 1; i >= 0; i--) {

                result += shortestPathNodes.get(i);
                if (i != 0) {
                    result += "->";
                }
            }
        }
        return result;
    }

    public double shortestPathLength(V target) {
        if (this.distances.containsKey(target)) {
            return this.distances.get(target);
        }
        return 0.0;
    }
}
