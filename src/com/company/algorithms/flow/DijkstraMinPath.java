package com.company.algorithms.flow;

import com.company.fuzzy.hierarchy.FuzzyNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Fuzziness;
import com.company.fuzzy.interfaces.Operations;

import java.util.*;

public class DijkstraMinPath< E extends FuzzyNumber ,
        O extends Comparator< E > & Operations< E > & Builder< E > ,
        T extends Fuzziness< E > > {

    private O ops;
    private T entropy;

    private Vector<E> maxPoss;
    private Vector<Double> minTimes;
    private Vector<Vector<Integer>> minPaths;

    public DijkstraMinPath(O _ops, T _entropy) {
        ops = _ops;
        entropy = _entropy;
    }

    private void findMinPaths(Graph graph, int s, double maxTime) {
        int n = graph.n;
        maxPoss = new Vector<E>(n);
        minTimes = new Vector<Double>(n);
        minPaths = new Vector<Vector<Integer>>(n);
        for (int i = 0; i < n; ++i) {
            if (i == s) {
                maxPoss.add(ops.newMax());
                minTimes.add(0.);
            } else {
                maxPoss.add(ops.newMin());
                minTimes.add(Double.POSITIVE_INFINITY);
            }
            minPaths.add(new Vector<Integer>());
        }

        boolean[] finishedNodes = new boolean[n];

        int currentNode;
        do {
            E currentMaxPoss = ops.newMin();
            currentNode = -1;
            for (int i = 0; i < n; ++i) {
                if (!finishedNodes[i] && ops.compare(maxPoss.get(i), currentMaxPoss) > 0) {
                    currentNode = i;
                    currentMaxPoss = maxPoss.get(i);
                }
            }

            if (currentNode == -1) {
                break;
            }

            finishedNodes[currentNode] = true;
            for (int i = 0; i < n; ++i) {
                Graph.Edge e = graph.adjacencyMatrix.get(currentNode).get(i);
                if (e != null) {
                    if (!finishedNodes[i] && minTimes.get(currentNode) + e.time < maxTime) {
                        E newPoss = ops.mult(maxPoss.get(currentNode), (E) e.cost);
                        if (ops.compare(maxPoss.get(i), newPoss) < 0) {
                            maxPoss.set(i, newPoss);
                            minTimes.set(i, minTimes.get(currentNode) + e.time);
                            Vector<Integer> newPath = new Vector<Integer>(minPaths.get(currentNode));
                            newPath.add(i);
                        }
                    }
                }
            }

        } while (currentNode != -1);

        print();
    }

    public void print() {
        for (int i = 0; i < maxPoss.size(); ++i) {
            System.out.println("Cost: " + maxPoss.get(i).toString() + " Time: " + minTimes.get(i) + " Entropy: " + entropy.getEntropy(maxPoss.get(i)));
        }
    }

    public double getAverageEntropy(Graph graph, int s, double maxTime) {
        findMinPaths(graph, s, maxTime);
        double averageEntropy = 0.;
        for (int i = 0; i < maxPoss.size(); ++i) {
            averageEntropy += entropy.getEntropy(maxPoss.get(i));
        }
        averageEntropy /= maxPoss.size();
        return averageEntropy;
    }

    public double getSTEntropy(Graph graph, int s, int t, double maxTime) {
        findMinPaths(graph, s, maxTime);
        return entropy.getEntropy(maxPoss.get(t));
    }

    public E getSTPoss(Graph graph, int s, int t, double maxTime) {
        findMinPaths(graph, s, maxTime);
        return maxPoss.get(t);
    }

}
