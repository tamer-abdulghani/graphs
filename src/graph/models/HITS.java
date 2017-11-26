/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tamer
 */
public class HITS {

    public static void RankPage(Graph<String> graph, int k) {

        ArrayList<Page> pages = convertGraphNodesToPages(graph.getVertices());
        HashMap<Page, ArrayList<Page>> adjMatrix = convertGraphAdjToPageMatrix(pages, graph.getAdjacencies());

        for (int i = 0; i < k; i++) {

            for (Page p : pages) {

                p.setAuth(getSumOfHubsValueOfNieghbors(p, adjMatrix));
                p.setHub(getSumOfAuthsValueOfNieghbors(p, adjMatrix));
            }
        }

    }

    private static ArrayList<Page> convertGraphNodesToPages(List<String> vertices) {
        ArrayList<Page> a = new ArrayList<>();
        for (String s : vertices) {
            a.add(new Page(s, 1, 1));
        }
        return a;
    }

    private static HashMap<Page, ArrayList<Page>> convertGraphAdjToPageMatrix(ArrayList<Page> pages, Map<String, List<String>> adjacencies) {
        HashMap<Page, ArrayList<Page>> a = new HashMap<>();

        for (Map.Entry<String, List<String>> el : adjacencies.entrySet()) {
            Page p = getPageByName(pages, el.getKey());
            ArrayList<Page> adj = new ArrayList<>();
            for (String nighbor : el.getValue()) {
                Page ni = getPageByName(pages, nighbor);
                adj.add(ni);
            }
            a.put(p, adj);
        }

        return a;
    }

    private static Page getPageByName(ArrayList<Page> pages, String key) {
        for (Page p : pages) {
            if (p.getName() == key) {
                return p;
            }
        }
        return null;
    }

    private static float getSumOfHubsValueOfNieghbors(Page p, HashMap<Page, ArrayList<Page>> adjMatrix) {
        ArrayList<Page> lsit = adjMatrix.get(p);

        int hubSum = 0;
        for (Page nieghbot : lsit) {
            hubSum += nieghbot.getHub();
        }

        return hubSum;
    }

    private static float getSumOfAuthsValueOfNieghbors(Page p, HashMap<Page, ArrayList<Page>> adjMatrix) {
        ArrayList<Page> lsit = adjMatrix.get(p);

        int authSum = 0;
        for (Page nieghbot : lsit) {
            authSum += nieghbot.getAuth();
        }

        return authSum;
    }
}
