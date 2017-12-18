package frontend.model.notification.gameresult;

import model.status.StatusManager;
import util.Observer;

public class MessageMaster implements Observer<StatusManager>{
	private SuccessMessage success;
	private FailureMessage failure;
	private InfeasibleMessage infeasible;
	
	public MessageMaster() {
		success = new SuccessMessage();
		failure = new FailureMessage();
		infeasible = new InfeasibleMessage();
	}

	public SuccessMessage getSuccessMessage() {
		return success;
	}

	public FailureMessage getFailureMessage() {
		return failure;
	}

	public InfeasibleMessage getInfeasibleMessage() {
		return infeasible;
	}

	@Override
	public void update(StatusManager status) {
		if (status.isSuccess())
			success.show();
		else if(status.isFailure())
			failure.show();
		if(!status.isPlanning() && !status.isFeasible() && status.isMessageOn())
			infeasible.show();
	}
	
	

}
