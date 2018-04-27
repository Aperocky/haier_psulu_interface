package model.gamedata.game.log;

import java.io.FileWriter;
import java.io.IOException;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

public class Log {
	private YamlWriter writer;
	private YamlReader reader;
	
	public Log(int uid) {
		try {
			writer = new YamlWriter(new FileWriter("/Users/Feng/Documents/workspace/haier_psulu_interface/data/" + 
					uid + 
					".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void writeAction(ActionEntry action) {
		try {
//			ActionEntry test = new ActionEntry();
//			test.setUid(101);
//			writer.write(test);
			writer.write(action);
		} catch (YamlException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void flushOutput() {
		try {
			writer.close();
		} catch (YamlException e) {
			e.printStackTrace();
		}
	}

//	public void readAction() {
//		try {
//			reader = new YamlReader(new FileReader("/Users/Feng/Documents/workspace/YamlTest/src/user.yml"));
//			contact = reader.read(User.class);
//			System.out.println(contact.getAge());
//			System.out.println(contact.getName());
//		} catch (YamlException | FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
}
