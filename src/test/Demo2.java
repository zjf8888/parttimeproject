package test;


import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

public class Demo2 {
	public static void main(String args[]) throws Exception {
		String path = "http://www.blogjava.net/51AOP/archive/2006/07/19/59064.html";
		
		String result;
		StringBean sb = new StringBean ();
        sb.setLinks (false);
        sb.setReplaceNonBreakingSpaces (true);
        sb.setCollapse (true);
        sb.setURL (path);
		result=sb.getStrings ();
		//System.out.println(result);
		//readAll(result);
		readTextAndLink(result);
		//readByHtml(result);
		//readTextAndTitle(result);
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
			} //else if (node instanceof TitleTag) {
//				TitleTag titlenode = (TitleTag) node;
//				line = titlenode.getTitle();
//			}
			if (isTrimEmpty(line))
				continue;
			System.out.println(line);
		}
	}

	// 处理特定tag 该Demo处理span中class为tpc_content的内容管理

	public static void readTextAndLink(String result) throws Exception {
		Parser parser = new Parser("http://www.blogjava.net/51AOP/archive/2006/07/19/59064.html");
		NodeList nodelist;
		parser.setEncoding("utf-8");
		AndFilter filter =
            new AndFilter(
                    new TagNameFilter("span"),
                        new HasAttributeFilter("class","tpc_content") );
		nodelist = parser.parse(filter);
		Node[] nodes = nodelist.toNodeArray();
		String line = "";
		for (int i = 0; i < nodes.length; i++) {
			
			Node node = nodes[i];
			//System.out.println(node);
			if (node instanceof Tag) {
				Tag tag=(Tag) node;
				//System.out.println(tag);
				NodeList charlist=tag.getChildren();
				for(int j=0;j<charlist.size();j++){
					
					Node chnode=charlist.elementAt(j);
					
					//System.out.println(j);
					//System.out.println(chnode);
					if(chnode instanceof Text){
						Text txt=(Text) chnode;
						
						line+=txt.getText();
						//System.out.println(txt.getText());
					}
				}
				System.out.println(line);
			}
			if(node instanceof Text){
				//System.out.println(node);
				Text txt=(Text)node;
				line=txt.getText();
			}
			if (node instanceof TextNode) {
				//System.out.println(node);
				TextNode textnode = (TextNode) node;
				line = textnode.getText();
			} else if (node instanceof LinkTag) {
				//System.out.println(node);
				LinkTag link = (LinkTag) node;
				line = link.getLink();
			}
//			if (isTrimEmpty(line))
//				continue;
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
			Node node=nodes.elementAt(i);
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
