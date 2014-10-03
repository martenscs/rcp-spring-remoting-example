package net.martenscs.client.stock.trader;

import net.martenscs.client.stock.trader.views.ExplorerView;
import net.martenscs.client.stock.trader.views.WatchListView;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;

public class Perspective implements IPerspectiveFactory {

    @SuppressWarnings("unused")
	public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        layout.setFixed(false);

        layout.addStandaloneView(ExplorerView.ID,  
        		false, 
        		IPageLayout.LEFT, 
        		0.25f, 
        		editorArea);
        IFolderLayout topLeft = layout.createFolder("TOP", 
        		IPageLayout.TOP, 
        		0.50f, 
        		editorArea);
        layout.addView(WatchListView.ID, 
        		IPageLayout.BOTTOM, 
        		0.25f, 
        		editorArea);
    }
}
