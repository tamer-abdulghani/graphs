/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.interfaces;

import java.util.List;

/**
 *
 * @author Tamer
 */
public interface IGraph<V> {

    public abstract int addVertex(V v);

    public abstract void removeVertex(V v);

    public abstract void addEdge(V s, V t);

    public abstract void addEdge(int s, int t);

    public abstract void removeEdge(V s, V t);

    public abstract void removeEdge(int s, int t);

    public abstract boolean isAdjacent(V s, V t);

    public abstract boolean isAdjacent(int i, int j);

    public abstract List<V> getVertices();

    public abstract List<V> neighbors_of(V v);
}
