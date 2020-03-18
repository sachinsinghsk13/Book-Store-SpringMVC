package com.techjs.yourbookstrore.ui;

public class Pagination {
	private static final int defaultTtemPerPage = 15;
	private long total;
	private int current;
	private int itemPerPage = defaultTtemPerPage;
	private int totolPages;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getItemPerPage() {
		return itemPerPage;
	}
	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}
	public int getTotolPages() {
		return totolPages;
	}
	public void setTotolPages(int totolPages) {
		this.totolPages = totolPages;
	}
	public static int getDefaultttemperpage() {
		return defaultTtemPerPage;
	}
	
}
