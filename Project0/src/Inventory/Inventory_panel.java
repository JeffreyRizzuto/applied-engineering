package Inventory;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class Inventory_panel extends JPanel implements Scrollable{

	private static final long serialVersionUID = 6246152661885910554L;
	
	private Inventory_model model;
	private Inventory_view view;

	public Inventory_panel(Inventory_model model, Inventory_view view) {
		this.model = model;
		this.view = view;
	}

	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

}
