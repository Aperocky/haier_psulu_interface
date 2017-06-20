package util;

public class PathReaderTest {

	public static void main(String[] args) {
		ResourceParser rp = new ResourceParser("path");
		System.out.println(rp.getString("psulu_output"));
	}
}
