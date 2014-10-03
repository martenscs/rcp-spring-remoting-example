package net.martenscs.osgi.ws.services.stock;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import net.martenscs.stock.trader.api.StockDTO;
import net.martenscs.stock.trader.api.StockService;


@SuppressWarnings("rawtypes")
public class StockServiceImpl implements StockService {
	private Map mapOfStocks;

	public List<StockDTO> getStocks(List<String> tickerList) {
		List<StockDTO> resultList = new ArrayList<StockDTO>();

		for (Iterator it = tickerList.listIterator(); it.hasNext();) {
			resultList.add((StockDTO) mapOfStocks.get(it.next()));
		}

		return resultList;
	}

	public void setStocks(Map<String, StockDTO> mapOfStocks) {
		this.mapOfStocks = mapOfStocks;
	}
}
