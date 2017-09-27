package frontend.model.notification.gameresult;

public class InfeasibleMessage extends Message{
	
	public InfeasibleMessage() {
		super("Infeasible Plan", "Given parameters do not produce a feasible plan.");
	}

}
