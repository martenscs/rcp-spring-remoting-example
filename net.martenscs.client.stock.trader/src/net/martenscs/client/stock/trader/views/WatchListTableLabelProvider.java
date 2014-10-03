package net.martenscs.client.stock.trader.views;

import java.text.NumberFormat;

import net.martenscs.stock.trader.api.StockDTO;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class WatchListTableLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	private static NumberFormat numberFormat = NumberFormat.getInstance();

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element != null) {
			switch (columnIndex) {
			case 0:
				return ((StockDTO) element).getTickerSymbol();
			case 1:
				return ((StockDTO) element).getLastTrade().toString();
			case 2:
				return numberFormat.format(((StockDTO) element).getVolume());
			case 3:
				return ((StockDTO) element).getDaysRange();
			case 4:
				return numberFormat.format(((StockDTO) element).getAvgVol());
			case 5:
				return ((StockDTO) element).getDaysRange();
			case 6:
				return ((StockDTO) element).getFiftyTwoWeekRange();
			case 7:
				return ((StockDTO) element).getMarketCap();
			}
		}

		return "";
	}

}
