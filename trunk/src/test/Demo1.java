package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class Demo1 {
	public static void main(String args[]) throws Exception {
		String path = "d:/深圳市行政许可电子监察系统.htm";
		StringBuffer sbStr = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(
				new File(path)));
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sbStr.append(temp);
			sbStr.append("\r\n");
		}
		reader.close();

		String result = sbStr.toString();
		readAll(result);
		// readTextAndLink(result);
		// readByHtml(result);
		// readTextAndTitle(result);
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

	// 分别读纯文本和链接

	public static void readTextAndLink(String result) throws Exception {
		Parser parser;
		NodeList nodelist;
		parser = Parser.createParser(result, "GB2312");
		NodeFilter textFilter = new NodeClassFilter(TextNode.class);
		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { textFilter, linkFilter });
		nodelist = parser.parse(lastFilter);
		Node[] nodes = nodelist.toNodeArray();
		String line = "";
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof TextNode) {
				TextNode textnode = (TextNode) node;
				line = textnode.getText();
			} else if (node instanceof LinkTag) {
				LinkTag link = (LinkTag) node;
				line = link.getLink();
			}
			if (isTrimEmpty(line))
				continue;
			System.out.println(line);
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
			if (node instanceof TextNode) {
				TextNode textnode = (TextNode) node;
				String line = textnode.toPlainTextString().trim();
				if (line.equals(""))
					continue;
				System.out.println(line);
			}if (node instanceof TableTag) {
				TableTag tabelTag=(TableTag) node;
				String line = tabelTag.toPlainTextString().trim();
				if (line.equals(""))
					continue;
				System.out.println(line);
			}
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
