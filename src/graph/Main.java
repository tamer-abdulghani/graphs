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
import graph.models.JGraphT_helper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jgraph.graph.Edge;


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

        JGraphT_helper.createGUIgraph(freeNetwork);

        Graph<String> graph2 = new Graph<String>(false);
        for (int i = 0; i < 50; i++) {
            graph2.addVertex("" + i);
        }
        Graph<String> freeNetwork2 = generator.generateScaleFreeNetwork((ArrayList<String>) graph2.getVertices());
        System.out.println(freeNetwork2.toString());

       JGraphT_helper.createGUIgraph(freeNetwork2);

       graphDraw fra = new graphDraw();
       fra.setVisible(true);
    }

   

   
}
