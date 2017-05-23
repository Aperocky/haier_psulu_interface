package view.editor.canvas.grid;

//This entire file is part of my masterpiece.
//Yuansong Feng

public abstract class GridPaintStyle implements IPaintStyle{
	private final double width;
	private final double height;
	private final double unit;
	
	public GridPaintStyle(double width, double height, double unit) {
	    this.width = width;
	    this.height = height;
	    this.unit = unit;
	}

	public double width() {
		return width;
	}
	
	public double height(){
		return height;
	}
	
	public double unit() {
		return unit;
	}
	
	

}
