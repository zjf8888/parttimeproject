package test;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;


public class Demo3 {
	public static void main(String args[]) throws Exception {
		String path = "http://www.blogjava.net/51AOP/archive/2006/07/19/59064.html";

		String result;
		StringBean sb = new StringBean();
		sb.setLinks(false);
		sb.setReplaceNonBreakingSpaces(true);
		sb.setCollapse(true);
		sb.setURL(path);
		result = sb.getStrings();
		// System.out.println(result);
		// readAll(result);
		//readTextAndLink(result);
		// readByHtml(result);
		// readTextAndTitle(result);
		readLink();
	}

	// 按页面方式处理.解析标准的html页面
	public static void readByHtml(String content) throws Exception {
		Parser myParser;
		myParser = Parser.createParser(content, "GB2312");

		HtmlPage visitor = new HtmlPage(myParser);

		myParser.visitAllNodesWith(visitor);

		String textInPage = visitor.getTitle();
		System.out.println(textInPage);
		NodeList nodelist;
		nodelist = visitor.getBody();
		System.out.print(nodelist.asString().trim());

	}

	// 读取文本内容和标题
	public static void readTextAndTitle(String result) throws Exception {
		Parser parser;
		NodeList nodelist;
		parser = Parser.createParser(result, "GB2312");
		NodeFilter textFilter = new NodeClassFilter(TextNode.class);
		NodeFilter titleFilter = new NodeClassFilter(TitleTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { textFilter, titleFilter });
		nodelist = parser.parse(lastFilter);
		Node[] nodes = nodelist.toNodeArray();
		String line = "";
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof TextNode) {
				TextNode textnode = (TextNode) node;
				line = textnode.getText();
			} // else if (node instanceof TitleTag) {
			// TitleTag titlenode = (TitleTag) node;
			// line = titlenode.getTitle();
			// }
			if (isTrimEmpty(line))
				continue;
			System.out.println(line);
		}
	}

	// 处理特定tag 该Demo处理span中class为tpc_content的内容管理

	public static void readTextAndLink(String result) throws Exception {
		Parser parser = new Parser("http://10.0.2.200/");
		NodeList nodelist;
		parser.setEncoding("utf-8");
		NodeClassFilter tableFilter = new NodeClassFilter(TableTag.class);
		HasAttributeFilter tableAttribute1 = new HasAttributeFilter("id",
				"Announcement");// hava the attribute "bgcolor"
		HasAttributeFilter tableAttribute2 = new HasAttributeFilter("width",
				"98%");
		HasAttributeFilter tableAttribute3 = new HasAttributeFilter("border",
				"0");
		AndFilter filter = new AndFilter(new NodeFilter[] { tableFilter,
				tableAttribute1, tableAttribute2, tableAttribute3 });

		nodelist = parser.parse(filter);
		Node[] nodes = nodelist.toNodeArray();
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			System.out.println(node);
			Parser body_parser = new Parser(node.toHtml()) ;	
			NodeClassFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList list=body_parser.parse(linkFilter);
			Node[] bodyNodes = list.toNodeArray();
			for(int j=0;j<bodyNodes.length;j++){
				Node bodyNode=bodyNodes[j];
				if (bodyNode instanceof LinkTag) {
					LinkTag link = (LinkTag) bodyNode;
					System.out.println("LINK: \t" + link.getLink());
				}
			}
		}
	}
	/**
	 * 处理所有连接，特别测试HasParentFilter
	 * @param result
	 * @throws Exception
	 */
	public static void readLink() throws Exception {
		Parser parser = new Parser("http://10.0.2.200/");
		NodeList nodelist;
		parser.setEncoding("utf-8");
		NodeClassFilter tableFilter = new NodeClassFilter(TableTag.class);
		HasAttributeFilter tableAttribute1 = new HasAttributeFilter("id",
				"Announcement");// hava the attribute "bgcolor"
		HasAttributeFilter tableAttribute2 = new HasAttributeFilter("width",
				"98%");
		HasAttributeFilter tableAttribute3 = new HasAttributeFilter("border",
				"0");
		AndFilter Parentfilter = new AndFilter(new NodeFilter[] { tableFilter,
				tableAttribute1, tableAttribute2, tableAttribute3 });
		NodeFilter filter = new HasParentFilter(Parentfilter,true);
		NodeFilter filterID = new HasAttributeFilter( "href" );
		AndFilter endfilter = new AndFilter(filterID,filter);
		nodelist = parser.extractAllNodesThatMatch(endfilter);
		Node[] nodes = nodelist.toNodeArray();
		
			for(int j=0;j<nodes.length;j++){
				Node bodyNode=nodes[j];
				//System.out.println(bodyNode);
				if (bodyNode instanceof LinkTag) {
					LinkTag link = (LinkTag) bodyNode;
					System.out.println("LINK: \t" + link.getLink());
				}
			}
		
	}

	public static void readAll(String result) throws Exception {
		Parser parser;
		NodeList nodes;
		parser = Parser.createParser(result, "GB2312");
		nodes = parser.extractAllNodesThatMatch(new TagNameFilter("table"));

		// 读取所有的内容节点
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.elementAt(i);
			System.out.println(node);
			TextNode textnode = (TextNode) nodes.elementAt(i);

			String line = textnode.toPlainTextString().trim();
			if (line.equals(""))
				continue;
			System.out.println(line);
		}
	}

	/**
	 * 去掉左右空格后字符串是否为空
	 */
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(astr.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否为空:null或者长度为0.
	 */
	public static boolean isBlank(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}
	

}
