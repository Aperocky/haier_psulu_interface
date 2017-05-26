package model.plan.algorithm.Dijkstra;


public class Neighbor {
	public int A;
	public int B;
	public double distance;

	Neighbor(int a, int b, double dist) {
		A = a;
		B = b;
		distance = dist;
	}
	
	@Override 
	public boolean equals(Object other) {
		if(other == this)
			return true;
		if(!(other instanceof Neighbor))
			return false;
		Neighbor conn = (Neighbor) other;
		
		return conn.distance == this.distance && 
				((conn.A == this.A && conn.B == this.B) 
						|| (conn.B == this.A && conn.A == this.B));
	}
}