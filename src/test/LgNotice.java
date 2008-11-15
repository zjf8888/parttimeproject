package test;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class LgNotice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String result = "http://www.lg.gov.cn/web/govopen/index.aspx?govId=122";
		readTextAndLink(result);
	}

	public static void readTextAndLink(String result) {
		Parser parser;
		try {
			parser = new Parser(result);

			NodeList nodelist;
			parser.setEncoding("utf-8");
			NodeClassFilter tableFilter = new NodeClassFilter(TableTag.class);
			HasAttributeFilter tableAttribute1 = new HasAttributeFilter(
					"class", "gv");// hava the attribute "bgcolor"
			HasAttributeFilter tableAttribute2 = new HasAttributeFilter(
					"rules", "all");
			HasAttributeFilter tableAttribute3 = new HasAttributeFilter("id",
					"gvArticle");
			AndFilter andfilter = new AndFilter(new NodeFilter[] { tableFilter,
					tableAttribute1, tableAttribute2, tableAttribute3 });
			NodeFilter parentFilter = new HasParentFilter(andfilter, true);
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			AndFilter filter = new AndFilter(linkFilter, parentFilter);
			nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			for (int j = 0; j < nodes.length; j++) {
				Node bodyNode = nodes[j];
				// System.out.println(bodyNode);
				if (bodyNode instanceof LinkTag) {
					LinkTag link = (LinkTag) bodyNode;
					System.out.println("LINK: \t" + link.getLink());
				}
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
