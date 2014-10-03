package net.martenscs.stock.trader.api;

import java.io.Serializable;

/**
 * User: martenscs
 */
public class StockDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1965609278799534981L;
	private String tickerSymbol;
	private Double avgVol;
	private Double change;
	private String daysRange;
	private String fiftyTwoWeekRange;
	private Double lastTrade;
	private String marketCap;
	private Double volume;

	public StockDTO(String tickerSymbol, Double avgVol, Double change,
			String daysRange, String fiftyTwoWeekRange, Double lastTrade,
			String marketCap, Double volume) {
		this.tickerSymbol = tickerSymbol;
		this.avgVol = avgVol;
		this.change = change;
		this.daysRange = daysRange;
		this.fiftyTwoWeekRange = fiftyTwoWeekRange;
		this.lastTrade = lastTrade;
		this.marketCap = marketCap;
		this.volume = volume;
	}

	public Double getAvgVol() {
		return avgVol;
	}

	public Double getChange() {
		return change;
	}

	public String getDaysRange() {
		return daysRange;
	}

	public String getFiftyTwoWeekRange() {
		return fiftyTwoWeekRange;
	}

	public Double getLastTrade() {
		return lastTrade;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public String getTickerSymbol() {
		return tickerSymbol;
	}

	public Double getVolume() {
		return volume;
	}
}
