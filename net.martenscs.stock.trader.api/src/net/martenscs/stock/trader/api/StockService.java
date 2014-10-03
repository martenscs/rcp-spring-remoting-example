package net.martenscs.stock.trader.api;

import java.util.List;
import java.util.Map;

/**
 * @author martenscs
 * 
 */
public interface StockService {
	/**
	 * @param tickerList
	 * @return
	 */
	public List<StockDTO> getStocks(List<String> tickerList);

	/**
	 * @param mapOfStocks
	 */
	public void setStocks(Map<String, StockDTO> mapOfStocks);
}
