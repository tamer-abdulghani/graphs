/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.models;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

/**
 *
 * @author Tamer
 */
public class JGraphT_helper {

    public static void createGUIgraph(Graph<String> graph) {
        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListenableGraph<String, MyEdge> g = getListenableGraph(graph);
        JGraphXAdapter<String, MyEdge> graphAdapter
                = new JGraphXAdapter<String, MyEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.add(new mxGraphComponent(graphAdapter));

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    public static void createGUIgraph(Graph<String> graph, JPanel jPanel1) {
        ListenableGraph<String, MyEdge> g = getListenableGraph(graph);
        JGraphXAdapter<String, MyEdge> graphAdapter
                = new JGraphXAdapter<String, MyEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(new mxGraphComponent(graphAdapter), BorderLayout.CENTER);
        jPanel1.setPreferredSize(jPanel1.getPreferredSize());
        jPanel1.validate();
    }

    public static ListenableGraph<String, MyEdge> getListenableGraph(Graph<String> myGraph) {
        ListenableDirectedWeightedGraph<String, MyEdge> lgraph
                = new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);

        for (String v : myGraph.getVertices()) {
            lgraph.addVertex(v);
        }

        for (String v1 : myGraph.getVertices()) {
            for (String v2 : myGraph.getAdjacencies().get(v1)) {
                MyEdge e = (MyEdge) lgraph.addEdge(v1, v2);
                lgraph.setEdgeWeight(e, 1);
            }
        }
        return lgraph;
    }

    public static class MyEdge extends DefaultWeightedEdge {

        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
}
