package com.company.algorithms.flow;

import com.company.fuzzy.hierarchy.FuzzyNumber;
import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.hierarchy.TrapezoidalNumber;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.Builder;
import com.company.fuzzy.interfaces.Converter;
import com.company.fuzzy.interfaces.implementations.*;

import java.util.Random;
import java.util.Vector;

public class GraphGenerator {

    private static GraphGenerator instance;

    private GraphGenerator() {
        initialGraph = new Graph();
        triangularGraph = new Graph();
        trapezoidalGraph = new Graph();
        intervalGraph = new Graph();
    }

    public static GraphGenerator getInstance() {
        if (instance == null) {
            instance = new GraphGenerator();
        }
        return instance;
    }

    private Converter<Interval, TriangularNumber> intTriConverter;
    private Converter<TriangularNumber, TrapezoidalNumber> triTrapConverter;
    private Converter<TrapezoidalNumber, Interval> trapIntConverter;

    public Graph initialGraph;

    public Graph triangularGraph;
    public Graph trapezoidalGraph;
    public Graph intervalGraph;

    private int[] chainParams = null;

    public void init(Converter<Interval, TriangularNumber> _intTriConverter,
                     Converter<TriangularNumber, TrapezoidalNumber> _triTrapConverter,
                     Converter<TrapezoidalNumber, Interval> _trapIntConverter) {
        intTriConverter = _intTriConverter;
        triTrapConverter = _triTrapConverter;
        trapIntConverter = _trapIntConverter;
    }

    public void setFreeMode() {
        chainParams = null;
        generateTriangularGraph();
        generateTrapezoidalGraph();
        generateIntervalGraph();
    }

