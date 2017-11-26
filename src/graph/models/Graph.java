/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.models;

import graph.interfaces.IGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tamer
 */
public class Graph<V> implements IGraph<V> {

    private boolean isDirected;
    private List<V> vertices;
    private Map<V, List<V>> adjacencies;
    private Map<V, Double> distance;

    public Graph(boolean isDirected) {
        vertices = new ArrayList<>();
        adjacencies = new HashMap<>();
        this.isDirected = isDirected;
    }

    @Override
    public int addVertex(V v) {
        if (!vertices.contains(v)) {
            this.vertices.add(v);
        }

        if (!this.adjacencies.containsKey(v)) {
            this.getAdjacencies().put(v, new ArrayList<V>());
        }
        return this.vertices.indexOf(v);
    }

    @Override
    public void removeVertex(V v) {
        if (this.vertices.contains(v)) {
            this.vertices.remove(v);
        }

        if (this.getAdjacencies().containsKey(v)) {
            this.getAdjacencies().remove(v);
        }
    }

    @Override
    public void addEdge(V s, V t) {
        if (this.getAdjacencies().containsKey(s) && this.getAdjacencies().containsKey(t)) {
            if (isDirected) {
                if (!this.adjacencies.get(s).contains(t)) {
                    this.getAdjacencies().get(s).add(t);
                }
            } else {
                if (!this.adjacencies.get(s).contains(t)) {
                    this.getAdjacencies().get(s).add(t);
                }
                if (!this.adjacencies.get(t).contains(s)) {
                    this.getAdjacencies().get(t).add(s);
                }
            }
        }
    }

    @Override
    public void addEdge(int s, int t) {
        V a = this.vertices.get(s);
        V b = this.vertices.get(t);
        this.addEdge(a, b);
    }

    @Override
    public void removeEdge(V s, V t) {
        if (this.getAdjacencies().containsKey(s) && this.getAdjacencies().get(s).contains(t)) {
            this.getAdjacencies().get(s).remove(t);
        }

        if (this.getAdjacencies().containsKey(t) && this.getAdjacencies().get(t).contains(s)) {
            this.getAdjacencies().get(t).remove(s);
        }
    }

    @Override
    public void removeEdge(int s, int t) {
        V a = this.vertices.get(s);
        V b = this.vertices.get(t);
        this.removeEdge(a, b);
    }

    @Override
    public boolean isAdjacent(V s, V t) {

        if (this.getAdjacencies().containsKey(s) && this.getAdjacencies().get(s).contains(t)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isAdjacent(int i, int j) {
        V a = this.vertices.get(i);
        V b = this.vertices.get(j);
        return this.isAdjacent(a, b);
    }

    @Override
    public List<V> getVertices() {
        return this.vertices;
    }

    @Override
    public List<V> neighbors_of(V v) {
        return this.getAdjacencies().get(v);
    }

    @Override
    public String toString() {
        String res = "";

        for (V v1 : this.getAdjacencies().keySet()) {
            res += v1.toString() + ": ";
            for (V v2 : this.getAdjacencies().get(v1)) {
                res += v2.toString() + ",";
            }
            res += "\n";
        }

        return res;
    }

    public int degree(V v) {
        if (this.getAdjacencies().containsKey(v)) {
            return this.getAdjacencies().get(v).size();
        }
        return -1;
    }

    public List<Integer> degreeDistribution() {

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < graphMaxDegree(); i++) {
            list.add(getNodeNumberForADegree(i));
        }

        return list;
    }

    public int graphMaxDegree() {
        int max = 0;
        for (V v1 : this.getAdjacencies().keySet()) {
            if (degree(v1) > max) {
                max = degree(v1);
            }
        }
        return max;
    }

    public int getNodeNumberForADegree(int degree) {

        int count = 0;

        for (V v1 : this.getAdjacencies().keySet()) {
            if (degree(v1) == degree) {
                count++;
            }
        }

        return count;
    }

    public double pathLength(V a, V b) {

        Dijkstra<V> dijkstra = new Dijkstra<V>(a, this);
        dijkstra.execute();
        System.out.println(dijkstra.shortestPathNodes(b));
        return dijkstra.shortestPathLength(b);
    }

    public double averagePathLength() {
        double averageShortestPath = 0.0;
        int countOfPaths = 0;
        for (V source : this.vertices) {
            Dijkstra<V> dijkstra = new Dijkstra<V>(source, this);
            dijkstra.execute();

            for (V target : this.vertices) {
                if (source != target) {
                    double shortPath = dijkstra.shortestPathLength(target);
                    if (shortPath != Double.POSITIVE_INFINITY) {
                        averageShortestPath += shortPath;
                        countOfPaths++;
                    }
                }
            }
        }

        return averageShortestPath / countOfPaths;
    }

    public Map<V, List<V>> getAdjacencies() {
        return adjacencies;
    }

    public int getEdgesNumber() {
        int count = 0;
        for (Map.Entry<V, List<V>> el : this.adjacencies.entrySet()) {
            count += el.getValue().size();
        }

        return count;

    }
};
