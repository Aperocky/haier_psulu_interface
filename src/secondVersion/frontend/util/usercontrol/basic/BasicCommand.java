package secondVersion.frontend.util.usercontrol.basic;

import java.util.Optional;

/**
 * Skeletal implementation of user controls using keyboard 
 * including delete, copy and paste. 
 * 
 * @author Feng
 *
 */
public class BasicCommand<T extends ICloneable<T>> {

	private T selected;
	private T deleting;
	private T copying;

	public BasicCommand() {
		
	}
	
	public void select(T select) {
		selected = select;
		deleting = select;
	}

	public void copy() {
		if (selected != null)
			copying = selected;
	}

	public Optional<T> paste() {
		if (copying != null) {
			copying = copying.copy();
		}
		return Optional.ofNullable(copying);
	}

	public Optional<T> delete() {
		return Optional.ofNullable(deleting);
	}

}
