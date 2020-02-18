package du_paths;

public class Node {

	private int node;
	private boolean def, use;
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	
	public Node(int node, boolean def, boolean use) {
		super();
		this.node=node;
		this.def = def;
		this.use = use;
	}
	
	public boolean isDef() {
		return def;
	}
	public void setDef(boolean def) {
		this.def = def;
	}
	public boolean isUse() {
		return use;
	}
	public void setUse(boolean use) {
		this.use = use;
	}
	@Override
	public boolean equals(Object obj) {
		Node n=(Node) obj;
		return this.node==n.node;
	}
	
	
	@Override
	public String toString() {
		return "Node [node=" + node + ", def=" + def + ", use=" + use + "]";
	}
	@Override
	public int hashCode() {
		return Integer.hashCode(node);
	}
	
}