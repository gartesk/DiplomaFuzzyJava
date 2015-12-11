package com.company.algorithms.flow;

import com.company.fuzzy.hierarchy.FuzzyNumber;

import java.util.Vector;

public class Graph {

    public static class Edge {
        FuzzyNumber cost;
        double time;

        Edge(FuzzyNumber _cost, double _time) {
            cost = _cost;
            time = _time;
        }

        Edge(Edge e) {
            cost = e.cost;
            time = e.time;
        }
    }

    int n;
    Vector<Vector<Edge>> adjacencyMatrix;

}
