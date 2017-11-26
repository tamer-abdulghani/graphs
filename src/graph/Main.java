/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import graph.models.Graph;
import graph.models.GraphGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jgraph.graph.Edge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

/**
 *
 * @author Tamer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Graph<String> graph = new Graph(false);
        String t = "Toulouse";
        String p = "Paris";
        String l = "Lyon";
        String n = "Nates";
        String m = "Marseille";
        String br = "Barcelona";
        String b = "Bordeaux";
        String a = "Amsterdam";

        graph.addVertex(t);
        graph.addVertex(p);
        graph.addVertex(l);
        graph.addVertex(m);
        graph.addVertex(b);
        graph.addVertex(br);
        graph.addVertex(n);
        graph.addVertex(a);

        graph.addEdge(t, l);
        graph.addEdge(t, m);
        graph.addEdge(t, b);
        graph.addEdge(t, br);

        graph.addEdge(m, l);
        graph.addEdge(b, n);
        graph.addEdge(n, p);
        graph.addEdge(l, p);

        // Try connect Barcelona with Paris ==> change all the matrix!
        //graph.addEdge(br, p);
        System.out.println(graph.toString());

        System.out.println(graph.pathLength(br, p));

        System.out.println("Average Shortest Path between all Vertexes is :" + graph.averagePathLength());

        GraphGenerator<String> generator = new GraphGenerator<String>(false);

        Graph<String> freeNetwork = generator.generateScaleFreeNetwork((ArrayList<String>) graph.getVertices());
        System.out.println(freeNetwork.toString());

        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListenableGraph<String, MyEdge> g = buildGraph(freeNetwork);
        JGraphXAdapter<String, MyEdge> graphAdapter
                = new JGraphXAdapter<String, MyEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.add(new mxGraphComponent(graphAdapter));

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        Graph<String> graph2 = new Graph<String>(false);
        for (int i = 0; i < 50; i++) {
            graph2.addVertex("" + i);
        }
        Graph<String> freeNetwork2 = generator.generateScaleFreeNetwork((ArrayList<String>) graph2.getVertices());
        System.out.println(freeNetwork2.toString());

        JFrame frame2 = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListenableGraph<String, MyEdge> g2 = buildGraph(freeNetwork2);
        JGraphXAdapter<String, MyEdge> graphAdapter2
                = new JGraphXAdapter<String, MyEdge>(g2);

        mxIGraphLayout layout2 = new mxCircleLayout(graphAdapter2);
        layout2.execute(graphAdapter2.getDefaultParent());

        frame2.add(new mxGraphComponent(graphAdapter2));

        frame2.pack();
        frame2.setLocationByPlatform(true);
        frame2.setVisible(true);

    }

    public static ListenableGraph<String, MyEdge> buildGraph(Graph<String> myGraph) {
        ListenableDirectedWeightedGraph<String, MyEdge> g
                = new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);

        for (String v : myGraph.getVertices()) {
            g.addVertex(v);
        }

        for (String v1 : myGraph.getVertices()) {
            for (String v2 : myGraph.getAdjacencies().get(v1)) {
                System.out.println("" + v1);
                System.out.println("" + v2);
                MyEdge e = (MyEdge) g.addEdge(v1, v2);
                g.setEdgeWeight(e, 1);
            }
        }
        return g;
    }

    public static class MyEdge extends DefaultWeightedEdge {

        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
}
