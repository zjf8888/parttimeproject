package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import org.htmlparser.Node;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.Text;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

/**
 * 用来遍历WML文档中的所有超链接
 * 
 * @author Winter Lau
 */
public class HyperLinkTrace {
	public static void main(String[] args) throws Exception {
		// 初始化HTMLParser
		Parser parser = new Parser();
		parser.setEncoding("gb2312");
		parser.setInputHTML(getWmlContent());

		// 注册新的结点解析器
		PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
		factory.registerTag(new WmlGoTag());
		parser.setNodeFactory(factory);
		// 遍历符合条件的所有节点
		NodeList nlist = parser.extractAllNodesThatMatch(lnkFilter);
		for (int i = 0; i < nlist.size(); i++) {
			TagNode node = (TagNode) nlist.elementAt(i);
			// System.out.println(node);
			if (node instanceof LinkTag) {
				LinkTag link = (LinkTag) node;
				//System.out.println("LINK: \t" + link.getLink());
			} else if (node instanceof WmlGoTag) {
				NodeList charlist = node.getChildren();
				for (int j = 0; j < charlist.size(); j++) {
					Node chnode = charlist.elementAt(j);
					 System.out.println(chnode);
					// System.out.println(chnode);
					if (chnode instanceof Text) {
						
						Text txt = (Text) chnode;

						System.out.println(txt.getText());
					}
				}
				WmlGoTag go = (WmlGoTag) node;
				//System.out.println("GO: \t" + go.getLink());
			}
		}
	}

	/**
	 * 获取测试的WML脚本内容
	 * 
	 * @return
	 * @throws Exception
	 */
	static String getWmlContent() throws Exception {
		String path = "D:/深圳市财政局.htm";
		BufferedReader in = new BufferedReader(new FileReader(new File(path)));
		StringBuffer wml = new StringBuffer();
		do {
			String line = in.readLine();
			if (line == null)
				break;
			if (wml.length() > 0)
				wml.append("\r\n");
			wml.append(line);
		} while (true);
		return wml.toString();
	}

	/**
	 * 解析出所有的链接，包括行为<a>与<go>
	 */
	static NodeFilter lnkFilter = new NodeFilter() {
		public boolean accept(Node node) {
			if (node instanceof WmlGoTag)
				return true;
			if (node instanceof LinkTag)
				return true;
			return false;
		}
	};

	/**
	 * WML文档的GO标签解析器
	 * 
	 * @author Winter Lau
	 */
	static class WmlGoTag extends CompositeTag {
		private static final String[] mIds = new String[] { "D", "T", "L",
				"Time" };

		private static final String[] mEndTagEnders = new String[] { "Documents" };

		public String[] getIds() {
			return (mIds);
		}

		public String[] getEnders() {
			return (mIds);
		}

		public String[] getEndTagEnders() {
			return (mEndTagEnders);
		}

		public String getLink() {
			return super.getAttribute("L");
		}

		public String getMethod() {
			return super.getAttribute("method");
		}
	}
}
