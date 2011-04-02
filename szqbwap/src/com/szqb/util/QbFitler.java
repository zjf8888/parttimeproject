package com.szqb.util;

import org.htmlparser.NodeFilter;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableTag;

public class QbFitler {
	/**
	 * 首页过滤器
	 * 
	 * @return
	 */
	public static NodeFilter indexFilter() {
		NodeFilter[] nodeFilter = new NodeFilter[5];
		NodeFilter filterdiv = new TagNameFilter("table");
		NodeFilter width = new HasAttributeFilter("width", "100%");
		NodeFilter border = new HasAttributeFilter("border", "0");
		NodeFilter cellspacing = new HasAttributeFilter("cellspacing", "1");
		NodeFilter cellpadding = new HasAttributeFilter("cellpadding", "0");
		nodeFilter[0] = filterdiv;
		nodeFilter[1] = width;
		nodeFilter[2] = border;
		nodeFilter[3] = cellspacing;
		nodeFilter[4] = cellpadding;
		AndFilter andFilter = new AndFilter(nodeFilter);// to link the three
		return andFilter;
	}

	public static NodeFilter pageFilter() {
		NodeFilter[] nodeFilter = new NodeFilter[2];
		NodeFilter filterdiv = new TagNameFilter("span");
		NodeFilter style = new HasAttributeFilter("style", "font-size:13pt");
		nodeFilter[0] = filterdiv;
		nodeFilter[1] = style;
		AndFilter andFilter = new AndFilter(nodeFilter);// to link the three
		return andFilter;
	}	
	public static NodeFilter secondFilter(){
		NodeFilter[] nodeFilter = new NodeFilter[4];
		NodeFilter filterdiv = new TagNameFilter("td");
		NodeFilter clas = new HasAttributeFilter("class", "font02");
		NodeFilter align = new HasAttributeFilter("align", "center");
		NodeFilter style = new HasAttributeFilter("align", "color: #827E7B;");
		nodeFilter[0] = filterdiv;
		nodeFilter[1] = clas;
		nodeFilter[2] = align;
		nodeFilter[3] = style;
		AndFilter andFilter = new AndFilter(nodeFilter);// to link the three
		return andFilter;
	}
	public static NodeFilter pageTitleFilter(){
		NodeFilter[] nodeFilter = new NodeFilter[2];
		NodeFilter filterdiv = new TagNameFilter("div");
		NodeFilter style = new HasAttributeFilter("style", "display:inline");
		nodeFilter[0] = filterdiv;
		nodeFilter[1] = style;
		AndFilter andFilter = new AndFilter(nodeFilter);// to link the three
		return andFilter;
	}
	public static NodeFilter tableFilter() {
		NodeFilter[] nodeFilter = new NodeFilter[6];
		NodeFilter filterLink = new NodeClassFilter(TableTag.class);
		NodeFilter width = new HasAttributeFilter("width", "315");
		NodeFilter border = new HasAttributeFilter("border", "0");
		NodeFilter cellspacing = new HasAttributeFilter("cellspacing", "0");
		NodeFilter cellpadding = new HasAttributeFilter("cellpadding", "0");
		NodeFilter align = new HasAttributeFilter("align", "left");
		nodeFilter[0] = filterLink;
		nodeFilter[1] = width;
		nodeFilter[2] = border;
		nodeFilter[3] = cellspacing;
		nodeFilter[4] = cellpadding;
		nodeFilter[5] = align;
		AndFilter andFilter = new AndFilter(nodeFilter);// to link the three
		return andFilter;
	}
}
