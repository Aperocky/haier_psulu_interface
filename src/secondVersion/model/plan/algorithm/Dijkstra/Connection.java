package secondVersion.model.plan.algorithm.Dijkstra;


public class Connection {
	public int A;
	public int B;
	public double distance;

	Connection(int a, int b, double dist) {
		A = a;
		B = b;
		distance = dist;
	}
	
	@Override 
	public boolean equals(Object other) {
		if(other == this)
			return true;
		if(!(other instanceof Connection))
			return false;
		Connection conn = (Connection) other;
		
		return conn.distance == this.distance && 
				((conn.A == this.A && conn.B == this.B) 
						|| (conn.B == this.A && conn.A == this.B));
	}
}