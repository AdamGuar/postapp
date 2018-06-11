package com.app.postappclient.model;


import java.util.ArrayList;
import java.util.List;

public class ParsedModel {

    String modelName;
    private List<Node> nodes = new ArrayList<>();
    private List<ElementSolid> elements = new ArrayList<>();

    public ParsedModel(String modelName, List<Node> nodes, List<ElementSolid> elements) {
        this.modelName = modelName;
        this.nodes = nodes;
        this.elements = elements;
    }

    public ParsedModel() {
    }

    public String getModelName() {
        return modelName;
    }


    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public List<Node> getNodes() {
        return nodes;
    }


    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    public List<ElementSolid> getElements() {
        return elements;
    }


    public void setElements(List<ElementSolid> elements) {
        this.elements = elements;
    }
}

class Node {

    private int id;
    private float x, y, z;
    private double value;

    public Node(int id, float x, float y, float z, double value) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
    }
        
	public Node() {
	}

	public int getId() {
		return id;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public double getValue() {
		return value;
	}

    

}


class ElementSolid {

    private int eid;
    private int pid;
    private List<Node> nodes;

    public ElementSolid(int eid, int pid, List<Node> nodes) {
        this.eid = eid;
        this.pid = pid;
        this.nodes = nodes;
    }

    public int getEid() {
        return eid;
    }

    public int getPid() {
        return pid;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public ElementSolid() {
    }
}