    public void setChainMode(int[] params) {
        try {
            chainParams = params;
            if ((chainParams[0] == 2 && chainParams[1] == 2) || (chainParams[1] == 1 && chainParams[2] == 1)) {
                generateIntervalGraph();
            } else {
                if ((chainParams[0] == 1 && chainParams[2] == 1) || (chainParams[1] == 2 && chainParams[2] == 2)) {
                    generateTriangularGraph();
                } else {
                    if ((chainParams[0] == 1 && chainParams[1] == 1) || (chainParams[0] == 2 && chainParams[2] == 2)) {
                        generateTrapezoidalGraph();
                    } else {
                        chainParams = null;
                        throw new Exception("Invalid chain params");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void generateRandomGraph(int n, double pTriangular, double pTrapezoidal, double pInterval) {
        initialGraph.n = n;
        initialGraph.adjacencyMatrix = new Vector<Vector<Graph.Edge>>(n);
        for (int i = 0; i < n; ++i) {
            initialGraph.adjacencyMatrix.add(new Vector<Graph.Edge>(n));
        }
        Random rand = new Random();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                initialGraph.adjacencyMatrix.get(i).add(initialGraph.adjacencyMatrix.get(j).get(i));
            }
            initialGraph.adjacencyMatrix.get(i).add(null);
            for (int j = i + 1; j < n; ++j) {
                double currentRoll = rand.nextDouble();
                if (currentRoll < pTriangular) {
                    initialGraph.adjacencyMatrix.get(i).add(new Graph.Edge(TriAveragePossOps.getInstance().newRandom(), rand.nextDouble()));
                } else {
                    if (currentRoll < pTriangular + pTrapezoidal) {
                        initialGraph.adjacencyMatrix.get(i).add(new Graph.Edge(TrapAveragePossOps.getInstance().newRandom(), rand.nextDouble()));
                    } else {
                        if (currentRoll < pTriangular + pTrapezoidal + pInterval) {
                            initialGraph.adjacencyMatrix.get(i).add(new Graph.Edge(IntPossOps.getInstance().newRandom(), rand.nextDouble()));
                        } else {
                            initialGraph.adjacencyMatrix.get(i).add(null);
                        }
                    }
                }
            }
        }
    }

    private void generateTriangularGraph() {
        triangularGraph.n = initialGraph.n;
        triangularGraph.adjacencyMatrix = new Vector<Vector<Graph.Edge>>(triangularGraph.n);
        for (int i = 0; i < triangularGraph.n; ++i) {
            triangularGraph.adjacencyMatrix.add(new Vector<Graph.Edge>(triangularGraph.n));
        }

        for (int i = 0; i < triangularGraph.n; ++i) {
            for (int j = 0; j < triangularGraph.n; ++j) {
                Graph.Edge e = initialGraph.adjacencyMatrix.get(i).get(j);
                if (e != null) {
                    if (e.cost instanceof TriangularNumber) {
                        triangularGraph.adjacencyMatrix.get(i).add(new Graph.Edge((TriangularNumber) e.cost, e.time));
                    } else {
                        if (e.cost instanceof TrapezoidalNumber) {
                            FuzzyNumber newCost = null;
                            if (chainParams == null || chainParams[1] == 2) {
                                if (triTrapConverter != null) {
                                    newCost = triTrapConverter.inverse((TrapezoidalNumber) e.cost);
                                }
                            } else {
                                if (intTriConverter != null) {
                                    newCost = intTriConverter.straight(trapIntConverter.straight((TrapezoidalNumber) e.cost));
                                }
                            }
                            if (newCost != null) {
                                triangularGraph.adjacencyMatrix.get(i).add(new Graph.Edge(newCost, e.time));
                            } else {
                                triangularGraph.adjacencyMatrix.get(i).add(null);
                            }
                        } else {
                            if (e.cost instanceof Interval) {
                                FuzzyNumber newCost = null;
                                if (chainParams == null || chainParams[0] == 1) {
                                    if (intTriConverter != null) {
                                        newCost = intTriConverter.straight((Interval) e.cost);
                                    }
                                } else {
                                    if (triTrapConverter != null) {
                                        newCost = triTrapConverter.inverse(trapIntConverter.inverse((Interval) e.cost));
                                    }
                                }
                                if (newCost != null) {
                                    triangularGraph.adjacencyMatrix.get(i).add(new Graph.Edge(newCost, e.time));
                                } else {
                                    triangularGraph.adjacencyMatrix.get(i).add(null);
                                }
                            }
                        }
                    }
                } else {
                    triangularGraph.adjacencyMatrix.get(i).add(null);
                }
            }
        }
    }

    private void generateTrapezoidalGraph() {
        trapezoidalGraph.n = initialGraph.n;
        trapezoidalGraph.adjacencyMatrix = new Vector<Vector<Graph.Edge>>(trapezoidalGraph.n);
        for (int i = 0; i < trapezoidalGraph.n; ++i) {
            trapezoidalGraph.adjacencyMatrix.add(new Vector<Graph.Edge>(trapezoidalGraph.n));
        }

        for (int i = 0; i < trapezoidalGraph.n; ++i) {
            for (int j = 0; j < trapezoidalGraph.n; ++j) {
                Graph.Edge e = initialGraph.adjacencyMatrix.get(i).get(j);
                if (e != null) {
                    if (e.cost instanceof TrapezoidalNumber) {
                        trapezoidalGraph.adjacencyMatrix.get(i).add(new Graph.Edge((TrapezoidalNumber) e.cost, e.time));
                    } else {
                        if (e.cost instanceof TriangularNumber) {
                            FuzzyNumber newCost = null;
                            if (chainParams == null || chainParams[1] == 1) {
                                if (triTrapConverter != null) {
                                    newCost = triTrapConverter.straight((TriangularNumber) e.cost);
                                }
                            } else {
                                if (trapIntConverter != null) {
                                    newCost = trapIntConverter.inverse(intTriConverter.inverse((TriangularNumber) e.cost));
                                }
                            }
                            if (newCost != null) {
                                trapezoidalGraph.adjacencyMatrix.get(i).add(new Graph.Edge(newCost, e.time));
                            } else {
                                trapezoidalGraph.adjacencyMatrix.get(i).add(null);
                            }
                        } else {
                            if (e.cost instanceof Interval) {
                                FuzzyNumber newCost = null;
                                if (chainParams == null || chainParams[2] == 2) {
                                    if (trapIntConverter != null) {
                                        newCost = trapIntConverter.inverse((Interval) e.cost);
                                    }
                                } else {
                                    if (triTrapConverter != null) {
                                        newCost = triTrapConverter.straight(intTriConverter.straight((Interval) e.cost));
                                    }
                                }
                                if (newCost != null) {
                                    trapezoidalGraph.adjacencyMatrix.get(i).add(new Graph.Edge(newCost, e.time));
                                } else {
                                    trapezoidalGraph.adjacencyMatrix.get(i).add(null);
                                }
                            }
                        }
                    }
                } else {
                    trapezoidalGraph.adjacencyMatrix.get(i).add(null);
                }
            }
        }
    }

    private void generateIntervalGraph() {
        intervalGraph.n = initialGraph.n;
        intervalGraph.adjacencyMatrix = new Vector<Vector<Graph.Edge>>(intervalGraph.n);
        for (int i = 0; i < trapezoidalGraph.n; ++i) {
            intervalGraph.adjacencyMatrix.add(new Vector<Graph.Edge>(intervalGraph.n));
        }

        for (int i = 0; i < intervalGraph.n; ++i) {
            for (int j = 0; j < intervalGraph.n; ++j) {
                Graph.Edge e = initialGraph.adjacencyMatrix.get(i).get(j);
                if (e != null) {
                    if (e.cost instanceof Interval) {
                        intervalGraph.adjacencyMatrix.get(i).add(new Graph.Edge((Interval) e.cost, e.time));
                    } else {
                        if (e.cost instanceof TriangularNumber) {
                            FuzzyNumber newCost = null;
                            if (chainParams == null || chainParams[0] == 2) {
                                if (intTriConverter != null) {
                                    newCost = intTriConverter.inverse((TriangularNumber) e.cost);
                                }
                            } else {
                                if (trapIntConverter != null) {
                                    newCost = trapIntConverter.straight(triTrapConverter.straight((TriangularNumber) e.cost));
                                }
                            }
                            if (newCost != null) {
                                intervalGraph.adjacencyMatrix.get(i).add(new Graph.Edge(newCost, e.time));
                            } else {
                                intervalGraph.adjacencyMatrix.get(i).add(null);
                            }
                        } else {
                            if (e.cost instanceof TrapezoidalNumber) {
                                FuzzyNumber newCost = null;
                                if (chainParams == null || chainParams[2] == 1) {
                                    if (trapIntConverter != null) {
                                        newCost = trapIntConverter.straight((TrapezoidalNumber) e.cost);
                                    }
                                } else {
                                    if (intTriConverter != null) {
                                        newCost = intTriConverter.inverse(triTrapConverter.inverse((TrapezoidalNumber) e.cost));
                                    }
                                }
                                if (newCost != null) {
                                    intervalGraph.adjacencyMatrix.get(i).add(new Graph.Edge(newCost, e.time));
                                } else {
                                    intervalGraph.adjacencyMatrix.get(i).add(null);
                                }
                            }
                        }
                    }
                } else {
                    intervalGraph.adjacencyMatrix.get(i).add(null);
                }
            }
        }
    }

}
