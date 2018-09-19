package util;

import java.util.List;

public class PageBean<T> {

	private int current_page = 1; // 当前的页数
	private int pageCount = 4; // 每页显示的行数
	private int totalCounts; // 总记录数
	private int totalPages; // 总页数
	private List<T> pageData; /// 封装数据

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	public int gettotalPages() {
		if(totalCounts%pageCount==0) {
			totalPages=totalCounts/pageCount;
		}else {
			totalPages=totalCounts/pageCount+1;
					}
		return totalPages;
	}

	public void settotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
