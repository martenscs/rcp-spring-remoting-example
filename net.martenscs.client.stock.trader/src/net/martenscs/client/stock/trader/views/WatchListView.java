package net.martenscs.client.stock.trader.views;

import java.util.ArrayList;
import java.util.List;

import net.martenscs.client.stock.trader.Activator;
import net.martenscs.stock.trader.api.StockDTO;
import net.martenscs.stock.trader.api.StockService;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.dialogs.ViewContentProvider;
import org.eclipse.ui.part.ViewPart;


@SuppressWarnings("restriction")
public class WatchListView extends ViewPart {

	public static final String ID = "net.martenscs.client.stock.trader.views.WatchListView";

	private TableViewer viewer;
	private Action getStocksAction;
	private Action clearTableAction;
	private Action doubleClickAction;

	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public WatchListView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 8;
		gridLayout.marginHeight = 5;
		gridLayout.marginWidth = 5;
		parent.setLayout(gridLayout);

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new WatchListTableLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(StockDTO.class);
		viewer.getControl().setLayoutData(gridData);

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tickerSymbolColumn = new TableColumn(table, SWT.NONE);
		tickerSymbolColumn.setText("Ticker Symbol");
		tickerSymbolColumn.setWidth(100);

		TableColumn lastTradeColumn = new TableColumn(table, SWT.NONE);
		lastTradeColumn.setText("Last Trade");
		lastTradeColumn.setWidth(100);

		TableColumn volumeColumn = new TableColumn(table, SWT.NONE);
		volumeColumn.setText("Volume");
		volumeColumn.setWidth(100);

		TableColumn daysrangeColumn = new TableColumn(table, SWT.NONE);
		daysrangeColumn.setText("Days Range");
		daysrangeColumn.setWidth(100);

		TableColumn avgvolColumn = new TableColumn(table, SWT.NONE);
		avgvolColumn.setText("Avg Vol");
		avgvolColumn.setWidth(100);

		TableColumn daysRangeColumn = new TableColumn(table, SWT.NONE);
		daysRangeColumn.setText("Days Range");
		daysRangeColumn.setWidth(100);

		TableColumn fiftyTwoWeekColumn = new TableColumn(table, SWT.NONE);
		fiftyTwoWeekColumn.setText("52 Week Range");
		fiftyTwoWeekColumn.setWidth(100);

		TableColumn marketCapColumn = new TableColumn(table, SWT.NONE);
		marketCapColumn.setText("Market Cap");
		marketCapColumn.setWidth(100);

		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				WatchListView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(getStocksAction);
		manager.add(new Separator());
		manager.add(clearTableAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(getStocksAction);
		manager.add(clearTableAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(getStocksAction);
		manager.add(clearTableAction);
	}

	private void makeActions() {
		getStocksAction = new Action() {
			@SuppressWarnings("rawtypes")
			public void run() {
				showMessage("Getting Watch List...");

				StockService stockServiceHttp = (StockService) Activator
						.lookupNoThow(
								net.martenscs.stock.trader.api.StockService.class
										.getCanonicalName(), null);

				List<String> tickerList = new ArrayList<String>();
				tickerList.add("msft");
				tickerList.add("sunw");
				tickerList.add("orcl");
				tickerList.add("ibm");
				List stockList = stockServiceHttp.getStocks(tickerList);

				viewer.add(stockList.toArray());
			}
		};
		getStocksAction.setText("Get Watch List");
		getStocksAction.setToolTipText("Get Watch List");
		getStocksAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		clearTableAction = new Action() {
			public void run() {
				showMessage("Clear Watch List");
				viewer.getTable().removeAll();
			}
		};
		clearTableAction.setText("Clear Watch List");
		clearTableAction.setToolTipText("Clear Watch List");
		clearTableAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				showMessage("Double-click detected on " + obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"Watch List View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}